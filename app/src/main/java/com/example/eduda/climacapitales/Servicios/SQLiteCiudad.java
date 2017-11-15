package com.example.eduda.climacapitales.Servicios;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by eduda on 06/11/2017.
 */

public class SQLiteCiudad extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Clima.db";
    public static final String CIUDADES_TABLE_NAME = "ciudades";

    String sqlCrear = "CREATE TABLE " +  CIUDADES_TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre TEXT, key INTEGER)";

    public SQLiteCiudad(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCrear);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //se elimina version anterior de tabla
        db.execSQL("DROP TABLE IF EXISTS ciudades");
        //se crea nueva version de tabla
        db.execSQL(sqlCrear);
    }
}
