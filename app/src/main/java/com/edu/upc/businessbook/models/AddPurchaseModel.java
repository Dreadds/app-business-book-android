package com.edu.upc.businessbook.models;

public class AddPurchaseModel {

    public String codeGuide;
    public int localId;
    public int providerId;
    public int companyId;

    public AddPurchaseModel() {
    }

    public AddPurchaseModel(String codeGuide, int localId, int providerId, int companyId) {
        this.codeGuide = codeGuide;
        this.localId = localId;
        this.providerId = providerId;
        this.companyId = companyId;
    }

    public String getCodeGuide() {
        return codeGuide;
    }

    public AddPurchaseModel setCodeGuide(String codeGuide) {
        this.codeGuide = codeGuide;
        return this;
    }

    public int getLocalId() {
        return localId;
    }

    public AddPurchaseModel setLocalId(int localId) {
        this.localId = localId;
        return this;
    }

    public int getProviderId() {
        return providerId;
    }

    public AddPurchaseModel setProviderId(int providerId) {
        this.providerId = providerId;
        return this;
    }

    public int getCompanyId() {
        return companyId;
    }

    public AddPurchaseModel setCompanyId(int companyId) {
        this.companyId = companyId;
        return this;
    }
}
