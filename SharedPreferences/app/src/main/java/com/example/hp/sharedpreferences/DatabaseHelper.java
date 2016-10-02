package com.example.hp.sharedpreferences;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HP on 02-10-2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Details.db";
    public static final String TABLE_NAME = "Details_table";
    public static final String uniqueID = "ID";
    public static final String sqname = "NAME";
    public static final String sqnative = "NATIVE_PLACE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, NATIVE_PLACE TEXT)");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DRP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String namein, String nativein){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(sqname,namein);
        contentValues.put(sqnative,nativein);
        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result==-1)
            return false;
        else
            return true;

    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+TABLE_NAME,null); // Cursor class provides random read write access to DB
        return result;

    }

    public boolean updateDate(String ID, String Name, String Native ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(uniqueID,ID);
        contentValues.put(sqname,Name);
        contentValues.put(sqnative,Native);
        db.update("TABLE_NAME",contentValues,"ID = ?", new String[]{ID });
        return true;
    }

    public Integer deleteData (String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {ID});

    }
}
