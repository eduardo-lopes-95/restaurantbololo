package br.com.restaurante.controller;

import br.com.restaurante.dao.impl.Sql2oAddressDao;
import br.com.restaurante.model.Address;
import br.com.restaurante.service.ZipcodeServiceImpl;
import br.com.restaurante.shared.ViaCepZipCodeDto;
import com.google.gson.Gson;
import org.sql2o.Sql2o;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;

public class AddressController {

    static Gson gson;
    static Sql2oAddressDao addressDao;

    public static void init(Gson gsonParam, Sql2o sql2o) {
        gson = gsonParam;
        addressDao = new Sql2oAddressDao(sql2o);
    }

    public AddressController() {
    }

    public static Route post = (req, res) -> {
        return AddNewAddress(req, res);
    };

    public static Route getById = (req, res) -> {
        // READ
        return GetAddressById(req, res);
    };

    public static Route getAll = (req, res) -> {
        // READ
        return GetAllAddresses(res);
    };

    public static Route deletes = (req, res) -> {
        return DeleteAddress(req);
    };

    private static String DeleteAddress(Request req) {
        int addressId = Integer.parseInt(req.params("id"));
        addressDao.deleteById(addressId);
        return "Address deleted.";
    }

    private static String GetAddressById(Request req, Response res) {
        int addressId = Integer.parseInt(req.params("id"));
        res.type("application/json");
        return gson.toJson(addressDao.findById(addressId));
    }

    private static String AddNewAddress(Request req, Response res) {
        String cep = req.params("zipCode");
        ViaCepZipCodeDto dto;
        try {
            dto = new ZipcodeServiceImpl().GetZipCode(cep);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Address address = new Address();
        address.setZipCode(dto.cep.replace("-",""));
        address.setCity(dto.localidade);
        address.setStreet(dto.logradouro);
        address.setNeighborhood(dto.bairro);
        address.setAdditionalInformation(dto.complemento);

        addressDao.add(address);

        res.status(201);
        return gson.toJson(address);
    }

    private static String GetAllAddresses(Response res) {
        res.type("application/json");
        return gson.toJson(addressDao.getAll());
    }
}
