package com.example.abc.chinesemedicine;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

public class MyYAxisValueFormatter implements IAxisValueFormatter {

    private List<String> mValues;

    public MyYAxisValueFormatter(List<String> values) {
        this.mValues = values;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        // "value" represents the position of the label on the axis (x or y)

        if(value<mValues.size())
        {

            return mValues.get((int) value);
        }else
        {
            return mValues.get(0);
        }
    }

}
