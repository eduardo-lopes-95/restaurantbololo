package br.com.restaurante.dao;

import br.com.restaurante.model.Product;
import br.com.restaurante.model.Restaurant;

import java.util.List;

public interface RestaurantDao {
    // create
    void add(Restaurant restaurant);

    //read
    List<Restaurant> getAll();

    //update
    void update(int id, String newName, int newAddressId, String newPhone);

    // find
    Restaurant findById(int id);

    //delete
    void deleteById(int id);
}
