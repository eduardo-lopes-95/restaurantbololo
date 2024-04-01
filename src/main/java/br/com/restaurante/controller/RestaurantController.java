package br.com.restaurante.controller;

import static spark.Spark.post;
import static spark.Spark.get;
import static spark.Spark.delete;

import br.com.restaurante.dao.impl.Sql2oRestaurantDao;
import br.com.restaurante.model.Restaurant;
import com.google.gson.Gson;
import org.sql2o.Sql2o;

public class RestaurantController {
    static Gson gson;
    static Sql2oRestaurantDao restaurantDao;

    public static void init(Gson gsonParam, Sql2o sql2o) {
        gson = gsonParam;
        restaurantDao = new Sql2oRestaurantDao(sql2o);
    }

    public RestaurantController() {
    }

    public static void posts(){
        post("/restaurants/new", "application/json", (req, res) -> {
            Restaurant restaurant = gson.fromJson(req.body(), Restaurant.class);
            restaurantDao.add(restaurant);
            res.status(201);
            return gson.toJson(restaurant);
        });
    }

    public static void gets() {
        // READ

        get("/restaurants", "application/json", (req, res) -> { // accept a request in format JSON from an app
            // res.type("application/json");
            return gson.toJson(restaurantDao.getAll());// send it back to be displayed
        });

        get("/restaurants/:id", "application/json", (req, res) -> { // accept a request in format JSON from an app
            // res.type("application/json");
            int restaurantId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(restaurantDao.findById(restaurantId));
        });
    }

    public static void deletes(){
        delete("/restaurants/:id", (req, res) -> {
            int restaurantId = Integer.parseInt(req.params("id"));
            restaurantDao.deleteById(restaurantId);
            return "Restaurant deleted.";
        });
    }
}
