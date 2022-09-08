package com.example.crudsqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String USER_ID = "userID";
    public static final String USER_EMAIL = "userEmail";
    public static final String USER_PASSWORD = "userPassword";



    public DBHelper(@Nullable Context context) {
        super(context, "MyDB.db", null, 1);
    }

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public DBHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String createTableSTatementOne = "CREATE TABLE CustTable(CustomerID Integer PRIMARY KEY AUTOINCREMENT, " + CUSTOMER_NAME_FIRST + " Text, CustomerAge Int, ActiveCustomer BOOL) ";
        String createTableSTatement = "CREATE TABLE userTable" + "(" + USER_ID + " Integer PRIMARY KEY AUTOINCREMENT, " + USER_EMAIL + " Text, " + USER_PASSWORD + " Text" + ")";
        db.execSQL(createTableSTatement);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS userTable");
        onCreate(db);
    }
    public void addUser(userModel u){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_EMAIL, u.getEmail());
        cv.put(USER_PASSWORD, u.getPassword());
        long insert = db.insert("userTable", null, cv);
        if(insert == -1){
            System.out.println("Error");
        }
        else{
            System.out.println("Success");
        }
    }

    public ArrayList<userModel> getAllUsers() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorCourses = db.rawQuery("SELECT * FROM userTable" , null);

        ArrayList<userModel> userArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {

                userArrayList.add(new userModel(cursorCourses.getInt(0),cursorCourses.getString(1), cursorCourses.getString(2) ));
            } while (cursorCourses.moveToNext());

        }

        cursorCourses.close();
        return userArrayList;
    }

    public void deleteUser(userModel u){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("userTable", "userId = ?", new String[]{String.valueOf(u.getId())});
        db.close();
    }

    public void updateUser(userModel u){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_EMAIL, u.getEmail());
        cv.put(USER_PASSWORD, u.getPassword());
        db.update("userTable", cv, "userId = ?", new String[]{String.valueOf(u.getId())});
        db.close();
    }

}