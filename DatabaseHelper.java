package com.example.lakepulse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "GLOF.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_ALERTS = "alerts";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MESSAGE = "message";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_ALERTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_MESSAGE + " TEXT, " +
                    COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALERTS);
        onCreate(db);
    }

    public Cursor getAllAlerts() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_ALERTS + " ORDER BY " + COLUMN_TIMESTAMP + " DESC", null);
    }
}
