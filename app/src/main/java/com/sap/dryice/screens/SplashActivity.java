package com.sap.dryice.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.sap.dryice.R;

public class SplashActivity extends AppCompatActivity {

    private Animation alphaAnimation,transitionAnimation;
    private ImageView logoSplash,explore_analyze;
    private View screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hideNavigationBarStatusBar();

        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_splash_screen);
        transitionAnimation = AnimationUtils.loadAnimation(this, R.anim.transition_splash_screen);
        logoSplash = findViewById(R.id.logo_splash);
        explore_analyze = findViewById(R.id.explore_analyze);

        explore_analyze.setAnimation(transitionAnimation);
        logoSplash.setAnimation(alphaAnimation);

        openApp(true);
    }

    /**
     * This method is used to change the Splash Screen to the Login Screen.
     * @param locationPermission
     */
    private void openApp(boolean locationPermission) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, GraphicsActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }

    /**
     * This method is used to hide the navigation bar and the status bar. Method found
     * in the Android Developers Documentation:
     * https://developer.android.com/training/system-ui/navigation?hl=es
     */
    private void hideNavigationBarStatusBar () {
        screen = getWindow().getDecorView();
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |View.SYSTEM_UI_FLAG_FULLSCREEN;
        screen.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_splash);
    }
}