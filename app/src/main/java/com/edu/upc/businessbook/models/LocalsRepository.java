package com.edu.upc.businessbook.models;

import com.edu.upc.businessbook.R;

import java.util.ArrayList;
import java.util.List;

public class LocalsRepository {
    private static LocalsRepository instance;
    private List<Local> locals;

    public static LocalsRepository getInstance(){
        if(instance == null) instance = new LocalsRepository();
        return instance;
    }

    public List<Local> getLocals() {
        if (locals == null) init();
        return locals;
    }

    private LocalsRepository init(){
        locals = new ArrayList<>();
        locals.add( new Local("Local 1", "Av. Marina","ACT", R.drawable.img_marketplace_logo));
        locals.add( new Local("Local 2", "Av. La Paz", "ACT",  R.drawable.img_marketplace_logo));
        locals.add( new Local("Local 3", "Av. Salaverry", "ACT",  R.drawable.img_marketplace_logo));
        return this;
    }
}
