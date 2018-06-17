package com.edu.upc.businessbook.viewcontrollers.activities;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.upc.businessbook.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class TopProdRepActivity extends AppCompatActivity {
    private PieChart pieChart;
    String[] listItems;
    Bundle idlocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_prod_rep);

        idlocal = new Bundle();
        idlocal.putInt("Selected",0);

        pieChart = (PieChart) findViewById(R.id.chart);
        TextView text = (TextView) findViewById(R.id.titleText);


        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.15f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(new PieEntry(34f,"Aceite Sol"));
        yValues.add(new PieEntry(23f,"Sal Yodada"));
        yValues.add(new PieEntry(14f,"Coca Cola"));
        yValues.add(new PieEntry(33f,"Donofrio"));
        yValues.add(new PieEntry(40,"Pilsen Callao"));
        yValues.add(new PieEntry(23,"Pan Bimbo"));

        PieDataSet dataSet = new PieDataSet(yValues, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(16f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_reports, menu);
        return true;
    }

    public AlertDialog.Builder localDialog(){
        listItems = new String[] {"Local 1", "Local 2", "Local 3"};

        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(TopProdRepActivity.this);
        mBuilder.setTitle("Choose an local");
        mBuilder.setIcon(R.drawable.ic_action_local_dialog);
        mBuilder.setSingleChoiceItems(listItems,idlocal.getInt("Selected"), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i==0)
                    idlocal.putInt("Selected",0);
                if (i==1)
                    idlocal.putInt("Selected",1);
                if (i==2)
                    idlocal.putInt("Selected",2);
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
                //Toast.makeText(TopProdRepActivity.this,"Clickkk",Toast.LENGTH_SHORT).show();
                AlertDialog mDialog = localDialog().create();
                mDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
