package com.edu.upc.businessbook.network;

public class BusinessBookApi {

<<<<<<< HEAD
    private  static String BASE_URL = "http://chemita96-001-site1.dtempurl.com/businessbookapi/v1";
    public static String getProductsUrl(){
        return BASE_URL + "/products";
=======
    public static String getProductsUrl(int companyId){

        return BASE_URL + "businessbookapi/v1/companies/" + String.valueOf(companyId) + "/products";
>>>>>>> kevin
    }
    public static String getDataProfileUrl(int id){return BASE_URL+ "/companies/"+ String.valueOf(id) ;  }
    public static String postDataProfileUrl(){return BASE_URL+ "/companies" ;  }
    public static String getLocalsUrl(int companyId)
<<<<<<< HEAD
    {return BASE_URL + "/companies/" + String.valueOf(companyId) + "/locals"; }
    public static String getProvidersUrl(int companyId)
    {return BASE_URL + "/companies/" + String.valueOf(companyId) + "/providers"; }
=======
        {return BASE_URL + "businessbookapi/v1/companies/" + String.valueOf(companyId) + "/locals"; }

    public static String getProvidersUrl(int companyId) {
        return BASE_URL + "businessbookapi/v1/companies/" + String.valueOf(companyId) + "/providers"; }

    public static String getClientsUrl(int companyId) {
        return BASE_URL + "businessbookapi/v1/companies/" + String.valueOf(companyId) + "/clients"; }
>>>>>>> kevin
}
