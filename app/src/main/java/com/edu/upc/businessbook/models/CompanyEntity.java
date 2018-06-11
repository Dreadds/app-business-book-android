package com.edu.upc.businessbook.models;

public class CompanyEntity {
    private int companyId;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String mobile;


    public CompanyEntity(int companyid, String name, String address, String email, String phone, String mobile) {
        this.companyId = companyid;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.mobile = mobile;
    }

    public CompanyEntity() {
    }

    public int getCompanyid() {
        return companyId;
    }

    public CompanyEntity setCompanyid(int companyid) {
        this.companyId = companyid;
        return this;
    }

    public String getName() {
        return name;
    }

    public CompanyEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public CompanyEntity setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CompanyEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CompanyEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public CompanyEntity setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }
}
