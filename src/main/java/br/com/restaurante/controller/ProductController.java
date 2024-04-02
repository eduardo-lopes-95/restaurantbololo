package br.com.restaurante.controller;

import br.com.restaurante.dao.impl.Sql2oProductDao;
import br.com.restaurante.model.Product;
import com.google.gson.Gson;
import org.sql2o.Sql2o;
import spark.Request;
import spark.Response;
import spark.Route;


public class ProductController {

    static Gson gson;
    static Sql2oProductDao productDao;

    public static void init(Gson gsonParam, Sql2o sql2o) {
        gson = gsonParam;
        productDao = new Sql2oProductDao(sql2o);
    }

    private ProductController() {
    }

    public static Route post = (req, res) ->  {
        return AddProduct(req, res);
    };

    public static Route getAll = (req, res) ->  {
        return GetAllProduct();
    };

    public static Route delete = (req, res) ->  {
        return DeleteOrderById(req, res);
    };

    private static String AddProduct(Request req, Response res) {
        Product product = gson.fromJson(req.body(), Product.class);
        productDao.add(product);
        res.status(201);
        // res.type("application/json");
        return gson.toJson(product);
    }

    private static String GetAllProduct() {
        return gson.toJson(productDao.getAll());
    }

    private static String DeleteOrderById(Request req, Response res) {
        int productId = Integer.parseInt(req.params("id"));
        productDao.deleteById(productId);
        res.status(201);
        return "";
    }
}
