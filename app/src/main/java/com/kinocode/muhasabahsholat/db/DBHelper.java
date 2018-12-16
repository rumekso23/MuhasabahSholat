package com.kinocode.muhasabahsholat.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String db_name = "muhasabahsholat.db";
    public static final String TABLE_NAME = "evaluasi_sholat";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TANGGAL = "tanggal";
    public static final String COLUMN_SHOLAT = "sholat";
    public static final String COLUMN_STATUS = "status";
    private static final int db_version = 1;


    private static final String db_create = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID +" integer primary key autoincrement, "
            + COLUMN_TANGGAL + " varchar(50) unique not null, "
            + COLUMN_SHOLAT + " varchar(50) not null, "
            + COLUMN_STATUS + " varchar(50));";

    public DBHelper(Context context){
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),"Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData( String tanggal, String sholat, String status){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TANGGAL, tanggal);
        contentValues.put(COLUMN_SHOLAT, sholat);
        contentValues.put(COLUMN_STATUS, status);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean updateData(String id, String tanggal, String sholat, String status){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, id);
        contentValues.put(COLUMN_TANGGAL, tanggal);
        contentValues.put(COLUMN_SHOLAT, sholat);
        contentValues.put(COLUMN_STATUS, status);
        db.update(TABLE_NAME, contentValues, "tanggal = ?", new String[] {tanggal});
        return true;
    }
}
