package com.edu.upc.businessbook.models;

public class CompanyPostEntity {
    private int companyId;
    private String name;
    private int locationId;
    private String email;
    private String phone;
    private String mobile;
    private int employeeId;
    public CompanyPostEntity() {

    }

    public CompanyPostEntity(int companyId, String name, int locationId, String email, String phone, String mobile, int employeeId) {
        this.companyId = companyId;
        this.name = name;
        this.locationId = locationId;
        this.email = email;
        this.phone = phone;
        this.mobile = mobile;
        this.employeeId = employeeId;
    }

    public CompanyPostEntity(String name, int id, String email, String phone, String mobile, int employeeId) {
        this.name = name;
        this.locationId = id;
        this.email = email;
        this.phone = phone;
        this.mobile = mobile;
        this.employeeId = employeeId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public CompanyPostEntity setCompanyId(int companyId) {
        this.companyId = companyId;
        return this;
    }

    public String getName() {
        return name;
    }

    public CompanyPostEntity setName(String name) {
        this.name = name;
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
    public int getEmployeeId(){
        return employeeId;
    }

    public CompanyPostEntity setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
        return this;
    }
    public int getLocationlId(){
        return locationId;
    }

    public CompanyPostEntity setLocationId(int locationId) {
        this.locationId = locationId;
        return this;
    }
}
