package br.com.restaurante.dao;

import br.com.restaurante.model.Address;
import br.com.restaurante.model.Client;

import java.util.List;

public interface ClientDao {
    // create
    void add(Client user);

    // find
    Client findById(int id);

    List<Client> getAll();

    //delete
    void deleteById(int id);
}
