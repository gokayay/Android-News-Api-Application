package com.gokaya.news.database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert
    void insert(NewsEntity newsEntity);

    @Query("DELETE FROM newsTable")
    void deleteAll();

    @Query("SELECT * FROM newsTable")
    List<NewsEntity> getAllNews();


}
