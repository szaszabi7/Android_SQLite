package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "tanulok.db";
    private static final int DB_VERSIOM = 1;

    private static final String TABLE_NAME = "tanulok";
    private static final String COL_ID = "id";
    private static final String COL_VEZNEV = "vezeteknev";
    private static final String COL_KERNEV = "keresztnev";
    private static final String COL_JEGY = "jegy";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSIOM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_VEZNEV + " TEXT NOT NULL, " +
                COL_KERNEV + " TEXT NOT NULL, " +
                COL_JEGY + " TEXT NOT NULL " +
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVerison) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean rogzites(String vezeteknev, String keresztnev, int jegy) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_VEZNEV, vezeteknev);
        values.put(COL_KERNEV, keresztnev);
        values.put(COL_JEGY, jegy);
        return db.insert(TABLE_NAME, null, values) != 1;
    }

    public Cursor listaz() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor eredmeny = db.query(TABLE_NAME, new String[]{COL_ID, COL_VEZNEV, COL_KERNEV, COL_JEGY},
                null, null, null, null, null);
        return eredmeny;
    }

    public int torles(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int eredmeny = db.delete(TABLE_NAME, COL_ID + " =?", new String[]{id});
        return eredmeny;
    }

    public int modositas(String id, String veznev, String kernev, String jegy) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_VEZNEV, veznev);
        values.put(COL_KERNEV, kernev);
        values.put(COL_JEGY, jegy);
        return  db.update(TABLE_NAME, values, COL_ID + "=?", new String[]{id});
    }
}
