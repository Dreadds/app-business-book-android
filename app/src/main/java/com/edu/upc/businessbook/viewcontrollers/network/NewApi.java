package com.edu.upc.businessbook.viewcontrollers.network;

public class NewApi {
    private static String BASE_URL = "http://chemita96-001-site1.dtempurl.com/";

<<<<<<< HEAD
    public static String getListSaleUrl(int companyId) { return BASE_URL + "businessbookapi/v1/companies/"  + String.valueOf(companyId) + "/sales"; }
    public static String postSale(){ return BASE_URL + "businessbookapi/v1/sales"; }
    public static String getListClient(int companyId){return BASE_URL + "businessbookapi/v1/companies/" + String.valueOf(companyId) + "/clients"; }
=======
    /*SALES*/
    public static String getListSaleUrl() { return BASE_URL + "businessbookapi/v1/sales"; }
    public static String postSale(){ return BASE_URL + "businessbookapi/v1/sales"; }

    /*Client*/
    public static String getListClientUrl(int companyId) { return BASE_URL + "businessbookapi/v1/companies/" + String.valueOf(companyId) + "/clients"; }

>>>>>>> dbf662a94f771a39caa9253941763a8fbd10d386
    public static String getPurchaselUrl() { return BASE_URL + "/purchases"; }
}
