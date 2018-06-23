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

    public String getClienteId() {
        return clienteId;
    }

    public Customer setClienteId(String clienteId) {
        this.clienteId = clienteId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Customer setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public Customer setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getDni() {
        return dni;
    }

    public Customer setDni(String dni) {
        this.dni = dni;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public Customer setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Customer setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public Customer setCreationDate(String creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public Customer setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public String getState() {
        return state;
    }

    public Customer setState(String state) {
        this.state = state;
        return this;
    }
}
