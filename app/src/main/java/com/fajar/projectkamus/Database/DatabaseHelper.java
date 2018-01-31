package com.fajar.projectkamus.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.fajar.projectkamus.Database.DatabaseContract.EnglishIndonesiaColumn.WORDS_ENGLISH;
import static com.fajar.projectkamus.Database.DatabaseContract.EnglishIndonesiaColumn.DETAILS_ENGLISH;
import static com.fajar.projectkamus.Database.DatabaseContract.IndonesiaEnglishColumn.WORDS_INDONESIA;
import static com.fajar.projectkamus.Database.DatabaseContract.IndonesiaEnglishColumn.DETAILS_INDONESIA;
import static com.fajar.projectkamus.Database.DatabaseContract.TABLE_ENGLISH_INDONESIA;
import static com.fajar.projectkamus.Database.DatabaseContract.TABLE_INDONESIA_ENGLISH;



public class DatabaseHelper extends SQLiteOpenHelper{

    private static String DATABASE_NAME = "DB_KAMUS";

    private static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_ENGLISH_INDONESIA = "CREATE TABLE " + TABLE_ENGLISH_INDONESIA + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + WORDS_ENGLISH + " TEXT NOT NULL, " + DETAILS_ENGLISH + " TEXT NOT NULL);";

    public static String CREATE_TABLE_INDONESIA_ENGLISH = "CREATE TABLE " + TABLE_INDONESIA_ENGLISH + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + WORDS_INDONESIA + " TEXT NOT NULL, " + DETAILS_INDONESIA + " TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ENGLISH_INDONESIA);
        db.execSQL(CREATE_TABLE_INDONESIA_ENGLISH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENGLISH_INDONESIA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INDONESIA_ENGLISH);
        onCreate(db);
    }
}
