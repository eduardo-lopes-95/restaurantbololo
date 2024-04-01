package br.com.restaurante.model;

public class Restaurant {

    private int id;
    private String name;
    private int addressId;
    private String phone;

    public Restaurant(String name, int addressId, String phone, int id) {
        this.name = name;
        this.addressId = addressId;
        this.phone = phone;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

