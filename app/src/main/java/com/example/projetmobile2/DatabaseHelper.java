package com.example.projetmobile2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Restaurants.db";
    public static final String TABLE_NAME="Restaurants_table";

    public static final String COL_1="idRestaurant";
    public static final String COL_2="nomRestaurant";
    public static final String COL_3="adresseRestaurant";
    public static final String COL_4="qualitePlats";
    public static final String COL_5="qualiteService";
    public static final String COL_6="prixMoyen";
    public static final String COL_7="nbEtoiles";

    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" (idRestaurant INTEGER PRIMARY KEY AUTOINCREMENT,nomRestaurant TEXT,adresseRestaurant TEXT,qualitePlats TEXT,qualiteService TEXT,prixMoyen REAL,nbEtoiles INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String nomres,String adress, String plat,String service,float prix,int etoiles){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,nomres);
        contentValues.put(COL_3,adress);
        contentValues.put(COL_4,plat);
        contentValues.put(COL_5,service);
        contentValues.put(COL_6,prix);
        contentValues.put(COL_7,etoiles);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}
