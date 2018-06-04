package com.edu.upc.businessbook.models;

public class AddSaleModel {
    public String codeGuide;
    public int localId;
    public float priceTotal;
    public int EmployeeId;
    public int clientId;

    public AddSaleModel(String codeGuide, float priceTotal, int localId, int employeeId, int clientId) {

        this.codeGuide = codeGuide;
        this.priceTotal = priceTotal;
        this.localId = localId;
        EmployeeId = employeeId;
        this.clientId = clientId;
    }

    public AddSaleModel() {
    }

    public String getCodeGuide() {
        return codeGuide;
    }

    public AddSaleModel setCodeGuide(String codeGuide) {
        this.codeGuide = codeGuide;
        return this;
    }

    public float getPriceTotal() {
        return priceTotal;
    }

    public AddSaleModel setPriceTotal(float priceTotal) {
        this.priceTotal = priceTotal;
        return this;
    }

    public int getLocalId() {
        return localId;
    }

    public AddSaleModel setLocalId(int localId) {
        this.localId = localId;
        return this;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public AddSaleModel setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
        return this;
    }

    public int getClientId() {
        return clientId;
    }

    public AddSaleModel setClientId(int clientId) {
        this.clientId = clientId;
        return this;
    }
}
