package com.edu.upc.businessbook.models;

import com.edu.upc.businessbook.R;

import java.util.ArrayList;
import java.util.List;

public class ItemsRepository {
    private static ItemsRepository instance;
    private List<Item> items;

    public static ItemsRepository getInstance(){
        if(instance == null) instance = new ItemsRepository();
        return instance;
    }

    public List<Item> getItems() {
        if (items == null) init();
        return items;
    }

    private ItemsRepository init() {
        items = new ArrayList<>();
        items.add(new Item("Sales", "sales", R.drawable.sales));
        items.add(new Item("Purchases", "purchases", R.drawable.purchases));
        items.add(new Item("Products", "products", R.drawable.products));
        items.add(new Item("Clients", "clients", R.drawable.client));
        items.add(new Item("Providers", "providers", R.drawable.providers));
        return this;
    }
}
