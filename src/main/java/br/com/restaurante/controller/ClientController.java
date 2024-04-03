package br.com.restaurante.controller;

import br.com.restaurante.dao.impl.Sql2oClientDao;
import br.com.restaurante.model.Client;
import com.google.gson.Gson;
import org.sql2o.Sql2o;
import spark.Request;
import spark.Response;
import spark.Route;

public class ClientController {

    static Gson gson;
    static Sql2oClientDao clientDao;

    public static void init(Gson gsonParam, Sql2o sql2o) {
        gson = gsonParam;
        clientDao = new Sql2oClientDao(sql2o);
    }

    public ClientController() {
    }

    public static Route post = (req, res) -> {
        return AddClient(req, res);
    };

    public static Route getById = (req, res) -> {
        // READ
        return GetClientById(req, res);
    };

    public static Route getAll = (req, res) -> {
        // READ
        return GetAllClients(res);
    };

    public static Route delete = (req, res) -> {
        return DeleteClientById(req);
    };

    public static String DeleteClientById(Request req) {
        int clientId = Integer.parseInt(req.params("id"));
        clientDao.deleteById(clientId);
        return "Client deleted.";
    }

    public static String AddClient(Request req, Response res) {
        Client client = gson.fromJson(req.body(), Client.class);
        clientDao.add(client);
        res.status(201);
        return gson.toJson(client);
    }

    public static String GetClientById(Request req, Response res) {
        int clientId = Integer.parseInt(req.params("id"));
        res.type("application/json");
        return gson.toJson(clientDao.findById(clientId));
    }

    public static String GetAllClients(Response res) {
        res.type("application/json");
        return gson.toJson(clientDao.getAll());
    }
}
