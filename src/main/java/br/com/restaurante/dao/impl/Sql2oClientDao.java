package br.com.restaurante.dao.impl;

import br.com.restaurante.dao.ClientDao;
import br.com.restaurante.model.Client;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oClientDao implements ClientDao {

    private final Sql2o sql2o;

    public Sql2oClientDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Client client) {
        String sql = "INSERT INTO clients (name, document) VALUES (:name, :document)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(client)
                    .executeUpdate()
                    .getKey();
            client.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public Client findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM clients WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Client.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from clients WHERE id=:id";

        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();

        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Client> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM clients")
                    .executeAndFetch(Client.class);
        }
    }
}
