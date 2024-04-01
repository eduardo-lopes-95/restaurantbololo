package br.com.restaurante.controller;

import br.com.restaurante.dao.impl.Sql2oClientDao;
import br.com.restaurante.dao.impl.Sql2oRestaurantDao;
import br.com.restaurante.model.Address;
import br.com.restaurante.model.Client;
import com.google.gson.Gson;
import org.sql2o.Sql2o;

import static spark.Spark.*;

public class ClientController {

    static Gson gson;
    static Sql2oClientDao clientDao;

    public static void init(Gson gsonParam, Sql2o sql2o) {
        gson = gsonParam;
        clientDao = new Sql2oClientDao(sql2o);
    }

    public ClientController() {
    }

    public static void posts(){
        post("/client/new", "application/json", (req, res) -> {
            Client client = gson.fromJson(req.body(), Client.class);
            clientDao.add(client);
            res.status(201);
            return gson.toJson(client);
        });
    }

    public static void gets() {
        // READ
        get("/client/:id", "application/json", (req, res) -> { // accept a request in format JSON from an app
            // res.type("application/json");
            int clientId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(clientDao.findById(clientId));
        });
    }

    public static void deletes(){
        delete("/client/:id", (req, res) -> {
            int clientId = Integer.parseInt(req.params("id"));
            clientDao.deleteById(clientId);
            return "Client deleted.";
        });
    }
}
