package com.edu.upc.businessbook.viewcontrollers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.models.Local;
import com.edu.upc.businessbook.models.LocalsRepository;
import com.edu.upc.businessbook.models.Producto;
import com.edu.upc.businessbook.network.BusinessBookApi;
import com.edu.upc.businessbook.viewcontrollers.adapters.LocalsAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView localsRecyclerView;
    private LocalsAdapter localsAdapter;
    //LinearLayoutManager layoutManager;
    private RecyclerView.LayoutManager localsLayoutManager;
    private List<Local> locals;



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        localsRecyclerView = view.findViewById(R.id.recycler_locals);
        locals = new ArrayList<>();
        localsAdapter = new LocalsAdapter(LocalsRepository.getInstance().getLocals());
        localsLayoutManager = new LinearLayoutManager(view.getContext());
        localsRecyclerView.setAdapter(localsAdapter);
        localsRecyclerView.setLayoutManager(localsLayoutManager);
        updateView();
        return view;
    }
    private void updateView(){

    }

}
