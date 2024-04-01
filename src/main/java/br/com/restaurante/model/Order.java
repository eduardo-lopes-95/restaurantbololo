package br.com.restaurante.model;

import java.time.LocalDate;

public class Order {
    private int id;

    private int clientId;

    private int addressId; //será usado para conectar o endereco de entrega (um para um)

    private LocalDate orderDate;

    private OrderStatus orderStatus;

    public Order(int id, int clientId, int addressId, LocalDate orderDate, OrderStatus orderStatus) {
        this.id = id;
        this.clientId = clientId;
        this.addressId = addressId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
