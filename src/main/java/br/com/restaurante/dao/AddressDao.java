package br.com.restaurante.dao;

import br.com.restaurante.model.Address;

public interface AddressDao {
    // create
    void add(Address user);

    // find
    Address findById(int id);

    //delete
    void deleteById(int id);
}
