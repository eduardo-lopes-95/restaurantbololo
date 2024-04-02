package br.com.restaurante.controller;

import static spark.Spark.post;
import static spark.Spark.get;
import static spark.Spark.delete;

import br.com.restaurante.dao.impl.Sql2oRestaurantDao;
import br.com.restaurante.model.Restaurant;
import com.google.gson.Gson;
import org.sql2o.Sql2o;
import spark.Request;
import spark.Response;
import spark.Route;

public class RestaurantController {
    static Gson gson;
    static Sql2oRestaurantDao restaurantDao;

    public static void init(Gson gsonParam, Sql2o sql2o) {
        gson = gsonParam;
        restaurantDao = new Sql2oRestaurantDao(sql2o);
    }

    public RestaurantController() {
    }

    public static Route post = (req, res) ->{
        return AddRestaurant(req, res);
    };

    public static Route getById = (req, res) -> {
        // READ
        return GetRestaurantById(req, res);
    };

    public static Route getAll = (req, res) -> {
        // READ
        return GetAllRestaurants();
    };

    public static Route delete = (req, res) -> {
        return DeleteRestaurantById(req);
    };

    private static String DeleteRestaurantById(Request req) {
        int restaurantId = Integer.parseInt(req.params("id"));
        restaurantDao.deleteById(restaurantId);
        return "Restaurant deleted.";
    }

    private static String AddRestaurant(Request req, Response res) {
        Restaurant restaurant = gson.fromJson(req.body(), Restaurant.class);
        restaurantDao.add(restaurant);
        res.status(201);
        return gson.toJson(restaurant);
    }

    private static String GetRestaurantById(Request req, Response res) {
        int restaurantId = Integer.parseInt(req.params("id"));
        res.type("application/json");
        return gson.toJson(restaurantDao.findById(restaurantId));
    }

    private static String GetAllRestaurants() {
        return gson.toJson(restaurantDao.getAll());
    }
}
