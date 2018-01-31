package com.fajar.projectkamus.Database;

import android.provider.BaseColumns;



public class DatabaseContract {
    static String TABLE_ENGLISH_INDONESIA = "table_english_indonesia";
    static String TABLE_INDONESIA_ENGLISH = "table_indonesia_english";

    static final class EnglishIndonesiaColumn implements BaseColumns{

        static String WORDS_ENGLISH = "words_english";

        static String DETAILS_ENGLISH = "details_english";
    }

    static final class IndonesiaEnglishColumn implements BaseColumns{
        static String WORDS_INDONESIA = "words_indonesia";

        static String DETAILS_INDONESIA = "details_indonesia";
    }
}
