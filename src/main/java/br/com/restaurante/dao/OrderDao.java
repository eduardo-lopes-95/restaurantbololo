package br.com.restaurante.dao;

import br.com.restaurante.model.Client;
import br.com.restaurante.model.Order;

import java.util.List;

public interface OrderDao {
    // create
    void add(Client user);

    //read
    List<Order> getAll();

    // find
    Client findById(int id);

    //delete
    void deleteById(int id);
}
