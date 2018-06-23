package com.edu.upc.businessbook.viewcontrollers.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.edu.upc.businessbook.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SalesRepActivity extends AppCompatActivity {

    private LineChart pChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_rep);


        pChart = (LineChart)findViewById(R.id.lineChartSales);

        // pChart.setOnChartGestureListener(PurchasesRepActivity.this);
        // pChart.setOnChartValueSelectedListener(PurchasesRepActivity.this);

        pChart.setDragEnabled(true);
        pChart.setScaleEnabled(false);

        ArrayList<Entry> yValue = new ArrayList<>();
        yValue.add(new Entry(0,45f));
        yValue.add(new Entry(1,35f));
        yValue.add(new Entry(2,55f));
        yValue.add(new Entry(3,20f));
        yValue.add(new Entry(4,70f));
        yValue.add(new Entry(5,60f));
        yValue.add(new Entry(6,65f));

        LineDataSet setPurchases = new LineDataSet(yValue, "Data Sales");

        setPurchases.setFillAlpha(110);
        setPurchases.setColor(Color.RED);
        setPurchases.setLineWidth(3f);
        setPurchases.setValueTextSize(10f);
        setPurchases.setValueFormatter(new MyValueFormatter());

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(setPurchases);

        LineData data = new LineData(dataSets);
        data.setValueFormatter(new MyValueFormatter());

        pChart.setData(data);
        pChart.getAxisRight().setEnabled(false);

        String[]values = new String[]{"Jan","Feb","Mar","Apr","May", "Jun", "Jul"};

        XAxis xAxis = pChart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter(values));

        YAxis yAxis = pChart.getAxisLeft();
        yAxis.setValueFormatter(new MyYAxisValuesFormatter());

        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
    }

    public class MyXAxisValueFormatter implements IAxisValueFormatter {
        private String[] xValues;
        public MyXAxisValueFormatter(String[] values){
            this.xValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return xValues[(int)value];
        }
    }

    public class MyYAxisValuesFormatter implements IAxisValueFormatter{
        private DecimalFormat mFormat;

        public  MyYAxisValuesFormatter(){
            mFormat = new DecimalFormat("###,###,##0.0");
        }
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return "S/." + mFormat.format(value);
        }
    }

    public class MyValueFormatter implements IValueFormatter {
        private DecimalFormat mFormat;

        public  MyValueFormatter(){
            mFormat = new DecimalFormat("###,###,##0.0");
        }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return "S/." + mFormat.format(value);
        }
    }
}
