package com.example.pdmd_examenfinal_raul_villodres;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelper extends SQLiteOpenHelper {
        private static final String NOMBRE_BASE_DATOS = "enlaces";
        private static final String NOMBRE_TABLA_DATOS = "enlaces";
        private static final int VERSION_BASE_DATOS = 1;

        public dbHelper(Context context){
            super(context, NOMBRE_BASE_DATOS, null, VERSION_BASE_DATOS);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(String.format("CREATE TABLE IF NOT EXISTS enlaces(id integer primary key autoincrement, nombre text, link text, email text, categoria text)"));
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldversion, int newVersion){

        }
    }

