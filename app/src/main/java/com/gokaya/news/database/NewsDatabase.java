package com.gokaya.news.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {NewsEntity.class},version = 1, exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase {

    public abstract NewsDao newsDao();
    private static NewsDatabase Instance;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static NewsDatabase getNewsDatabase(final Context context){
        if(Instance == null){
            synchronized (NewsDatabase.class){
                if(Instance == null) {
                    Instance = Room.databaseBuilder(context.getApplicationContext(), NewsDatabase.class, "newsTable")
                            .fallbackToDestructiveMigration()
                            .addCallback(sHaberDatabase)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }

            }
        }
        return Instance;
    }


    private static RoomDatabase.Callback sHaberDatabase = new RoomDatabase.Callback(){

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);


        }
    };



}
