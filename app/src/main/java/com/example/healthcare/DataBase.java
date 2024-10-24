package com.example.healthcare;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    public DataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT UNIQUE NOT NULL,email TEXT NOT NULL,password TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        onCreate(sqLiteDatabase);
    }

    public String register(String username,String email,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        String select="SELECT * FROM users WHERE username=? AND email=? AND password=?;";
        if(db.rawQuery(select,new String[]{username,email,password}).getCount()>0){
            return "already registered";
        }

        String query="INSERT INTO users(username,email,password) VALUES(?,?,?);";
        try{
            db.execSQL(query,new String[]{username,email,password});
            return "registered";
        }catch (Exception e){
            return "failed";
        }
    }

    public boolean login(String username,String password){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM users WHERE username=? AND password =?;";
        Cursor cursor=db.rawQuery(query,new String[]{username,password});
        boolean auth=cursor.getCount()>0;
        cursor.close();
        return auth;
    }
}
