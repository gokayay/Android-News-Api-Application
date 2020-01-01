package com.gokaya.news;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class NewsContentActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newscontent);

        ArrayList<String> NewsString = getIntent().getStringArrayListExtra("NewsContent");
        assert NewsString != null;

        TextView Title = findViewById(R.id.cardTitle);
        Title.setText(NewsString.get(0));

        TextView Content = findViewById(R.id.cardContent);
        Content.setText(NewsString.get(1));

        TextView Date = findViewById(R.id.cardDate);
        Date.setText(NewsString.get(2));

        TextView Source = findViewById(R.id.cardNewsSource);
        Source.setText(NewsString.get(3));


        ImageView i = findViewById(R.id.newsImage);
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream((InputStream)new URL(NewsString.get(4)).getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        i.setImageBitmap(bitmap);

    }

}
