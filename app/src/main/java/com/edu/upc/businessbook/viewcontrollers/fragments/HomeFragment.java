package com.edu.upc.businessbook.viewcontrollers.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
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
import com.edu.upc.businessbook.network.BusinessBookApi;
import com.edu.upc.businessbook.viewcontrollers.adapters.LocalsAdapter;
import com.edu.upc.businessbook.viewcontrollers.dialogs.DialogPersonalized;

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
        //localsAdapter = new LocalsAdapter(LocalsRepository.getInstance().getLocals());
        localsAdapter = new LocalsAdapter(locals);
        localsLayoutManager = new LinearLayoutManager(view.getContext());
        localsRecyclerView.setAdapter(localsAdapter);
        localsRecyclerView.setLayoutManager(localsLayoutManager);
        getListLocals(1);
        FloatingActionButton localFab = (FloatingActionButton) view.findViewById(R.id.localFab);
        localFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DialogPersonalized();
                dialogFragment.show(getFragmentManager(), "Dialog");
            }
        });
        return view;
    }
    private void getListLocals(int companyId){
        AndroidNetworking.get(BusinessBookApi.getLocalsUrl(companyId))
                .addHeaders("Authorization","Bearer x_5nQgRHNmLQB9j2ftuindO37XFafd3C2TV2muPbvOvjiyl9IUwU7RgJQ0sK86GYnOCt9akkgcmmor_3eU0tS8xSfGDA1KIkXvViwy2ifmOYvMvHVvj-pP_BRSSXYo5IpjptUpWMpKLhDQphN0VLdq7irRfHHcqZNrMf9IGoMPxbfDO62tWeLzG7JPogUOsFwe1GMN-YqtTADgtTV2o3tuPnwCYbijHN0J-bVxB2BnTBg6fSKKspADTwSIIbERaRV5NeT09nT6V6xZ4796UoZ3LSJcRkwtatLrg4Bj8hQnM")
                .setPriority(Priority.LOW)
                .setTag("businessbook")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response != null){
                                locals = Local.Builder.from(response.getJSONArray("Result")).buildAll();
                                localsAdapter.setLocals(locals);
                                localsAdapter.notifyDataSetChanged();
                                Log.d("businessbook", String.format("Locals Count: %d", locals.size()));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }


}
