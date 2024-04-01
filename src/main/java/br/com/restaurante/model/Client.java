package br.com.restaurante.model;

public class Client {
    private int id;

    private String name;

    private String document;

    public Client(int id, String name, String document) {
        this.id = id;
        this.name = name;
        this.document = document;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
