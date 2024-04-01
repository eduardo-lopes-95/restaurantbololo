package br.com.restaurante.dao;

import br.com.restaurante.model.Address;
import br.com.restaurante.model.Client;

public interface ClientDao {
    // create
    void add(Client user);

    // find
    Client findById(int id);

    //delete
    void deleteById(int id);
}
