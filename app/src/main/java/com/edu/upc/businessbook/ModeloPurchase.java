package com.edu.upc.businessbook;

import com.edu.upc.businessbook.models.PurchaseDetailEntity;
import com.edu.upc.businessbook.models.SaleDetailEntity;

import java.util.List;

public class ModeloPurchase {
    public List<PurchaseDetailEntity> listPurchaseDetail ;

    public ModeloPurchase(List<PurchaseDetailEntity> listPurchaseDetail) {
        this.listPurchaseDetail = listPurchaseDetail;
    }
}
