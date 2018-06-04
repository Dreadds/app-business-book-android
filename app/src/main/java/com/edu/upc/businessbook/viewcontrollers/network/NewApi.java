package com.edu.upc.businessbook.viewcontrollers.network;

public class NewApi {
    private static String BASE_URL = "http://chemita96-001-site1.dtempurl.com/";

    public static String getListSaleUrl() { return BASE_URL + "businessbookapi/v1/sales"; }
    public static String postSale(){ return BASE_URL + "businessbookapi/v1/sales"; }
    public static String getPurchaselUrl() { return BASE_URL + "/purchases"; }
}
