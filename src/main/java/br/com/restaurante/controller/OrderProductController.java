package br.com.restaurante.controller;

import br.com.restaurante.dao.impl.Sql2oOrderProductDao;
import br.com.restaurante.model.Order;
import br.com.restaurante.model.OrderProduct;
import br.com.restaurante.model.Product;
import com.google.gson.Gson;
import org.sql2o.Sql2o;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

import static spark.Spark.*;

public class OrderProductController {
    static Gson gson;
    static Sql2oOrderProductDao ordertProductDao;

    public static void init(Gson gsonParam, Sql2o sql2o) {
        gson = gsonParam;
        ordertProductDao = new Sql2oOrderProductDao(sql2o);
    }

    public OrderProductController() {
    }

    public static Route post = (req, res) -> {
        return AddOrderProduct(req, res);
    };

    public static Route getAll = (req, res) -> {
        return GetAllOrderProduct();
    };

    public static Route GetProductsByOrder = (req, res) -> {
        return GetProductByOrderId(req);
    };

    public static Route GetOrderByProduct = (req, res) -> {
        return GetOrderByProductId(req);
    };

    public static String AddOrderProduct(Request req, Response res) {
        OrderProduct orderProduct = gson.fromJson(req.body(), OrderProduct.class);
        ordertProductDao.add(orderProduct);
        res.status(201);
        return gson.toJson(orderProduct);
    }

    public static String GetOrderByProductId(Request req) throws Exception {
        int productId = Integer.parseInt(req.params("id"));
        List<Order> orderByProduct = ordertProductDao.findAllByProductId(productId);

        if (orderByProduct == null) {
            throw new Exception(String.format("No order with product id: \"%s\" exists", req.params("id")));
        } else {
            return gson.toJson(orderByProduct);
        }
    }

    public static String GetProductByOrderId(Request req) throws Exception {
        int orderId = Integer.parseInt(req.params("id"));
        List<Product> productsByOrder = ordertProductDao.findAllByOrderId(orderId);

        if (productsByOrder == null) {
            throw new Exception(String.format("No product to the order id: \"%s\" exists", req.params("id")));
        } else {
            return gson.toJson(productsByOrder);
        }
    }

    public static String GetAllOrderProduct() {
        return gson.toJson(ordertProductDao.getAll());
    }
}
