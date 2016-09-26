package com.example.abhinay.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by abhinay on 25/09/16.
 */

public class DBHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "Todo.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TODO_TABLE_NAME = "todo";
    public static final String TODO_COLUMN_ID = "_id";
    public static final String TODO_COLUMN_NAME = "name";
//    public static final String _COLUMN_GENDER = "gender";
//    public static final String PERSON_COLUMN_AGE = "age";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TODO_TABLE_NAME +
                        "(" + TODO_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        TODO_COLUMN_NAME + " TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TODO_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertItem(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TODO_COLUMN_NAME, name);

        db.insert(TODO_TABLE_NAME, null, contentValues);
        return true;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TODO_TABLE_NAME);
        return numRows;
    }

    public boolean updateItem(Integer id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TODO_COLUMN_NAME, name);

        db.update(TODO_TABLE_NAME, contentValues, TODO_COLUMN_ID + " = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteItem(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TODO_TABLE_NAME,
                TODO_COLUMN_ID + " = ? ",
                new String[] { Integer.toString(id) });
    }

    public Cursor getItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + TODO_TABLE_NAME + " WHERE " +
                TODO_COLUMN_ID + "=?", new String[]{Integer.toString(id)});
        return res;
    }

    public Cursor getAllItems() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + TODO_TABLE_NAME, null );
        return res;
    }
}