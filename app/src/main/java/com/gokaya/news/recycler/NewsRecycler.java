package com.gokaya.news.recycler;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.gokaya.news.NewsContentActivity;
import com.gokaya.news.R;
import com.gokaya.news.database.NewsEntity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class NewsRecycler extends RecyclerView.Adapter<NewsRecycler.ViewHolder>{

    Context context;

    private List<NewsEntity> news_list ;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ViewHolder viewHolder = new  ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false));

        return  viewHolder;
    }
    public NewsRecycler(List<NewsEntity> news_list, Context context){
        this.news_list = news_list;
        this.context = context;
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        if (news_list != null) {

            holder.newsTitle.setText(news_list.get(position).getNewsTitle());
            holder.newsSource.setText(news_list.get(position).getNewsSource());
            holder.newsDescription.setText(news_list.get(position).getNewsDescription());
            holder.newsDate.setText(news_list.get(position).getNewsDate());
            Picasso.with(holder.newsImage.getContext()).load(news_list.get(position).getNewsImage()).into(holder.newsImage);


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String title = news_list.get(position).getNewsTitle();
                    String date = news_list.get(position).getNewsDate();
                    String content = news_list.get(position).getNewsContent();
                    String source = news_list.get(position).getNewsSource();
                    String imageUrl =news_list.get(position).getNewsImage();

                    ArrayList<String> NewsString = new ArrayList<>();
                    NewsString.add(title);
                    NewsString.add(content);
                    NewsString.add(date);
                    NewsString.add(source);
                    NewsString.add(imageUrl);



                    Intent myIntent = new Intent(holder.cardView.getContext(), NewsContentActivity.class);
                    myIntent.putStringArrayListExtra("NewsContent", NewsString);
                    holder.cardView.getContext().startActivity(myIntent);

                }
            });

        }else{

            holder.newsTitle.setText("Item not found");
            holder.newsSource.setText("Item not found");
            holder.newsDescription.setText("Item not found");
            holder.newsAuthor.setText("Item not found");
            holder.newsDate.setText("Item not found");
            holder.newsSource.setText("Item not found");
        }

    }


    @Override
    public int getItemCount() {
        if (news_list != null) {
            return news_list.size();
        }else {
            return 0;
        }
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        public CardView cardView;
        public TextView newsTitle;
        public TextView newsDescription;
        public TextView newsAuthor;
        public TextView newsDate;
        public ImageView newsImage;
        public TextView newsSource;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cardView = itemView.findViewById(R.id.cardview);
            this.newsTitle = itemView.findViewById(R.id.cardTitle);
            this.newsDescription = itemView.findViewById(R.id.cardDescription);
            this.newsImage = itemView.findViewById(R.id.newsImage);
            this.newsDate = itemView.findViewById(R.id.cardDate);
            this.newsSource= itemView.findViewById(R.id.cardNewsSource);
        }
    }

}
