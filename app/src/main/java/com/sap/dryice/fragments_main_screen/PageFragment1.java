package com.sap.dryice.fragments_main_screen;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sap.dryice.R;

public class PageFragment1 extends Fragment {

    private int co2 = 1670;
    private int MAX_co2 = 1850;
    private int temperatura = 22;
    private int humedad = 56;

    private TextView text_view_progress;
    private TextView text_view_temp;
    private TextView text_view_hum;
    private ProgressBar progress_bar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.main_page1,container,false);
        progress_bar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        progress_bar.setMax(MAX_co2);
        text_view_progress = (TextView) rootView.findViewById(R.id.text_view_progress);
        text_view_temp = (TextView) rootView.findViewById(R.id.text_view_temp);
        text_view_hum = (TextView) rootView.findViewById(R.id.text_view_hum);

        updateProgressBar();
        updateTextTemp();
        updateTextHum();
        return rootView;
    }

    private void updateProgressBar() {

        new CountDownTimer(2000, 40) {

            int progreso = 50; // Variable que va a ir aumentando del progreso
            @Override
            public void onTick(long millisUntilFinished) {
                if(progreso<=co2){
                    progress_bar.setProgress(progreso);

                    text_view_progress.setText(String.valueOf(progreso));
                    if (co2<=617)
                        text_view_progress.setTextColor(Color.rgb(0,255,255));
                    else if (co2<=1234)
                        text_view_progress.setTextColor(Color.rgb(26,56,241));
                    else
                        text_view_progress.setTextColor(Color.rgb(252,100,2));
                    progreso += (50);
                }else {
                    progress_bar.setProgress(co2);
                    text_view_progress.setText(String.valueOf(co2));
                }

            }

            @Override
            public void onFinish() {
                progress_bar.setProgress(co2);
            }
        }.start();

    }
    private void updateTextTemp() {

        new CountDownTimer(2000, 30) {

            int progreso = 1; // Variable que va a ir aumentando del progreso
            @Override
            public void onTick(long millisUntilFinished) {
                if(progreso<=temperatura){

                    text_view_temp.setText(String.valueOf(progreso)+"°C");

                    progreso += (1);
                }else {
                    text_view_temp.setText(String.valueOf(temperatura)+"°C");

                }
            }

            @Override
            public void onFinish() {
                text_view_temp.setText(String.valueOf(temperatura)+"°C");

            }
        }.start();

    }private void updateTextHum() {

        new CountDownTimer(2000, 30) {

            int progreso = 2; // Variable que va a ir aumentando del progreso
            @Override
            public void onTick(long millisUntilFinished) {
                if(progreso <=humedad){

                    text_view_hum.setText(String.valueOf(progreso)+"%");

                    progreso += (2);
                }else {

                    text_view_hum.setText(String.valueOf(humedad)+"%");
                }

            }

            @Override
            public void onFinish() {

                text_view_hum.setText(String.valueOf(humedad)+"%");
            }
        }.start();

    }
}

