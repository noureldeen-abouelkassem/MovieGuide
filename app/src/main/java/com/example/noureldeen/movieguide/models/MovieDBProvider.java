package com.example.noureldeen.movieguide.models;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by noureldeen on 1/22/2018.
 */

public class MovieDBProvider extends ContentProvider {
    private MovieDBHelper movieDBHelper;

    @Override
    public boolean onCreate() {
        movieDBHelper = new MovieDBHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        Cursor cursor =  movieDBHelper.getReadableDatabase().query(MovieDBContract.MovieItem.TABLE_NAME,strings,s,strings1,null,null,null);
        cursor.setNotificationUri(getContext().getContentResolver(),MovieDBContract.MovieItem.TABLE_CONTENT_URI);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return MovieDBContract.MovieItem.CONTENT_TABLE_TYPE;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        int movieID;
        String movieTitle;
        try{
             movieID = contentValues.getAsInteger(MovieDBContract.MovieItem.MOVIE_ID);
        }catch (NullPointerException e){
            throw new IllegalArgumentException("Movie requires an ID");
        }
        try{
             movieTitle = contentValues.getAsString(MovieDBContract.MovieItem.MOVIE_TITLE);

        }catch (NullPointerException e){
            throw new IllegalArgumentException("Movie requires a Title");
        }
        if (movieID == 0) {
            Toast.makeText(getContext(),"Movie requires an ID",Toast.LENGTH_LONG).show();
            throw new IllegalArgumentException("Movie requires an ID");
        }
        if (movieTitle == null) {
            Toast.makeText(getContext(),"Movie requires a Title",Toast.LENGTH_LONG).show();
            throw new IllegalArgumentException("Movie requires a Title");
        }
        long _id;
        _id = db.insert(MovieDBContract.MovieItem.TABLE_NAME, null, contentValues);
        if (_id == -1) {
            Toast.makeText(getContext(),"Failed to add movie to favorites",Toast.LENGTH_LONG).show();
            return null;
        }
        getContext().getContentResolver().notifyChange(MovieDBContract.MovieItem.TABLE_CONTENT_URI,null);
        return ContentUris.withAppendedId(uri, _id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        getContext().getContentResolver().notifyChange(MovieDBContract.MovieItem.TABLE_CONTENT_URI,null);
        return movieDBHelper.getWritableDatabase().delete(MovieDBContract.MovieItem.TABLE_NAME, s, strings);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }


}
