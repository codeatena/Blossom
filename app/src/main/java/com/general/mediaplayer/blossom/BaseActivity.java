package com.general.mediaplayer.blossom;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class BaseActivity extends AppCompatActivity {


    public static int FILTER_RESET_DELAY = 1000 * 2 * 60;
    private static Handler mFilterResetHandler = new Handler();
    private static Runnable mFilterResetRunnable = null;
    boolean isPaused;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    public void stopResetTimer() {
        if (mFilterResetRunnable != null) {
            mFilterResetHandler.removeCallbacks(mFilterResetRunnable);
        }
    }

    public void setupFilterResetTimer() {
        stopResetTimer();

        mFilterResetRunnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(BaseActivity.this, LoopingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        };

        mFilterResetHandler.postDelayed(mFilterResetRunnable, FILTER_RESET_DELAY);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        setupFilterResetTimer();
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        isPaused = true;
        setupFilterResetTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();

        isPaused = false;
        stopResetTimer();

    }

    @Override
    public void finish() {
        super.finish();
    }

}
