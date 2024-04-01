package br.com.restaurante.dao;

import br.com.restaurante.model.Order;

import java.util.List;

public interface OrderDao {
    // create
    void add(Order order);

    //read
    List<Order> getAll();

    // find
    Order findById(int id);

    //delete
    void deleteById(int id);
}
