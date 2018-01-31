package com.fajar.projectkamus;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.fajar.projectkamus.Database.KamusEnglishIndonesiaHelper;
import com.fajar.projectkamus.Database.KamusIndonesiaEnglishHelper;
import com.fajar.projectkamus.Model.KamusIndoModel;
import com.fajar.projectkamus.Model.KamusModel;
import com.fajar.projectkamus.Preferences.AppPreferences;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new LoadData().execute();
    }

    private class LoadData extends AsyncTask<Void, Integer, Void> {

        final String TAG = LoadData.class.getSimpleName();
        KamusEnglishIndonesiaHelper kamusEnglishIndonesiaHelper;
        KamusIndonesiaEnglishHelper kamusIndonesiaEnglishHelper;
        AppPreferences appPreferences;

        @Override
        protected void onPreExecute() {
            kamusEnglishIndonesiaHelper = new KamusEnglishIndonesiaHelper(SplashActivity.this);
            kamusIndonesiaEnglishHelper = new KamusIndonesiaEnglishHelper(SplashActivity.this);
            appPreferences = new AppPreferences(SplashActivity.this);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Boolean firstRun = appPreferences.getFirstRun();
            if(firstRun){
                ArrayList<KamusModel> kamusModels = preLoadRaw();
                ArrayList<KamusIndoModel> kamusIndoModels = preLoadIndoRaw();

                kamusEnglishIndonesiaHelper.open();

                kamusEnglishIndonesiaHelper.beginTransaction();

                try {
                    for (KamusModel model: kamusModels){
                        kamusEnglishIndonesiaHelper.insertTransaction(model);
                    }
                    kamusEnglishIndonesiaHelper.setTransactionSuccess();
                }catch (Exception e){
                    Log.e(TAG, "doInBackground: Exception");
                }

                kamusEnglishIndonesiaHelper.endTransaction();

                kamusEnglishIndonesiaHelper.close();

                kamusIndonesiaEnglishHelper.open();

                kamusIndonesiaEnglishHelper.beginTransactionIndo();

                try {
                    for(KamusIndoModel indoModel: kamusIndoModels){
                        kamusIndonesiaEnglishHelper.insertTransactionIndo(indoModel);
                    }
                    kamusIndonesiaEnglishHelper.setTransactionSuccessIndo();
                }catch (Exception e){
                    Log.e(TAG, "doInBackground: Exception");
                }

                kamusIndonesiaEnglishHelper.endTransactionIndo();

                kamusIndonesiaEnglishHelper.closeIndo();

                appPreferences.setFirstRun(false);
            }else {
                try {
                    synchronized (this){
                        this.wait(2000);
                    }
                }catch (Exception e){

                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void result) {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    private ArrayList<KamusModel> preLoadRaw() {
        ArrayList<KamusModel> kamusModels = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try {
            Resources res = getResources();
            InputStream raw_dict = res.openRawResource(R.raw.english_indonesia);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;
            do{
                line = reader.readLine();
                String[] splitstr = line.split("\t");

                KamusModel kamusModel;

                kamusModel = new KamusModel(splitstr[0], splitstr[1]);
                kamusModels.add(kamusModel);
                count++;
            }while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kamusModels;
    }

    private ArrayList<KamusIndoModel> preLoadIndoRaw() {
        ArrayList<KamusIndoModel> kamusIndoModels = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try {
            Resources res = getResources();
            InputStream raw_dict = res.openRawResource(R.raw.indonesia_english);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;
            do{
                line = reader.readLine();
                String[] splitstr = line.split("\t");

                KamusIndoModel kamusIndoModel;

                kamusIndoModel = new KamusIndoModel(splitstr[0], splitstr[1]);
                kamusIndoModels.add(kamusIndoModel);
                count++;
            }while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kamusIndoModels;
    }
}