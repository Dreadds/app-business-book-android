package com.edu.upc.businessbook.viewcontrollers.network;

public class NewApi {
    private static String BASE_URL = "http://chemita96-001-site1.dtempurl.com/businessbookapi/v1";

    public static String getListSaleUrl() { return BASE_URL + "/sales"; }
    public static String getPurchaselUrl() { return BASE_URL + "/purchases"; }
}
