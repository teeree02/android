package com.example.projetmobile2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView; // Import for RecyclerView

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Restaurants.db";
    public static final String TABLE_NAME = "Restaurants_table";

    public static final String COL_1 = "idRestaurant";
    public static final String COL_2 = "nomRestaurant";
    public static final String COL_3 = "adresseRestaurant";
    public static final String COL_4 = "qualitePlats";
    public static final String COL_5 = "qualiteService";
    public static final String COL_6 = "prixMoyen";
    public static final String COL_7 = "nbEtoiles";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (idRestaurant INTEGER PRIMARY KEY ,nomRestaurant TEXT,adresseRestaurant TEXT,qualitePlats TEXT,qualiteService TEXT,prixMoyen REAL,nbEtoiles INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(Restaurant restaurant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, restaurant.getNomRestaurant());
        contentValues.put(COL_3, restaurant.getAdresseRestaurant());
        contentValues.put(COL_4, restaurant.getQualitePlats());
        contentValues.put(COL_5, restaurant.getQualiteService());
        contentValues.put(COL_6, restaurant.getPrixMoyen());
        contentValues.put(COL_7, restaurant.getNbEtoiles());
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME, null);
    }
    public boolean deleteRestaurantById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedRows = db.delete(TABLE_NAME, COL_1 + "=?", new String[]{String.valueOf(id)});
        return deletedRows > 0;
    }

    public boolean updateRestaurantById(int id, Restaurant restaurant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, restaurant.getNomRestaurant());
        contentValues.put(COL_3, restaurant.getAdresseRestaurant());
        contentValues.put(COL_4, restaurant.getQualitePlats());
        contentValues.put(COL_5, restaurant.getQualiteService());
        contentValues.put(COL_6, restaurant.getPrixMoyen());
        contentValues.put(COL_7, restaurant.getNbEtoiles());

        int updatedRows = db.update(TABLE_NAME, contentValues, COL_1 + "=?", new String[]{String.valueOf(id)});
        return updatedRows > 0;
    }
    public Cursor getRestaurantByName(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME + " where nomRestaurant=" + id, null);
    }
}
