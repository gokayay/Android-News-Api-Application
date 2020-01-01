package com.gokaya.news;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gokaya.news.database.NewsDao;
import com.gokaya.news.database.NewsDatabase;
import com.gokaya.news.database.NewsEntity;
import com.gokaya.news.recycler.NewsRecycler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

public class ExchangeRateActivity extends AppCompatActivity {
    private RequestQueue mQueue;
    Context context;
    String url = "https://api.exchangeratesapi.io/latest?base=TRY";
    TextView euroText;
    TextView dollarText;
    TextView gbpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchangerate);

        euroText = findViewById(R.id.euroText);
        dollarText = findViewById(R.id.dollarText);
        gbpText = findViewById(R.id.gbpText);

        new GetExchangeRate().execute(url);


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public class GetExchangeRate extends AsyncTask<String, Void, String> {

        Double dollar;
        Double euro;
        Double sterlin;

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... urls) {

            mQueue = Volley.newRequestQueue(ExchangeRateActivity.this);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                                JSONObject jsonObj = response.getJSONObject("rates");

                                        dollar = jsonObj.getDouble("USD");
                                        euro = jsonObj.getDouble("EUR");
                                        sterlin = jsonObj.getDouble("GBP");

                                        dollarText.setText(dollar.toString());
                                        euroText.setText(euro.toString());
                                        gbpText.setText(sterlin.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });

            mQueue.add(request);
            return url;
        }

        @Override
        protected void onPostExecute (String result){
            //mTextView.setText(result);
        }
    }



}
