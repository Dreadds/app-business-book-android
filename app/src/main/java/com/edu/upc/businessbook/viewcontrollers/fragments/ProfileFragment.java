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

        Button update = (Button) view.findViewById(R.id.button_update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void getProfile() {
        String token = "Bearer bAIzKTyzdDVBoN2IKF2ly9g_fMYFY6gtZk_3UOEh5jO44_u-ng9-Hru7-muEFY09ftcHFd0nWwZQ1cByOMI9vrCYKtwmU3Ozpe94kt1Wd9i-Wegxd2Urcowo6n6nzU4F1B_sCwbwc8tSGLG0Vga3-EITHnUy0h12fZxb9PDf5gzbrMtYl_uM5blrago12-qqY9F1yVUSGRUIU_L2YHwrxvNNeCrDPZo1upi8jx2Gses2NN7jNqkHkevwQOapnsrWtEKAtnWibXICI6fSd9VSjw";
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
