package com.fumiao.pay.tools;

import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DecimalFormat;

public class DecimalFormatter extends ValueFormatter {

    private DecimalFormat mFormat;

    public DecimalFormatter() {
        mFormat = new DecimalFormat("###,###,##0.00");
    }

    @Override
    public String getFormattedValue(float value) {
        if(value >= 0){
            return "￥"+mFormat.format(value);
        }else {
            return "￥-"+mFormat.format(Math.abs(value));
        }

    }
}
