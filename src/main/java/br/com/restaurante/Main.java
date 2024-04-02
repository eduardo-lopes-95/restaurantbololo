package br.com.restaurante;

import br.com.restaurante.controller.*;
import com.google.gson.Gson;
import org.sql2o.Sql2o;
import org.sql2o.Connection;
import spark.Spark;

import static spark.Spark.after;
import static spark.Spark.port;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    static Connection conn;
    static Gson gson = new Gson();

    public static void main(String[] args) {

        String connectionString = "jdbc:h2:~/restaurantapp.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        RestaurantController.init(gson, sql2o);
        AddressController.init(gson, sql2o);
        ClientController.init(gson, sql2o);
        OrderController.init(gson, sql2o);
        ProductController.init(gson, sql2o);
        OrderProductController.init(gson, sql2o);

        conn = sql2o.open();

        port(8080);

        Spark.get("/", (req, res) -> {return Main.class.getResourceAsStream("/index.html");});

        Spark.post("restaurant/new", RestaurantController.post);
        //Spark.get("/restaurant/:id", RestaurantController.getById);
        Spark.get("/restaurant/list", RestaurantController.getAll);
        Spark.delete("/restaurant/:id", RestaurantController.delete);

        Spark.post("address/new/:zipCode", AddressController.post);
        //Spark.get("/address/:id", AddressController.getById);
        Spark.get("/address/list", AddressController.getAll);
        Spark.delete("/address/:id", AddressController.deletes);

        Spark.post("client/new", ClientController.post);
        //Spark.get("/client/:id", ClientController.getById);
        Spark.get("/client/list", ClientController.getAll);
        Spark.delete("/client/:id", ClientController.delete);

        Spark.post("order/new", OrderController.post);
        //Spark.get("/order/:id/", OrderController.getById);
        Spark.get("/order/list", OrderController.getAll);
        Spark.delete("/order/:id", OrderController.delete);

        Spark.post("product/new", ProductController.post);
        Spark.get("/product/list", ProductController.getAll);
        Spark.delete("/product/:id", ProductController.delete);

        Spark.post("orderproduct/new", OrderProductController.post);
        Spark.get("/orderproduct/list", OrderProductController.getAll);
        Spark.get("/orders/:id/products", OrderProductController.GetProductsByOrder);
        Spark.get("/products/:id/orders", OrderProductController.GetOrderByProduct);

        after((req, res) -> {
            res.type("application/json");
        });
    }
}