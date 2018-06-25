package com.edu.upc.businessbook.viewcontrollers.network;

import android.view.View;

public class NewApi {
    private static String BASE_URL = "http://chemita96-001-site1.dtempurl.com/businessbookapi/v1";


    public static String getSaleUrl() {
        return BASE_URL + "/sales";
    }

    public static String getPurchaselUrl() {
        return BASE_URL + "/purchases";
    }


    //GET
    public static String getListSaleUrl(int companyId) { return BASE_URL + "/companies/"  + String.valueOf(companyId) + "/sales"; }
    public static String getListClient(int companyId){return BASE_URL + "/companies/" + String.valueOf(companyId) + "/clients"; }
    public static String getListLocal(int companyId){return BASE_URL + "/companies/" + String.valueOf(companyId) + "/locals"; }
    public static String getListProduct(int companyId){return BASE_URL + "/companies/" + String.valueOf(companyId) + "/products"; }
    public static String getDataProfileUrl(int id) { return BASE_URL + "/companies/" + String.valueOf(id); }
    public static String getLocationUrl(){
        return BASE_URL+ "/locations";
    }

    //POST
    public static String postSale(){ return BASE_URL + "/sales"; }
    public static String postSaleDetail(int saleId){ return BASE_URL + "/sales/" + String.valueOf(saleId) +"/items"; }
    public static String postUserRegisterUrl(){ return BASE_URL + "/registeremployee"; }
    public static String postUserLoginUrl(){ return BASE_URL + "/login"; }
    public static String postDataProfileUrl() {
        return BASE_URL + "/companies";
    }

    //PUT
    public static String putDataProfileUrl(){ return BASE_URL + "/companies"; }

}
