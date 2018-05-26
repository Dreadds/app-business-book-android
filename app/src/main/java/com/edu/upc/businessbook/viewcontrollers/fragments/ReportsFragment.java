package com.edu.upc.businessbook.viewcontrollers.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.viewcontrollers.activities.SalesRepActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportsFragment extends Fragment {

    GridLayout dashboardGrid;

    public ReportsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmen
        View view = inflater.inflate(R.layout.fragment_reports, container, false);

        dashboardGrid = (GridLayout)view.findViewById(R.id.gridDashboard);
        setSingleEvent(dashboardGrid);
        return view;
    }

    private void setSingleEvent(GridLayout dashboardGrid) {
        for(int i=0;i<dashboardGrid.getChildCount();i++)
        {
            CardView cardViewDashboard = (CardView) dashboardGrid.getChildAt(i);
            final int finalI = i;
            cardViewDashboard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getActivity(),"Click index: "+finalI,Toast.LENGTH_SHORT).show();
                    if(finalI==3){
                       Intent intent = new Intent(getActivity(), SalesRepActivity.class);
                        getActivity().startActivity(intent);
                    }
                }
            });
        }
    }

}

