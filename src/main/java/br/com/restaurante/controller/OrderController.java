package br.com.restaurante.controller;

import br.com.restaurante.dao.impl.Sql2oOrderDao;
import br.com.restaurante.model.Order;
import com.google.gson.Gson;
import org.sql2o.Sql2o;
import spark.Request;
import spark.Response;
import spark.Route;

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

    public  static Route post = (req, res) ->{
        return AddOrder(req, res);
    };

    public static Route getAll = (req, res) -> {
        return GetAllOrders();
    };

    public static Route getById = (req, res) -> {
        return GetOrderById(req);
    };

    public static Route delete = (req, res) -> {
        return DeleteOrderById(req, res);
    };

    public static String GetAllOrders() {
        return gson.toJson(orderDao.getAll());
    }

    public static String GetOrderById(Request req) throws Exception {
        int orderId = Integer.parseInt(req.params("id"));
        Order order = orderDao.findById(orderId);
        if (order == null) {
            throw new Exception(String.format("No order with the id: \"%s\" exists", req.params("id")));
        } else {
            return gson.toJson(order);
        }
    }

    public static String DeleteOrderById(Request req, Response res) {
        int productId = Integer.parseInt(req.params("id"));
        orderDao.deleteById(productId);
        res.status(201);
        return "";
    }

    public static String AddOrder(Request req, Response res) {
        Order order = gson.fromJson(req.body(), Order.class);
        orderDao.add(order);
        res.status(201);
        // res.type("application/json");
        return gson.toJson(order);
    }
}


