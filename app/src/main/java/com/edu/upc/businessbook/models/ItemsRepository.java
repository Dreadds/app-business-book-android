package com.edu.upc.businessbook.models;

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
        items.add(new Item("Sales", "sales"));
        items.add(new Item("Purchases", "purchases"));
        items.add(new Item("Sales", "sales"));
        items.add(new Item("Sales", "sales"));
        items.add(new Item("Sales", "sales"));
        return this;
    }
}
