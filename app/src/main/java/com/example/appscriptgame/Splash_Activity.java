package com.example.appscriptgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash_Activity extends AppCompatActivity {


    private boolean ispause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);



        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                if(!ispause) {
                    Intent intent = new Intent(Splash_Activity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.enter, R.anim.exit);
                    finish();
                }else
                {
                    finish();
                }
                // close this activity
            }
        }, 3000);

    }


    @Override
    public void onStop()
    {
        ispause=true;
        super.onStop();
    }

    @Override
    public void onResume()
    {
        ispause=false;
        super.onResume();
    }

}
