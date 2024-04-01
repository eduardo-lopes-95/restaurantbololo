package br.com.restaurante.controller;

import br.com.restaurante.dao.impl.Sql2oAddressDao;
import br.com.restaurante.model.Address;
import com.google.gson.Gson;
import org.sql2o.Sql2o;

import static spark.Spark.*;

public class AddressController {

    static Gson gson;
    static Sql2oAddressDao addressDao;

    public static void init(Gson gsonParam, Sql2o sql2o) {
        gson = gsonParam;
        addressDao = new Sql2oAddressDao(sql2o);
    }

    public AddressController() {
    }

    public static void posts(){
        post("/address/new", "application/json", (req, res) -> {
            Address address = gson.fromJson(req.body(), Address.class);
            addressDao.add(address);
            res.status(201);
            return gson.toJson(address);
        });
    }

    public static void gets() {
        // READ
        get("/address/:id", "application/json", (req, res) -> { // accept a request in format JSON from an app
            // res.type("application/json");
            int addressId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(addressDao.findById(addressId));
        });
    }

    public static void deletes(){
        delete("/address/:id", (req, res) -> {
            int addressId = Integer.parseInt(req.params("id"));
            addressDao.deleteById(addressId);
            return "Address deleted.";
        });
    }
}
