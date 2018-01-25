package com.example.noureldeen.movieguide.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.noureldeen.movieguide.models.MovieDBContract.MovieItem;

/**
 * Created by noureldeen on 1/22/2018.
 */

public class MovieDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME= "favorites.db";
    public MovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_MOVIE_TABLE = "CREATE TABLE "
                + MovieItem.TABLE_NAME + " ( "
                + MovieItem._ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + MovieItem.MOVIE_ID + " INTEGER NOT NULL , "
                + MovieItem.MOVIE_TITLE + " TEXT NOT NULL ); ";
        sqLiteDatabase.execSQL(CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // gonna implement it when updating the database
    }
}
