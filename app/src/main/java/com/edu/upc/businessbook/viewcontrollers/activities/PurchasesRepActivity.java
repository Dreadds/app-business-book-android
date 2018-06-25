package com.edu.upc.businessbook.viewcontrollers.activities;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

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
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class PurchasesRepActivity extends AppCompatActivity {

    private LineChart pChart;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    String[] listItems;
    Bundle idlocal;
    Bundle dateB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchases_rep);

        pChart = (LineChart)findViewById(R.id.lineChart);
        idlocal = new Bundle();
        idlocal.putInt("Selected",0);
        TextView text = (TextView) findViewById(R.id.titleText);
        dateB = new Bundle();

        mDisplayDate = (TextView)findViewById(R.id.dateText);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                dateB.putInt("year", cal.get(Calendar.YEAR));
                dateB.putInt("month",Calendar.MONTH );
                dateB.putInt("day",cal.get(Calendar.DAY_OF_MONTH) );

                DatePickerDialog monthDatePickerDialog = new DatePickerDialog(PurchasesRepActivity.this,
                        android.app.AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mDisplayDate.setText(String.valueOf(year));
                        if(year==2018){
                            pChart.setData(fillLineData(idlocal.getInt("Selected")));
                        }
                        else{
                            NoDataLineChart();
                        }
                    }
                }, dateB.getInt("year"), dateB.getInt("month"), dateB.getInt("day")){
                    @Override
                    protected void onCreate(Bundle savedInstanceState) {
                        super.onCreate(savedInstanceState);
                        getDatePicker().findViewById(getResources().getIdentifier("day","id","android")).setVisibility(View.GONE);
                        getDatePicker().findViewById(getResources().getIdentifier("month","id","android")).setVisibility(View.GONE);
                    }
                };
                monthDatePickerDialog.show();
            }
        });
        NoDataLineChart();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_reports, menu);
        return true;
    }
    public void NoDataLineChart(){
        pChart.clear();
        pChart.setNoDataText("No data available, please select a date");
    }

    public class MyXAxisValueFormatter implements IAxisValueFormatter{
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


    public AlertDialog.Builder localDialog(){
        listItems = new String[] {"Av. Salaverry 342", "Av. La Marina 879", "Jr. Cuzco 598"};

        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(PurchasesRepActivity.this);
        mBuilder.setTitle("Choose an local");
        mBuilder.setIcon(R.drawable.ic_action_local_dialog);
        mBuilder.setSingleChoiceItems(listItems,idlocal.getInt("Selected"), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i==0)
                    idlocal.putInt("Selected", 0);
                if (i==1)
                    idlocal.putInt("Selected", 1);
                if (i==2)
                    idlocal.putInt("Selected", 2);

                mDisplayDate.setText(" ----");
                NoDataLineChart();
            }
        });
        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return mBuilder;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.action_location:
                AlertDialog mDialog = localDialog().create();
                mDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public LineData fillLineData(int local){
        pChart.setDragEnabled(true);
        pChart.setScaleEnabled(false);

        ArrayList<Entry> yValue = new ArrayList<>();

        if(local==0){
            yValue.clear();
            yValue.add(new Entry(0,520f));
            yValue.add(new Entry(1,530f));
            yValue.add(new Entry(2,400f));
        }
        if(local==1){
            yValue.clear();
            yValue.add(new Entry(0,110f));
            yValue.add(new Entry(1,120f));
            yValue.add(new Entry(2,110f));
        }
        if(local==2){
            yValue.clear();
            yValue.add(new Entry(0,90f));
            yValue.add(new Entry(1,120f));
            yValue.add(new Entry(2,110f));
        }

        LineDataSet setPurchases = new LineDataSet(yValue, "Data Purchases");

        setPurchases.setFillAlpha(110);
        setPurchases.setColor(Color.RED);
        setPurchases.setLineWidth(3f);
        setPurchases.setValueTextSize(10f);
        setPurchases.setValueFormatter(new MyValueFormatter());

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(setPurchases);

        LineData data = new LineData(dataSets);
        data.setValueFormatter(new MyValueFormatter());

       // pChart.setData(data);
        pChart.getAxisRight().setEnabled(false);

        String[]values = new String[]{"Apr","May", "Jun"};

        XAxis xAxis = pChart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter(values));

        YAxis yAxis = pChart.getAxisLeft();
        yAxis.setValueFormatter(new MyYAxisValuesFormatter());

        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        return data;
    }
}

