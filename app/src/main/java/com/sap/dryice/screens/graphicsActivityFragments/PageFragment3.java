package com.sap.dryice.screens.graphicsActivityFragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.sap.dryice.R;

import java.util.ArrayList;
import java.util.Map;

public class PageFragment3 extends Fragment /**implements OnChartGestureListener, OnChartValueSelectedListener**/ {

    private static final String TAG = "Prueba";
    private LineChart mChart;
    private LineChart mChart2;
    private LineChart mChart3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.main_page3, container, false);
        mChart = (LineChart) rootView.findViewById(R.id.lineChart);
        mChart2 = (LineChart) rootView.findViewById(R.id.lineChart2);
        mChart3 = (LineChart) rootView.findViewById(R.id.lineChart3);

        //mChart.setOnChartGestureListener(PageFragment3.this);
        //mChart.setOnChartValueSelectedListener(PageFragment3.this);

        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart2.setDragEnabled(true);
        mChart2.setScaleEnabled(true);
        mChart3.setDragEnabled(true);
        mChart3.setScaleEnabled(false);

        ArrayList<Entry> yValores = new ArrayList();
        ArrayList<Entry> yValores2 = new ArrayList();
        ArrayList<Entry> yValores3 = new ArrayList();

        yValores.add(new Entry(1, 66f));
        yValores.add(new Entry(2, 70f));
        yValores.add(new Entry(3, 78f));
        yValores.add(new Entry(4, 52f));
        yValores.add(new Entry(5, 48f));
        yValores.add(new Entry(6, 40f));

        yValores2.add(new Entry(1, 70f));
        yValores2.add(new Entry(2, 10f));
        yValores2.add(new Entry(3, 30f));
        yValores3.add(new Entry(1, 10f));
        yValores3.add(new Entry(2, 40f));
        yValores3.add(new Entry(3, 60f));
        LineDataSet set1 = new LineDataSet(yValores, "Datos CO2");
        LineDataSet set2 = new LineDataSet(yValores2, "Datos Temperatura");
        LineDataSet set3 = new LineDataSet(yValores3, "Datos Humedad");

        set1.setFillAlpha(110);
        set1.setColor(Color.RED);
        set1.setLineWidth(3f);
        set2.setFillAlpha(110);
        set2.setColor(Color.BLUE);
        set2.setLineWidth(3f);
        set3.setFillAlpha(110);
        set3.setColor(Color.GREEN);
        set3.setLineWidth(3f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        ArrayList<ILineDataSet> dataSets2 = new ArrayList<>();
        dataSets2.add(set2);
        ArrayList<ILineDataSet> dataSets3 = new ArrayList<>();
        dataSets3.add(set3);

        LineData data = new LineData(dataSets);
        LineData data2 = new LineData(dataSets2);
        LineData data3 = new LineData(dataSets3);

        mChart.setData(data);
        mChart.getAxisLeft().setTextColor(Color.WHITE);
        mChart.getAxisRight().setTextColor(Color.WHITE);
        mChart.getXAxis().setTextColor(Color.WHITE);
        mChart.getLegend().setTextColor(Color.WHITE);
        mChart.getDescription().setTextColor(Color.WHITE);
        mChart.getDescription().setText("");
        mChart.invalidate();


        mChart2.setData(data2);
        mChart2.getAxisLeft().setTextColor(Color.WHITE);
        mChart2.getAxisRight().setTextColor(Color.WHITE);
        mChart2.getXAxis().setTextColor(Color.WHITE);
        mChart2.getLegend().setTextColor(Color.WHITE);
        mChart2.getDescription().setTextColor(Color.WHITE);
        mChart2.getDescription().setText("");


        mChart3.setData(data3);
        mChart3.getAxisLeft().setTextColor(Color.WHITE);
        mChart3.getAxisRight().setTextColor(Color.WHITE);
        mChart3.getXAxis().setTextColor(Color.WHITE);
        mChart3.getLegend().setTextColor(Color.WHITE);
        mChart3.getDescription().setTextColor(Color.WHITE);
        mChart3.getDescription().setText("");
        mChart.getAxisLeft().setAxisMaximum(100);
        mChart.getAxisLeft().setAxisMinimum(0);

        return rootView;
    }
}
