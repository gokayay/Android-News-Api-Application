package com.gokaya.news;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mQueue;
    Context context;
    public RecyclerView recyclerview;
    static final String KEY_AUTHOR = "author";
    static final String KEY_TITLE = "title";
    static final String KEY_DESCRIPTION = "description";
    static final String KEY_URLTOIMAGE = "urlToImage";
    static final String KEY_PUBLISHEDAT = "publishedAt";
    static final String KEY_CONTENT = "content";
    String url = "https://newsapi.org/v2/top-headlines?country=tr&apiKey=0330b250eb5744b895f92bd50be02798";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetNews().execute(url);

        recyclerview = findViewById(R.id.recycler);


        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);

        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
    }


    @Override
    protected void onStart() {
        super.onStart();

        recyclerview = findViewById(R.id.recycler);

    }

    public class GetNews extends AsyncTask<String, Void, String> {

        NewsDao dao = null;

        @Override
        protected void onPreExecute() {

            NewsDatabase newsDb = Room.databaseBuilder(getApplicationContext(), NewsDatabase.class, "newsTable")
                    .allowMainThreadQueries()
                    .build();
            dao = newsDb.newsDao();
            dao.deleteAll();
        }

        @Override
        protected String doInBackground(String... urls) {

            mQueue = Volley.newRequestQueue(MainActivity.this);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                                JSONArray jsonArray = response.getJSONArray("articles");


                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject j = jsonArray.getJSONObject(i);
                                    try {

                                        if (j.optString(KEY_AUTHOR) != "null") {
                                            NewsEntity newsEntity = new NewsEntity(j.optString(KEY_TITLE), j.optString(KEY_DESCRIPTION),
                                                    j.optString(KEY_CONTENT), j.optString(KEY_AUTHOR), j.optString(KEY_URLTOIMAGE), j.optString(KEY_PUBLISHEDAT));
                                            dao.insert(newsEntity);


                                        } else {
                                            NewsEntity newsEntity = new NewsEntity(j.optString(KEY_TITLE), j.optString(KEY_DESCRIPTION),
                                                    j.optString(KEY_CONTENT), j.optString(KEY_AUTHOR), j.optString(KEY_URLTOIMAGE), j.optString(KEY_PUBLISHEDAT));
                                            dao.insert(newsEntity);

                                        }


                                        List<NewsEntity> listNews = dao.getAllNews();
                                        NewsRecycler recycler = new NewsRecycler(listNews, context);
                                        recyclerview.setAdapter(recycler);


                                    } catch (Exception e) {
                                        System.out.println("---");
                                    }
                                }
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

