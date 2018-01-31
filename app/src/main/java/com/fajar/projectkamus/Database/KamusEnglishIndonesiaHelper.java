package com.fajar.projectkamus.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.fajar.projectkamus.Model.KamusModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.fajar.projectkamus.Database.DatabaseContract.EnglishIndonesiaColumn.DETAILS_ENGLISH;
import static com.fajar.projectkamus.Database.DatabaseContract.EnglishIndonesiaColumn.WORDS_ENGLISH;
import static com.fajar.projectkamus.Database.DatabaseContract.TABLE_ENGLISH_INDONESIA;



public class KamusEnglishIndonesiaHelper {

    private Context context;
    private DatabaseHelper databaseHelper;

    private SQLiteDatabase database;

    public KamusEnglishIndonesiaHelper(Context context) {
        this.context = context;
    }

    public KamusEnglishIndonesiaHelper open() throws SQLException{
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        databaseHelper.close();
    }

    public ArrayList<KamusModel> getDataByWord(String word){
        String result = "";
        Cursor cursor = database.query(TABLE_ENGLISH_INDONESIA,null, WORDS_ENGLISH + " LIKE ?", new String[]{"%" + word + "%"},null, null,WORDS_ENGLISH);
        cursor.moveToFirst();
        ArrayList<KamusModel> arrayList = new ArrayList<>();
        KamusModel kamusModel;
        if(cursor.getCount()>0){
            do{
                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusModel.setWords(cursor.getString(cursor.getColumnIndexOrThrow(WORDS_ENGLISH)));
                kamusModel.setDetails(cursor.getString(cursor.getColumnIndexOrThrow(DETAILS_ENGLISH)));

                arrayList.add(kamusModel);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(KamusModel kamusModel){
        ContentValues initialValues = new ContentValues();
        initialValues.put(WORDS_ENGLISH, kamusModel.getWords());
        initialValues.put(DETAILS_ENGLISH, kamusModel.getDetails());
        return database.insert(TABLE_ENGLISH_INDONESIA, null, initialValues);
    }

    public void beginTransaction(){
        database.beginTransaction();
    }

    public void setTransactionSuccess(){
        database.setTransactionSuccessful();
    }

    public void endTransaction(){
        database.endTransaction();
    }

    public void insertTransaction(KamusModel kamusModel){
        String sql = "INSERT INTO " + TABLE_ENGLISH_INDONESIA + " (" + WORDS_ENGLISH + ", " + DETAILS_ENGLISH + ") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, kamusModel.getWords());
        stmt.bindString(2, kamusModel.getDetails());
        stmt.execute();
        stmt.clearBindings();
    }

    public int update(KamusModel kamusModel){
        ContentValues args = new ContentValues();
        args.put(WORDS_ENGLISH, kamusModel.getWords());
        args.put(DETAILS_ENGLISH, kamusModel.getDetails());
        return database.update(TABLE_ENGLISH_INDONESIA, args, _ID + "= '" + kamusModel.getId() + "'", null);
    }

    public int delete(int id){
        return database.delete(TABLE_ENGLISH_INDONESIA, _ID + " = '" + id + "'", null);
    }
}
