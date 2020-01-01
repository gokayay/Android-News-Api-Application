package com.gokaya.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        ImageView img =findViewById(R.id.imageView);

        ImageView imgExchange =findViewById(R.id.imageView2);

        img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(LauncherActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        imgExchange.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent ix = new Intent(LauncherActivity.this, ExchangeRateActivity.class);
                startActivity(ix);
            }
        });
    }


}
