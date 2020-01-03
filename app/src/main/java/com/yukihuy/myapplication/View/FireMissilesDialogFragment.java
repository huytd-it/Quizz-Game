package com.yukihuy.myapplication.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.github.mikephil.charting.utils.ColorTemplate;
import com.yukihuy.myapplication.R;

import java.util.ArrayList;

public class FireMissilesDialogFragment extends DialogFragment {

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
        Chart();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater mInflater =  getActivity().getLayoutInflater();
        builder.setView(mInflater.inflate(R.layout.ask_audience_dialog,null));
        return builder.create();
    }
    public void Chart() {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(2f,10f));
        barEntries.add(new BarEntry(3f,50f));
        barEntries.add(new BarEntry(4f,10f));
        barEntries.add(new BarEntry(5f,30f));


        BarDataSet barDataSet = new BarDataSet(barEntries,"Đáp án");
        //barDataSet.setColors(new int[]{R.color.black,R.color.red,R.color.colorAccent,R.color.colorPrimaryDark});
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextSize(20f);

        YAxis yAxisl = barChart.getAxisLeft();
        yAxisl.setEnabled(false);
        YAxis yAxisr = barChart.getAxisRight();
        yAxisr.setEnabled(false);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setEnabled(false);



        BarData barData = new BarData(barDataSet,barDataSet);
        barData.setBarWidth(0.9f);
        barChart.setData(barData);
        barChart.setFitBars(true);
        barChart.animateY(5000, Easing.EaseOutBack);
        barChart.invalidate();


    }
}
