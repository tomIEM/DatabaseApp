package com.levy.tom.databaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by util on 02/10/2015.
 */
public class MessageDAO extends DAOBase{
    public static final String TABLE_NAME = "Message";
    public static final String KEY = "id";
    public static final String CONTENT = "content";
    public static final String DATE = "date_creation";

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CONTENT + " TEXT, " + DATE + " INTEGER);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public MessageDAO(Context pContext) {
        super(pContext);
    }

    public void ajouter(Message m) {
        ContentValues value = new ContentValues();
        value.put(MessageDAO.CONTENT, m.getContent());
        value.put(MessageDAO.DATE, String.valueOf(m.getTimestamp()));
        mDb.insert(MessageDAO.TABLE_NAME, null, value);

    }

    public void supprimer(long id) {
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[]{String.valueOf(id)});
    }

    public void modifier(Message m) {
        ContentValues value = new ContentValues();
        value.put(CONTENT, m.getContent());
        mDb.update(TABLE_NAME, value, KEY + " = ?", new String[]{String.valueOf(m.getId())});

    }

    public Message get(long id) {

        List<Message> result = null;
        Cursor query = mDb.rawQuery("select id as _id, content, date_creation from " + TABLE_NAME + " where id = ?", new String[]{String.valueOf(id)});
            query.moveToFirst();
            id = query.getLong(0);
            String content = query.getString(1);
            Long date_creation = query.getLong(2);
            Message m = new Message (id, content, date_creation);

            query.close();

        return m;
    }

    public ArrayList<Message> getAll() {

        ArrayList<Message> result = new ArrayList<Message>();
        Message m = null;
        Cursor query = mDb.rawQuery("select id as _id, content, date_creation from " + TABLE_NAME+" ORDER BY date_creation DESC" , new String[]{});
        while (query.moveToNext()) {
            Log.d("TEST", String.valueOf(query.getString(0)));
            Log.d("TEST", String.valueOf(query.getString(1)));
            Log.d("TEST", String.valueOf(query.getInt(2)));
            long id = query.getLong(0);
            String content = query.getString(1);
            Long date_creation = query.getLong(2);
            m = new Message (id, content, date_creation);
            result.add(m);
        }
        query.close();

        return result;
    }
}
