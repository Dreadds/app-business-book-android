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
        items.add(new Item("Sales", "sales", R.drawable.ic_local_atm_black_24dp));
        items.add(new Item("Purchases", "purchases", R.drawable.ic_add_shopping_cart_black_24dp));
        items.add(new Item("Products", "products", R.drawable.ic_gavel_black_24dp));
        items.add(new Item("Clients", "clients", R.drawable.ic_supervisor_account_black_24dp));
        items.add(new Item("Providers", "providers", R.drawable.ic_local_shipping_black_24dp));
        return this;
    }
}
