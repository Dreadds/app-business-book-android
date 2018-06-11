package com.edu.upc.businessbook.models;

public class AddSaleModel {

    public String codeGuide;
    public int localId;
    public int clientId;
    public int companyId;

    public AddSaleModel() {
    }

    public AddSaleModel(String codeGuide, int localId, int clientId, int companyId) {
        this.codeGuide = codeGuide;
        this.localId = localId;
        this.clientId = clientId;
        this.companyId = companyId;
    }

    public String getCodeGuide() {
        return codeGuide;
    }

    public void setCodeGuide(String codeGuide) {
        this.codeGuide = codeGuide;
    }

    public int getLocalId() {
        return localId;
    }

    public void setLocalId(int localId) {
        this.localId = localId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
