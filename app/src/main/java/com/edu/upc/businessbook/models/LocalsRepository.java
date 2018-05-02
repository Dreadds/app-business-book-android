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
        locals.add( new Local("Local 1", "Av. Marina", R.drawable.ic_business_black_24dp));
        locals.add( new Local("Local 2", "Av. La Paz",  R.drawable.ic_business_black_24dp));
        locals.add( new Local("Local 3", "Av. Salaverry",  R.drawable.ic_business_black_24dp));
        return this;
    }
}
