package br.com.restaurante.controller;

import br.com.restaurante.dao.impl.Sql2oProductDao;
import br.com.restaurante.model.Product;
import com.google.gson.Gson;
import org.sql2o.Sql2o;

import static spark.Spark.*;

public class ProductController {

    static Gson gson;
    static Sql2oProductDao productDao;

    public static void init(Gson gsonParam, Sql2o sql2o) {
        gson = gsonParam;
        productDao = new Sql2oProductDao(sql2o);
    }

    private ProductController() {
    }

    public  static void posts() {
        post("/products/new", "application/json", (req, res) -> {
            Product product = gson.fromJson(req.body(), Product.class);
            productDao.add(product);
            res.status(201);
            // res.type("application/json");
            return gson.toJson(product);
        });

    }

    public static void gets() {
        get("/products", "application/json", (req, res) -> { // accept a request in format JSON from an app
            return gson.toJson(productDao.getAll());// send it back to be displayed
        });
    }

    public static void deletes() {
        delete("/products/:id", (req, res) -> {
            int productId = Integer.parseInt(req.params("id"));
            productDao.deleteById(productId);
            res.status(201);
            return "";
        });
    }

}
