package com.edu.upc.businessbook.viewcontrollers.network;

public class NewApi {
    private static String BASE_URL = "http://chemita96-001-site1.dtempurl.com/businessbookapi/v1";

    public static String getSaleUrl() { return BASE_URL + "/sales"; }
    public static String getPurchaselUrl() { return BASE_URL + "/purchases"; }
    public static String getDataProfileUrl(int id){return BASE_URL+ "/companies/"+ String.valueOf(id) ;  }
    public static String postDataProfileUrl(){return BASE_URL+ "/companies" ;  }
}
