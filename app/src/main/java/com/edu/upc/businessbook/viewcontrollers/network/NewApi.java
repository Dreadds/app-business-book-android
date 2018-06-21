package com.edu.upc.businessbook.viewcontrollers.network;

import android.view.View;

public class NewApi {
    private static String BASE_URL = "http://chemita96-001-site1.dtempurl.com/";
    //GET
    public static String getListSaleUrl(int companyId) { return BASE_URL + "businessbookapi/v1/companies/"  + String.valueOf(companyId) + "/sales"; }
    public static String getListClient(int companyId){return BASE_URL + "businessbookapi/v1/companies/" + String.valueOf(companyId) + "/clients"; }
    public static String getListLocal(int companyId){return BASE_URL + "businessbookapi/v1/companies/" + String.valueOf(companyId) + "/locals"; }
    public static String getListProduct(int companyId){return BASE_URL + "businessbookapi/v1/companies/" + String.valueOf(companyId) + "/products"; }


    //POST
    public static String postSale(){ return BASE_URL + "businessbookapi/v1/sales"; }
    public static String postSaleDetail(int saleId){ return BASE_URL + "businessbookapi/v1/sales/" + String.valueOf(saleId) +"/items"; }
    public static String getPurchaselUrl() { return BASE_URL + "/purchases"; }
}
