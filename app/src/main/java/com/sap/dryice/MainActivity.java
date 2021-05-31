package com.sap.dryice;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private View screen;
    private int co2 = 1900;
    private int resultado_color;

    private TextView text_view_progress;
    private  ProgressBar progress_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideNavigationBarStatusBar();
        progress_bar = (ProgressBar) screen.findViewById(R.id.progress_bar);
        text_view_progress = (TextView) screen.findViewById(R.id.text_view_progress);
        updateProgressBar();



    }



    private void hideNavigationBarStatusBar () {
        screen = getWindow().getDecorView();
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |View.SYSTEM_UI_FLAG_FULLSCREEN;
        screen.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_main);
    }
    private void updateProgressBar() {
        resultado_color =  Math.abs(co2*100/1850);
        progress_bar.setProgress(resultado_color);
        if (co2<=617)
            text_view_progress.setTextColor(Color.rgb(0,255,255));
        else if (co2<=1234)
            text_view_progress.setTextColor(Color.rgb(26,56,241));
        else
            text_view_progress.setTextColor(Color.rgb(102,58,183));
        text_view_progress.setText(String.valueOf(co2));
    }
}