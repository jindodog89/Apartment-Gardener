package com.example.choi.apartmentgardener;

/**
 * Created by choi on 11/30/17.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "AGDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USERS = "users";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String EMAIL= "email";
    private static final String ACT_TYPE = "act_type";
    private static final String TABLE_LISTINGS = "listings";
    private static final String LOCATION = "location";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String ID = "id";

    public DatabaseManager (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        String sqlCreate = "create table " + TABLE_USERS + "( ";
        sqlCreate += USERNAME + " varchar(255) primary key, ";
        sqlCreate += PASSWORD + " varchar(255), " + EMAIL + " varchar(255), ";
        sqlCreate += ACT_TYPE + " varchar(26))";

        db.execSQL(sqlCreate);

        sqlCreate = "create table " + TABLE_LISTINGS + "( ";
        sqlCreate += ID + " integer primary key autoincrement, ";
        sqlCreate += USERNAME + " varchar(255), ";
        sqlCreate += LOCATION + " varchar(255), " + TITLE + " varchar(255), ";
        sqlCreate += DESCRIPTION + " varchar(1024))";

        db.execSQL(sqlCreate);

        //db.close();
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists " + TABLE_USERS);
        db.execSQL("drop table if exists " + TABLE_LISTINGS);
        onCreate(db);
    }

    public void insertUser (User user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_USERS;
        sqlInsert += " values('" + user.getUsername() + "', ";
        sqlInsert += "'" + password + "', ";
        sqlInsert += "'" + user.getEmail() + "', ";
        sqlInsert += "'" + user.getAct_type() + "' )";

        db.execSQL(sqlInsert);
        db.close();
    }

    public void insertListing (Listing listing){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_LISTINGS;
        sqlInsert += " values(null, '" + listing.getUser() + "', ";
        sqlInsert += "'" + listing.getLocation() + "', ";
        sqlInsert += "'" + listing.getTitle() + "', ";
        sqlInsert += "'" + listing.getDescription() + "' )";

        db.execSQL(sqlInsert);
        db.close();
    }

    public void deleteListing (Listing listing){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " + TABLE_LISTINGS;
        sqlDelete += " where " + ID + " = " + listing.getId();

        db.execSQL(sqlDelete);
        db.close();
    }

    public void updateListing (Listing listing){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlUpdate = "update " + TABLE_LISTINGS;
        sqlUpdate += " set " + USERNAME + " = '" + listing.getUser()+ "'";
        sqlUpdate += " and " + LOCATION + " = '" + listing.getLocation()+ "'";
        sqlUpdate += " and " + TITLE + " = '" + listing.getTitle()+ "'";
        sqlUpdate += " and " + DESCRIPTION + " = '" + listing.getDescription()+ "'";
        sqlUpdate += " where " + ID + " = " + listing.getId();

        db.execSQL(sqlUpdate);
        db.close();
    }

    public ArrayList<Listing> selectAllListings (){
        String sqlQuery = "select * from " + TABLE_LISTINGS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Listing> listings = new ArrayList<Listing>();

        while(cursor.moveToNext()){
            Listing curListing = new Listing(cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4),
                    Integer.parseInt(cursor.getString(0)));

            listings.add(curListing);
        }
        db.close();
        return listings;
    }

    public ArrayList<Listing> selectListingsByUser (User user){
        String sqlQuery = "select * from " + TABLE_LISTINGS;
        sqlQuery += " where " + USERNAME + " = '" + user.getUsername() + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Listing> listings = new ArrayList<Listing>();

        while(cursor.moveToNext()){
            Listing curListing = new Listing(cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4),
                    Integer.parseInt(cursor.getString(0)));

            listings.add(curListing);
        }
        db.close();
        return listings;
    }

    public boolean checkPassword (String username, String password){
        if(username.equals(""))
            return false;
        else if(password.equals(""))
            return false;
        String sqlQuery = "select * from " + TABLE_USERS;
        sqlQuery += " where " + USERNAME + " = '" + username + "'";
        sqlQuery += " and " + PASSWORD + " = '" + password + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        int count = cursor.getCount();

        db.close();
        return (count > 0);
    }

    public boolean checkUsername (String username){
        String sqlQuery = "select * from " + TABLE_USERS;
        sqlQuery += " where " + USERNAME + " = '" + username + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        int count = cursor.getCount();

        db.close();
        return (count > 0);
    }

    public boolean checkEmail (String email){
        String sqlQuery = "select * from " + TABLE_USERS;
        sqlQuery += " where " + EMAIL + " = '" + email + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        int count = cursor.getCount();

        db.close();
        return (count > 0);
    }

    public User getUser (String username){
        String sqlQuery = "select * from " + TABLE_USERS;
        sqlQuery += " where " + USERNAME + " = '" + username + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        cursor.moveToFirst();

        User curUser = new User(cursor.getString(0), cursor.getString(3),
                cursor.getString(2));

        db.close();
        return curUser;
    }

    public Listing selectListingById(int id){
        String sqlQuery = "select * from " + TABLE_LISTINGS;
        sqlQuery += " where " + ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        cursor.moveToFirst();

        Listing curListing = new Listing(cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4),
                Integer.parseInt(cursor.getString(0)));

        db.close();
        return curListing;
    }
}