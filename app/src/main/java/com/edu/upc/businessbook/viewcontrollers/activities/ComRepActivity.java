package com.edu.upc.businessbook.viewcontrollers.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.edu.upc.businessbook.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;

import java.util.ArrayList;

public class ComRepActivity extends AppCompatActivity {
    private BarChart chartComp;
    float barWidth;
    float barSpace;
    float groupSpace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_rep);

        barWidth = 0.3f;
        barSpace = 0f;
        groupSpace = 0.4f;

        chartComp = (BarChart)findViewById(R.id.barChartComp);
        chartComp.setDescription(null);
        chartComp.setPinchZoom(true);
        chartComp.setScaleEnabled(false);
        chartComp.setDrawBarShadow(false);
        chartComp.setDrawGridBackground(false);

        int grupCount =6;

        ArrayList xVals = new ArrayList();
        xVals.add("Jan");
        xVals.add("Feb");
        xVals.add("Mar");
        xVals.add("Apr");
        xVals.add("May");
        xVals.add("Jun");

        ArrayList yVals1 = new ArrayList();
        ArrayList yVals2 = new ArrayList();

        yVals1.add(new BarEntry(1, (float) 5));
        yVals2.add(new BarEntry(1, (float) 8));
        yVals1.add(new BarEntry(2, (float) 9));
        yVals2.add(new BarEntry(2, (float) 7));
        yVals1.add(new BarEntry(3, (float) 10));
        yVals2.add(new BarEntry(3, (float) 4));
        yVals1.add(new BarEntry(4, (float) 8));
        yVals2.add(new BarEntry(4, (float) 5));
        yVals1.add(new BarEntry(5, (float) 13));
        yVals2.add(new BarEntry(5, (float) 5));
        yVals1.add(new BarEntry(6, (float) 6));
        yVals2.add(new BarEntry(6, (float) 2));

        BarDataSet set1, set2;
        set1 = new BarDataSet(yVals1,"Sales");
        set1.setColor(Color.CYAN);
        set2 = new BarDataSet(yVals2,"Purchases");
        set2.setColor(Color.DKGRAY);

        BarData data = new BarData(set1,set2);
        data.setValueFormatter(new LargeValueFormatter());
        chartComp.setData(data);
        chartComp.getBarData().setBarWidth(barWidth);
        chartComp.getXAxis().setAxisMinimum(0);
        chartComp.getXAxis().setAxisMaximum(0 + chartComp.getBarData().getGroupWidth(groupSpace, barSpace)* grupCount);
        chartComp.groupBars(0, groupSpace ,barSpace);

        //X - Axis
        XAxis xAxis = chartComp.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMaximum(6);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));

        //Y - Axis
        chartComp.getAxisRight().setEnabled(false);
        YAxis lefAxis = chartComp.getAxisLeft();
        lefAxis.setValueFormatter(new LargeValueFormatter());
        lefAxis.setDrawGridLines(true);
        lefAxis.setSpaceTop(35f);
        lefAxis.setAxisMinimum(0f);
    }
}
