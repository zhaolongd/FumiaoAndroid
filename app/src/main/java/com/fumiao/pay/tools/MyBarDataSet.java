package com.fumiao.pay.tools;

import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.List;

public class MyBarDataSet extends BarDataSet {
    float max = 0;
    public MyBarDataSet(List<BarEntry> yVals, String label, float maxVal) {
        super(yVals, label);
        max = maxVal;
    }

    @Override
    public int getColor(int index) {

        if( getEntryForIndex(index).getY() == max)
            return mColors.get(0);
        else
            return mColors.get(1);
    }
}
