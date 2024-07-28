package com.smart.medicinedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MedicineHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME="MEDICINE.db";
    public static String MEDICINE_TABLE="Medicine";
    public static String col1 = "name";
    public static String col2 = "date";
    public static String col3 = "time";

    public MedicineHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Medicine(name TEXT,date TEXT,time TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
