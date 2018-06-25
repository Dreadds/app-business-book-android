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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ComRepActivity extends AppCompatActivity {
    private BarChart chartComp;
    float barWidth;
    float barSpace;
    float groupSpace;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    String[] listItems;
    Bundle idlocal;
    Bundle dateB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_rep);
        chartComp = (BarChart)findViewById(R.id.barChartComp);

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

                DatePickerDialog monthDatePickerDialog = new DatePickerDialog(ComRepActivity.this,
                        android.app.AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mDisplayDate.setText(String.valueOf(year));
                        if(year==2018){
                            chartComp.setData(fillBarData(idlocal.getInt("Selected")));
                        }
                        else{
                            noDataBarChart();
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
        noDataBarChart();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_reports, menu);
        return true;
    }
    public void noDataBarChart(){
        chartComp.clear();
        chartComp.setNoDataText("No data available, please select a date");
    }

    public class MyYAxisValuesFormatter implements IAxisValueFormatter {
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

        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(ComRepActivity.this);
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
                noDataBarChart();
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
    public BarData fillBarData(int local){
        barWidth = 0.3f;
        barSpace = 0f;
        groupSpace = 0.4f;

        chartComp.setDescription(null);
        chartComp.setPinchZoom(true);
        chartComp.setScaleEnabled(false);
        chartComp.setDrawBarShadow(false);
        chartComp.setDrawGridBackground(false);
        chartComp.setDragEnabled(true);

        int grupCount =6;

        ArrayList xVals = new ArrayList();
        ArrayList yVals1 = new ArrayList();
        ArrayList yVals2 = new ArrayList();

        xVals.add("Apr");
        xVals.add("May");
        xVals.add("Jun");

        if(local==0){
            yVals1.clear();
            yVals2.clear();
            yVals1.add(new BarEntry(1, (float) 530));
            yVals2.add(new BarEntry(1, (float) 520));
            yVals1.add(new BarEntry(2, (float) 550));
            yVals2.add(new BarEntry(2, (float) 530));
            yVals1.add(new BarEntry(3, (float) 495));
            yVals2.add(new BarEntry(3, (float) 400));
        }
        if(local==1){
            yVals1.clear();
            yVals2.clear();
            yVals1.add(new BarEntry(1, (float) 120));
            yVals2.add(new BarEntry(1, (float) 110));
            yVals1.add(new BarEntry(2, (float) 110));
            yVals2.add(new BarEntry(2, (float) 120));
            yVals1.add(new BarEntry(3, (float) 126));
            yVals2.add(new BarEntry(3, (float) 110));
        }
        if(local==2){
            yVals1.clear();
            yVals2.clear();
            yVals1.add(new BarEntry(1, (float) 125));
            yVals2.add(new BarEntry(1, (float) 90));
            yVals1.add(new BarEntry(2, (float) 127));
            yVals2.add(new BarEntry(2, (float) 120));
            yVals1.add(new BarEntry(3, (float) 130));
            yVals2.add(new BarEntry(3, (float) 110));
        }

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
        lefAxis.setValueFormatter(new MyYAxisValuesFormatter());
        lefAxis.setDrawGridLines(true);
        lefAxis.setSpaceTop(35f);
        lefAxis.setAxisMinimum(0f);

        return data;
    }
}
