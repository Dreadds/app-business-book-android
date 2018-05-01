package com.edu.upc.businessbook.models;

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
        locals.add( new Local("Local 1", "Av. Marina"));
        locals.add( new Local("Local 2", "Av. La Paz"));
        locals.add( new Local("Local 3", "Av. Salaverry"));
        return this;
    }
}
