package com.edu.upc.businessbook;

import com.edu.upc.businessbook.models.SaleDetail;
import com.edu.upc.businessbook.models.SaleDetailEntity;

import java.util.List;

public class Modelo {
    public List<SaleDetailEntity> listSaleDetail ;

    public Modelo(List<SaleDetailEntity> listSaleDetail) {
        this.listSaleDetail = listSaleDetail;
    }
}
