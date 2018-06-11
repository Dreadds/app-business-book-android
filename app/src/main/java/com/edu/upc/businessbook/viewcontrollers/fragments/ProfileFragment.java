package com.edu.upc.businessbook.viewcontrollers.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.models.CompanyEntity;
import com.edu.upc.businessbook.viewcontrollers.activities.ProfileActivity;
import com.edu.upc.businessbook.viewcontrollers.network.NewApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private TextView nameTexview;
    private TextView adressTextview;
    private TextView emailTextview;
    private TextView phoneTextview;
    private TextView mobileTextview;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        nameTexview = view.findViewById(R.id.text_company_name);
        adressTextview = view.findViewById(R.id.text_address);
        emailTextview = view.findViewById(R.id.text_email);
        phoneTextview = view.findViewById(R.id.text_phone);
        mobileTextview = view.findViewById(R.id.text_mobile);

        getProfile();

        Button btnsave = (Button) view.findViewById(R.id.button_update);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void getProfile() {
        String token = "Bearer kVFso18dz-0sHn3llcSrdzqt11om7EQfCwhKL0DB2MluD0XjYJkzoRnoj9QeoyjChwg82mk5D6o16Mp9x0dXuBifXUcEGdZhDB1c0s0JrDsndGVW2he1pcI5bK_V1Pk5yKgAJ6GjOjn0l9OF9-5Ooy5iNeUOmRF9XI-0kNdAc0nsghAAF5A9Xc67QaiN6--mrmuL7yS2fJL2FOg-scRxD-0SM1PMbOyG6KqOcsj-SGJJW4KQ8oyXI4yMoNl0jYgN2Pf8k2H72QQonzDHoAAWeg";
        int id = 5;
        AndroidNetworking
                .get(NewApi.getDataProfileUrl(id))
                .addHeaders("Authorization", token)
                .setPriority(Priority.LOW)
                .setTag("businessbook")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if ("200".equalsIgnoreCase(response.getString("Code"))) {
                                JSONArray ja = response.getJSONArray("Result");

                                CompanyEntity companyEntity = new CompanyEntity(ja.getJSONObject(0).getInt("companyId"),
                                        ja.getJSONObject(0).getString("name"), ja.getJSONObject(0).getString("address"), ja.getJSONObject(0).getString("email"),
                                        ja.getJSONObject(0).getString("phone"), ja.getJSONObject(0).getString("mobile"));
                                nameTexview.setText(companyEntity.getName());
                                adressTextview.setText(companyEntity.getAddress());
                                emailTextview.setText(companyEntity.getEmail());
                                phoneTextview.setText(companyEntity.getPhone());
                                mobileTextview.setText(companyEntity.getMobile());

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("CatchUp", anError.getErrorDetail());

                    }
                });
    }

}
