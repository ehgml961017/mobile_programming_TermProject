package com.example.termproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 박도희 on 2016-12-03.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Timer(idx primary autoincrement integer, category text, name text);"); //timer table 생성
        db.execSQL("create table Log(idx primary autoincrement integer, category text, name text, note text, pic text, latitude real, longitude real, startTime datatime, endTime datatime, leadTime real);"); //log table 생성.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
