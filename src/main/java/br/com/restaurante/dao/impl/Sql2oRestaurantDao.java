package br.com.restaurante.dao.impl;

import br.com.restaurante.dao.RestaurantDao;
import br.com.restaurante.model.Restaurant;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oRestaurantDao implements RestaurantDao {

    private final Sql2o sql2o;

    public Sql2oRestaurantDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Restaurant restaurant) {
        String sql = "INSERT INTO restaurants (name, addressId, phone) VALUES (:name, :addressId, :phone)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(restaurant)
                    .executeUpdate()
                    .getKey();
            restaurant.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Restaurant> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM restaurants")
                    .executeAndFetch(Restaurant.class);
        }
    }

    @Override
    public void update(int id, String newName, int newAddressId, String newPhone) {
        String sql = "UPDATE restaurants SET (name, addressId, phone) = (:name, :addressId, :phone) WHERE id=:id"; //CHECK!!!

        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("name", newName)
                    .addParameter("addressId", newAddressId)
                    .addParameter("phone", newPhone)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public Restaurant findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM restaurants WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Restaurant.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from restaurants WHERE id = :id"; //raw sql
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
