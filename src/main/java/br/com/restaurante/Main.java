package br.com.restaurante;

import br.com.restaurante.controller.*;
import com.google.gson.Gson;
import org.sql2o.Sql2o;
import org.sql2o.Connection;

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
        RestaurantController.posts();
        RestaurantController.gets();
        RestaurantController.deletes();

        AddressController.posts();
        AddressController.gets();
        AddressController.deletes();

        ClientController.posts();
        ClientController.gets();
        ClientController.deletes();

        OrderController.posts();
        OrderController.gets();
        OrderController.deletes();

        ProductController.posts();
        ProductController.gets();
        ProductController.deletes();

        OrderProductController.posts();
        OrderProductController.gets();

        after((req, res) -> {
            res.type("application/json");
        });
    }
}