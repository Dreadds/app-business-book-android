package com.edu.upc.businessbook.viewcontrollers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.models.Item;
import com.edu.upc.businessbook.models.ItemsRepository;
import com.edu.upc.businessbook.viewcontrollers.adapters.ItemsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment {
    private RecyclerView itemsRecyclerView;
    private ItemsAdapter itemsAdapter;
    //LinearLayoutManager layoutManager;
    private RecyclerView.LayoutManager itemsLayoutManager;
    private List<Item> items;


    public ItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        itemsRecyclerView = view.findViewById(R.id.recycler_items);
        items = new ArrayList<>();
        itemsAdapter = new ItemsAdapter(ItemsRepository.getInstance().getItems());
        itemsLayoutManager = new GridLayoutManager(view.getContext(), 2);
        itemsRecyclerView.setAdapter(itemsAdapter);
        itemsRecyclerView.setLayoutManager(itemsLayoutManager);
        return view;
    }

}
