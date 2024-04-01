package br.com.restaurante.dao;

import br.com.restaurante.model.Client;
import br.com.restaurante.model.Order;
import br.com.restaurante.model.Product;

import java.util.List;

public interface ProductDao {
    // create
    void add(Product user);

    //read
    List<Product> getAll();

    // find
    Product findById(int id);

    //delete
    void deleteById(int id);
}
