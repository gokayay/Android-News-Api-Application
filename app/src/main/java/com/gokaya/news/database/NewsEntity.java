package com.gokaya.news.database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "newsTable")
public class NewsEntity {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int myid ;

    @Nullable
    @ColumnInfo(name = "newsSource")
    private String newsSource;

    @Nullable
    @ColumnInfo(name = "newsLink")
    private String newsLink;

    @Nullable
    @ColumnInfo(name = "newsTitle")
    private String newsTitle;

    @Nullable
    @ColumnInfo(name = "newsContent")
    private String newsContent;

    @Nullable
    @ColumnInfo(name = "newsDescription")
    private String newsDescription;

    @Nullable
    @ColumnInfo(name = "newsImage")
    private String newsImage;

    @Nullable
    @ColumnInfo(name = "newsDate")
    private String newsDate;

    public NewsEntity (@NonNull String newsTitle,
                       @Nullable String newsDescription,
                       @Nullable String newsContent,
                       @Nullable String newsSource,
                       @Nullable String newsImage,
                       @Nullable String newsDate)
    {
        this.newsTitle = newsTitle;
        this.newsDescription = newsDescription;
        this.newsContent = newsContent;
        this.newsSource = newsSource;
        this.newsImage = newsImage;
        this.newsDate = newsDate;

    }

    public int getMyid() {
        return myid;
    }

    public void setMyid(int myid) {
        this.myid = myid;
    }

    @Nullable
    public String getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(@Nullable String newsSource) {
        this.newsSource = newsSource;
    }

    @Nullable
    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(@Nullable String newsTitle) {
        this.newsTitle = newsTitle;
    }

    @Nullable
    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(@Nullable String newsContent) {
        this.newsContent = newsContent;
    }

    @Nullable
    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(@Nullable String newsDescription) {
        this.newsDescription = newsDescription;
    }

    @Nullable
    public String getNewsLink() {
        return newsLink;
    }

    public void setNewsLink(@Nullable String newsLink) {
        this.newsLink = newsLink;
    }

    @Nullable
    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(@Nullable String newsImage) {
        this.newsImage = newsImage;
    }

    @Nullable
    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(@Nullable String newsDate) {
        this.newsDate = newsDate;
    }
}
