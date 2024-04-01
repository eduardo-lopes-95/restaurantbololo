package br.com.restaurante.controller;

import br.com.restaurante.dao.impl.Sql2oOrderDao;
import br.com.restaurante.model.Order;
import com.google.gson.Gson;
import org.sql2o.Sql2o;

import static spark.Spark.*;

public class OrderController {

    static Gson gson;
    static Sql2oOrderDao orderDao;

    public static void init(Gson gsonParam, Sql2o sql2o) {
        gson = gsonParam;
        orderDao = new Sql2oOrderDao(sql2o);
    }

    public OrderController() {
    }

    public  static void posts() {
        post("/orders/new", "application/json", (req, res) -> {
            Order order = gson.fromJson(req.body(), Order.class);
            orderDao.add(order);
            res.status(201);
            // res.type("application/json");
            return gson.toJson(order);
        });

    }

    public static void gets() {
        get("/orders", "application/json", (req, res) -> { // accept a request in format JSON from an app
            return gson.toJson(orderDao.getAll());// send it back to be displayed
        });

        get("/orders/:id", "application/json", (req, res) -> {
            int orderId = Integer.parseInt(req.params("id"));
            Order order = orderDao.findById(orderId);
            if (order == null) {
                throw new Exception(String.format("No order with the id: \"%s\" exists", req.params("id")));
            } else {
                return gson.toJson(order);
            }
        });
    }

    public static void deletes() {
        delete("/orders/:id", (req, res) -> {
            int productId = Integer.parseInt(req.params("id"));
            orderDao.deleteById(productId);
            res.status(201);
            return "";
        });
    }
}


