package com.levy.tom.databaseapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by util on 02/10/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {


    public static final String MESSAGE_KEY = "id";
    public static final String MESSAGE_CONTENT = "content";
    public static final String MESSAGE_DATE = "date_creation";

    public static final String MESSAGE_TABLE_NAME = "Message";
    public static final String MESSAGE_TABLE_CREATE =
            "CREATE TABLE " + MESSAGE_TABLE_NAME + " (" +
                    MESSAGE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    MESSAGE_CONTENT + " TEXT, " +
                    MESSAGE_DATE + " int);";
    public static final String MESSAGE_TABLE_DROP = "DROP TABLE IF EXISTS " + MESSAGE_TABLE_NAME + ";";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MESSAGE_TABLE_CREATE);
    }

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MESSAGE_TABLE_DROP);
        onCreate(db);
    }
}
