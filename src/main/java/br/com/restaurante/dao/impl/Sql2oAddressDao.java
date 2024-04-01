package br.com.restaurante.dao.impl;

import br.com.restaurante.dao.AddressDao;
import br.com.restaurante.model.Address;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

public class Sql2oAddressDao implements AddressDao {

    private final Sql2o sql2o;

    public Sql2oAddressDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Address address) {
        String sql = "INSERT INTO addresses (id, street, neighborhood, city, zipCode, additionalInformation) " +
                     "VALUES (:id, :street, :neighborhood, :city, :zipCode, :additionalInformation)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(address)
                    .executeUpdate()
                    .getKey();
            address.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public Address findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM addresses WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Address.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from addresses WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
