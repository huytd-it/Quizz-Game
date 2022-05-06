package com.yukihuy.myapplication.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.yukihuy.myapplication.R;

import java.util.ArrayList;
import java.util.Random;

public class AskAudicentDialog extends DialogFragment {

    BarChart barChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ask_audience_dialog,container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        barChart = view.findViewById(R.id.bargraph);
        ImageView button = view.findViewById(R.id.imgClose);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Chart();
    }


    public void Chart() {
        float yA,yB,yC,yD;
        Random random = new Random();
        yD = 40+random.nextInt(100);
        yB = random.nextInt((int) (100-yD));
        yC = random.nextInt((int)(100-yD-yB));
        yA = 100-yD-yB-yC;

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, (float) yA));
        barEntries.add(new BarEntry(1,(float)yB));
        barEntries.add(new BarEntry(2,(float)yC));
        barEntries.add(new BarEntry(3,(float)yD));

        BarDataSet barDataSet = new BarDataSet(barEntries,null);
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextSize(20f);
        barDataSet.setValueFormatter(new PercentFormatter());


        final String[] mValues = new String[] { "A", "B", "C", "D" };
        ValueFormatter valueFormatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int val = (int) (value);
                String label = "";
                if (val >= 0 && val < mValues.length) {
                    label = mValues[val];
                } else {
                    label = "";
                }
                return label;
            }
        };

        YAxis yAxisl = barChart.getAxisLeft();
        yAxisl.setEnabled(false);
        YAxis yAxisr = barChart.getAxisRight();
        yAxisr.setEnabled(false);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(valueFormatter);
        xAxis.setDrawAxisLine(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(barEntries.size());
        xAxis.setTextSize(20f);

        BarData barData = new BarData(barDataSet,barDataSet);

        barData.setBarWidth(0.9f);
        barChart.setData(barData);
        barChart.setFitBars(true);
        barChart.animateY(5000, Easing.EaseOutBack);
        barChart.invalidate();


    }
}
