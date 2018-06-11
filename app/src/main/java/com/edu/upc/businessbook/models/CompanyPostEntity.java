package com.edu.upc.businessbook.models;

public class CompanyPostEntity {
    private String name;
    private String address;
    private String email;
    private String phone;
    private String mobile;

    public CompanyPostEntity(String name, String address, String email, String phone, String mobile) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.mobile = mobile;
    }

    public CompanyPostEntity() {

    }

    public String getName() {
        return name;
    }

    public CompanyPostEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public CompanyPostEntity setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CompanyPostEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CompanyPostEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public CompanyPostEntity setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }
}
