package com.edu.upc.businessbook.network;

public class BusinessBookApi {
    private  static String BASE_URL = "http://chemita96-001-site1.dtempurl.com/";

    public static String getProductsUrl(){
        return BASE_URL + "/products";
    }

    public static String getLocalsUrl(int companyId)
        {return BASE_URL + "businessbookapi/v1/companies/" + String.valueOf(companyId) + "/locals"; }

}
