package com.sap.dryice.screens.graphicsActivityFragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.sap.dryice.R;
import com.sap.dryice.dbAccess.CollectRPiRTExtendedData;
import com.sap.dryice.dbEntities.RTExtendedData;
import com.sap.dryice.screens.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class PageFragment2 extends Fragment implements CollectRPiRTExtendedData.Comunication {

    private BarChart barChart;
    private BarChart barChart2;
    private BarChart barChart3;
    private String[] datos = new String[]{"C02 Max", "C02 Min"};
    private String[] datos2 = new String[]{"Temp. Max", "Temp. Min"};
    private String[] datos3 = new String[]{"Hum. Max", "Hum. Min"};
    private int[] datosRasp = new int[]{76, 22};
    private int[] datosRasp2 = new int[]{28, 22};
    private int[] datosRasp3 = new int[]{1269, 602};
    private int[] datosColores = new int[]{Color.rgb(252,100,2), Color.rgb(0,255,255)};



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.main_page2, container, false);

        barChart = (BarChart) rootView.findViewById(R.id.barChart);
        barChart2 = (BarChart) rootView.findViewById(R.id.barChart2);
        barChart3 = (BarChart) rootView.findViewById(R.id.barChart3);

        CollectRPiRTExtendedData.takeDataFromOneRPi(this::sendDataFromOneRPi, LoginActivity.RPI_USERUID);


        return rootView;

    }


    private Chart getChart(Chart chart, String description, int textColor, int background, int animateY) {
        chart.getDescription().setText(description);
        chart.getDescription().setTextSize(15);
        chart.setBackgroundColor(background);
        chart.animateY(animateY);
        legend(chart);
        return chart;
    }

    private Chart getChart2(Chart chart, String description, int textColor, int background, int animateY) {
        chart.getDescription().setText(description);
        chart.getDescription().setTextSize(15);
        chart.setBackgroundColor(background);
        chart.animateY(animateY);
        legend2(chart);
        return chart;
    }

    private Chart getChart3(Chart chart, String description, int textColor, int background, int animateY) {
        chart.getDescription().setText(description);
        chart.getDescription().setTextSize(15);
        chart.setBackgroundColor(background);
        chart.animateY(animateY);
        legend3(chart);
        return chart;
    }

    private void legend(Chart chart) {
        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextColor(getResources().getColor(R.color.claroLetras));
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        ArrayList<LegendEntry> entries = new ArrayList<>();
        for (int i = 0; i < datosRasp.length; i++) {
            LegendEntry entry = new LegendEntry();
            entry.formColor = datosColores[i];
            entry.label = datos[i];
            entries.add(entry);
        }
        legend.setCustom(entries);
    }

    private void legend2(Chart chart) {
        Legend legend2 = chart.getLegend();
        legend2.setForm(Legend.LegendForm.CIRCLE);
        legend2.setTextColor(getResources().getColor(R.color.claroLetras));
        legend2.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        ArrayList<LegendEntry> entries = new ArrayList<>();
        for (int i = 0; i < datosRasp2.length; i++) {
            LegendEntry entry2 = new LegendEntry();
            entry2.formColor = datosColores[i];
            entry2.label = datos2[i];
            entries.add(entry2);
        }
        legend2.setCustom(entries);
    }
    private void legend3(Chart chart) {
        Legend legend3 = chart.getLegend();
        legend3.setForm(Legend.LegendForm.CIRCLE);
        legend3.setTextColor(getResources().getColor(R.color.claroLetras));
        legend3.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        ArrayList<LegendEntry> entries3 = new ArrayList<>();
        for (int i = 0; i < datosRasp3.length; i++) {
            LegendEntry entry3 = new LegendEntry();
            entry3.formColor = datosColores[i];
            entry3.label = datos3[i];
            entries3.add(entry3);
        }
        legend3.setCustom(entries3);
    }


    private ArrayList<BarEntry> getBarEntries() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < datosRasp.length; i++) {
            entries.add(new BarEntry(i, datosRasp[i]));
        }
        return entries;
    }

    private ArrayList<BarEntry> getBarEntries2() {
        ArrayList<BarEntry> entries2 = new ArrayList<>();
        for (int i = 0; i < datosRasp2.length; i++) {
            entries2.add(new BarEntry(i, datosRasp2[i]));
        }
        return entries2;
    }

    private ArrayList<BarEntry> getBarEntries3() {
        ArrayList<BarEntry> entries3 = new ArrayList<>();
        for (int i = 0; i < datosRasp3.length; i++) {
            entries3.add(new BarEntry(i, datosRasp3[i]));
        }
        return entries3;
    }

    private void axisX(XAxis axis) {
        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(datos));
        axis.setEnabled(false);
    }

    private void axisX2(XAxis axis) {
        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(datos2));
        axis.setEnabled(false);
    }

    private void axisX3(XAxis axis) {
        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(datos3));
        axis.setEnabled(false);
    }

    private void axisLeft(YAxis axis) {
        axis.setSpaceTop(30);
        axis.setAxisMinimum(0);
    }

    private void axisRight(YAxis axis) {
        axis.setEnabled(false);
    }

    private void createCharts() {
        barChart = (BarChart) getChart(barChart, "", Color.GRAY, Color.rgb(76, 80, 118), 3000);
        //barChart.setDrawGridBackground(false);
        //barChart.setDrawBarShadow(false);

        barChart.setData(getBarData());
        barChart.invalidate();
        axisX(barChart.getXAxis());
        axisLeft(barChart.getAxisLeft());
        axisRight(barChart.getAxisRight());

        barChart.getAxisLeft().setAxisMaximum(100);
        barChart.getAxisLeft().setAxisMinimum(0);
        barChart.setBackgroundColor(getResources().getColor(R.color.oscuroPrimary));
        barChart.getAxisLeft().setTextColor(getResources().getColor(R.color.white));
        barChart.getAxisLeft().setAxisLineColor(getResources().getColor(R.color.white));


        barChart2 = (BarChart) getChart2(barChart2, "", Color.GRAY, Color.rgb(76, 80, 118), 3000);
        //barChart.setDrawGridBackground(false);
        //barChart.setDrawBarShadow(false);
        barChart2.setData(getBarData2());
        barChart2.invalidate();
        axisX(barChart2.getXAxis());
        axisLeft(barChart2.getAxisLeft());
        axisRight(barChart2.getAxisRight());

        barChart2.getAxisLeft().setAxisMaximum(50);
        barChart2.getAxisLeft().setAxisMinimum(0);
        barChart2.setBackgroundColor(getResources().getColor(R.color.oscuroPrimary));
        barChart2.getAxisLeft().setTextColor(getResources().getColor(R.color.white));
        barChart2.getAxisLeft().setAxisLineColor(getResources().getColor(R.color.white));

        barChart3 = (BarChart) getChart3(barChart3, "", Color.GRAY, Color.rgb(76, 80, 118), 3000);
        //barChart.setDrawGridBackground(false);
        //barChart.setDrawBarShadow(false);
        barChart3.setData(getBarData3());
        barChart3.invalidate();
        axisX(barChart3.getXAxis());
        axisLeft(barChart3.getAxisLeft());
        axisRight(barChart3.getAxisRight());

        barChart3.getAxisLeft().setAxisMaximum(2000);
        barChart3.getAxisLeft().setAxisMinimum(0);
        barChart3.setBackgroundColor(getResources().getColor(R.color.oscuroPrimary));
        barChart3.getAxisLeft().setTextColor(getResources().getColor(R.color.white));
        barChart3.getAxisLeft().setAxisLineColor(getResources().getColor(R.color.white));
    }

    private DataSet getData(DataSet dataSet) {
        dataSet.setColors(datosColores);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(10);
        return dataSet;
    }

    private DataSet getData2(DataSet dataSet2) {
        dataSet2.setColors(datosColores);
        dataSet2.setValueTextColor(Color.WHITE);
        dataSet2.setValueTextSize(10);
        return dataSet2;
    }

    private DataSet getData3(DataSet dataSet3) {
        dataSet3.setColors(datosColores);
        dataSet3.setValueTextColor(Color.WHITE);
        dataSet3.setValueTextSize(10);

        return dataSet3;
    }

    private BarData getBarData() {
        BarDataSet barDataSet = (BarDataSet) getData2(new BarDataSet(getBarEntries(), ""));
        barDataSet.setBarShadowColor(Color.DKGRAY);
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.45f);
        return barData;
    }

    private BarData getBarData2() {
        BarDataSet barDataSet2 = (BarDataSet) getData2(new BarDataSet(getBarEntries2(), ""));
        barDataSet2.setBarShadowColor(Color.DKGRAY);
        BarData barData2 = new BarData(barDataSet2);
        barData2.setBarWidth(0.45f);
        return barData2;
    }

    private BarData getBarData3() {
        BarDataSet barDataSet3 = (BarDataSet) getData3(new BarDataSet(getBarEntries3(), ""));
        barDataSet3.setBarShadowColor(Color.DKGRAY);
        BarData barData3 = new BarData(barDataSet3);
        barData3.setBarWidth(0.45f);
        return barData3;
    }

    @Override
    public void sendDataFromOneRPi(List<RTExtendedData> rteData) {

        for (RTExtendedData rtData : rteData) {
            if (rtData.getIdRPi().equals("RaspiDAM")) {
                System.out.println(rtData);
                datosRasp = new int[]{Integer.parseInt(rtData.getMaxCO2()), Integer.parseInt(rtData.getMinCO2())};
                datosRasp2 = new int[]{Integer.parseInt(rtData.getMaxTemperature()), Integer.parseInt(rtData.getMinTemperature())};
                datosRasp3 = new int[]{Integer.parseInt(rtData.getMaxRelHumedity()), Integer.parseInt(rtData.getMinRelHumedity())};

                createCharts();
            }
        }
    }

}

