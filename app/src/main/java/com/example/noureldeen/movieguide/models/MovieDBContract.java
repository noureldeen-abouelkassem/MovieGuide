package com.example.noureldeen.movieguide.models;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by noureldeen on 1/22/2018.
 */

class MovieDBContract {
    private final static String CONTENT_AUTHORITY = "com.example.noureldeen.movieguide";
    private static final String PATH_MOVIE = "movies";
    private final static Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final class MovieItem implements BaseColumns{
        public static final String CONTENT_TABLE_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIE;
        public static final Uri TABLE_CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,PATH_MOVIE);
        public static final String TABLE_NAME ="movies";
        public static final String _ID =BaseColumns._ID;
        public static final String MOVIE_ID ="movieID";
        public static final String MOVIE_TITLE ="movieTitle";
    }
}
