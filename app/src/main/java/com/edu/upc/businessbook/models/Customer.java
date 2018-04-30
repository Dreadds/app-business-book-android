package com.edu.upc.businessbook.models;

public class Customer {
    private String clienteId;
    private String name;
    private String lastName;
    private String fullName;
    private String dni;
    private String mail;
    private String phone;
    private String creationDate;
    private String updateDate;
    private String state;

    public Customer(String clienteId, String name, String lastName, String fullName, String dni, String mail, String phone, String creationDate, String updateDate, String state) {
        this.clienteId = clienteId;
        this.name = name;
        this.lastName = lastName;
        this.fullName = fullName;
        this.dni = dni;
        this.mail = mail;
        this.phone = phone;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.state = state;
    }

    public Customer() {
    }
}
