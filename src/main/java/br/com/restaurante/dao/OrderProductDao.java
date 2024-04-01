package br.com.restaurante.dao;

import br.com.restaurante.model.Order;
import br.com.restaurante.model.OrderProduct;
import br.com.restaurante.model.Product;

import java.util.List;

public interface OrderProductDao {

    // create
    void add(OrderProduct orderProduct);

    //read
    List<OrderProduct> getAll();

    // find
    List<Product> findAllByOrderId(int orderId);

    // find
    List<Order> findAllByProductId(int productId);

    //delete
    void deleteByOrderId(int orderId);
}
