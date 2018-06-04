package com.edu.upc.businessbook.network;

public class BusinessBookApi {
    private  static String BASE_URL = "http://chemita96-001-site1.dtempurl.com/businessbookapi";

    public static String getProductsUrl(){
        return BASE_URL + "/products";
    }
}
