package br.com.restaurante.dao;

import br.com.restaurante.model.Address;
import br.com.restaurante.model.Client;

import java.util.List;

public interface AddressDao {
    // create
    void add(Address user);

    List<Address> getAll();

    // find
    Address findById(int id);

    //delete
    void deleteById(int id);
}
