package com.example.telemedecin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "telemedecin.db";
    public static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT, email TEXT, password TEXT)");
        db.execSQL("CREATE TABLE rendezvous(id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, heure TEXT, motif TEXT, user_id INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS rendezvous");
        onCreate(db);
    }

    public boolean insertUser(String nom, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nom", nom);
        values.put("email", email);
        values.put("password", password);
        long res = db.insert("users", null, values);
        return res != -1;
    }

    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email=? AND password=?", new String[]{email, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public int getUserId(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM users WHERE email=? AND password=?", new String[]{email, password});
        if (cursor.moveToFirst()) {
            int userId = cursor.getInt(0);
            cursor.close();
            return userId;
        }
        cursor.close();
        return -1;
    }

    public boolean insertRendezVous(String date, String heure, String motif, int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date", date);
        values.put("heure", heure);
        values.put("motif", motif);
        values.put("user_id", userId);
        long res = db.insert("rendezvous", null, values);
        return res != -1;
    }

    // 🔍 Correction des appels getColumnIndex → getColumnIndexOrThrow
    public List<RendezVous> getAllRendezVous(int userId) {
        List<RendezVous> rendezVousList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM rendezvous WHERE user_id = ?", new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {
            do {
                // Utilisez getColumnIndexOrThrow() pour éviter -1
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                String heure = cursor.getString(cursor.getColumnIndexOrThrow("heure"));
                String motif = cursor.getString(cursor.getColumnIndexOrThrow("motif"));
                rendezVousList.add(new RendezVous(id, date, heure, motif));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return rendezVousList;
    }

    public boolean deleteRendezVous(int rendezVousId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete("rendezvous", "id = ?", new String[]{String.valueOf(rendezVousId)});
        db.close();
        return rowsDeleted > 0;
    }
}