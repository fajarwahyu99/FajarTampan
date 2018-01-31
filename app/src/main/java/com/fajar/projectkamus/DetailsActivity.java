package com.fajar.projectkamus;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    public static String EXTRA_WORDS = "extra_words";
    public static String EXTRA_DETAILS = "extra_details";

    public TextView textViewWord, textViewDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.details);

        String words = getIntent().getStringExtra(EXTRA_WORDS);
        String detail_word = getIntent().getStringExtra(EXTRA_DETAILS);

        textViewWord = findViewById(R.id.txt_words_detail);
        textViewDetail = findViewById(R.id.txt_details_detail);

        textViewWord.setText(words);
        textViewDetail.setText(detail_word);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if(i == android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);

    }
}
