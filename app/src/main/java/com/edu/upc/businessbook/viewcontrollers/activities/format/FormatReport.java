package com.edu.upc.businessbook.viewcontrollers.activities.format;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.widget.TextView;

import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.viewcontrollers.activities.TopProdRepActivity;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

public class FormatReport {

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
