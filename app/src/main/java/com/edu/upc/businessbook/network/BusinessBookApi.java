package com.edu.upc.businessbook.network;

public class BusinessBookApi {
    private  static String BASE_URL = "http://chemita96-001-site1.dtempurl.com/businessbookapi/v1";

    public static String getProductsUrl(){
        return BASE_URL + "/products";
    }
    public static String getDataProfileUrl(int id){return BASE_URL+ "/companies/"+ String.valueOf(id) ;  }
    public static String postDataProfileUrl(){return BASE_URL+ "/companies" ;  }
}
