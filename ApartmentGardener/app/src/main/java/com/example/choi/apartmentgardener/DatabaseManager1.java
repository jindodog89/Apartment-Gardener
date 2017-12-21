package com.example.choi.apartmentgardener;

/**
 * Created by choi on 11/30/17.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class DatabaseManager1 extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "taskholderDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TASKHOLDER = "taskholder";
    private static final String ID = "id";
    private static final String TITLE = "taskinformation";
    private static final String DESCRIPTION = "duedate";
    private static final String LOCATION = "location";
    //private static final String EMAILADDRESS = "emailaddress";

    public DatabaseManager1( Context context ) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    //must override
    public void onCreate( SQLiteDatabase db ) {
        // Create the task holder table
        String sqlCreate = "create table " + TABLE_TASKHOLDER + "( " + ID;
        sqlCreate += " integer primary key autoincrement, " + TITLE;
        sqlCreate += " text, " + DESCRIPTION + " text, " + LOCATION;
        sqlCreate += " text ) ";
        db.execSQL(sqlCreate);
    }

    //must override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop old table if it exists and recreates
        db.execSQL("drop table if exists " + TABLE_TASKHOLDER);
        onCreate( db );
    }

    public void insert(LandOwnerInfo taskholder) {
        Log.w( "DatabaseManager1", "insert" );
        SQLiteDatabase db = this.getWritableDatabase( );

        String sqlInsert = "insert into " + TABLE_TASKHOLDER;
        sqlInsert += " values( null, '" + taskholder.getTitle();
        sqlInsert += "', '" + taskholder.getDescription();
        sqlInsert += "', '" + taskholder.getLocation() + "' )";
        db.execSQL( sqlInsert );
        db.close( );
    }

//    public void deleteById( int id ) {
//        SQLiteDatabase db = this.getWritableDatabase( );
//        String sqlDelete = "delete from " + TABLE_TASKHOLDER;
//        sqlDelete += " where " + ID + " = " + id;
//        db.execSQL( sqlDelete );
//        db.close( );
//    }
//
//    //Test
//    public void deleteAllById() {
//        SQLiteDatabase db = this.getWritableDatabase( );
//        String sqlDeleteAll = "delete * from " + TABLE_TASKHOLDER;
//        db.execSQL( sqlDeleteAll );
//        db.close( );
//    }


//    public void updateById( int id, String title, String description, String location) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        String sqlUpdate = "update " + TABLE_TASKHOLDER;
//        sqlUpdate += " set "      + TITLE       + " = '" + title + "', ";
//        sqlUpdate += DESCRIPTION      + " = '" + description      + "' " + ", ";
//        sqlUpdate += LOCATION         + " = '" + location         + "' ";
//        //sqlUpdate += EMAILADDRESS + " = '" + emailaddress + "' ";
//        sqlUpdate += " where " + ID + " = " + id;
//        db.execSQL( sqlUpdate );
//        db.close( );
//    }

//    public void updateByIdTrial( int id, String taskinformation){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        String sqlUpdate = "update " + TABLE_TASKHOLDER + " set " + TASKINFORMATION + " = '" + taskinformation + "' where " + ID + " = " + id;
//        db.execSQL( sqlUpdate );
//        db.close( );
//    }

    public LandOwnerInfo selectById( int id ) {
        String sqlQuery = "select * from " + TABLE_TASKHOLDER;

        sqlQuery += " where " + ID + " = " + id;
        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );
        LandOwnerInfo taskholder = null;
        if( cursor.moveToFirst( ) )
            taskholder = new LandOwnerInfo( Integer.parseInt( cursor.getString( 0 ) ),
                    cursor.getString( 1 ), cursor.getString( 2 ), cursor.getString( 3 ));

        return taskholder;
    }

    public ArrayList<LandOwnerInfo> selectAll( ) {
        String sqlQuery = "select * from " + TABLE_TASKHOLDER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        ArrayList<LandOwnerInfo> tasks = new ArrayList<LandOwnerInfo>();
        while (cursor.moveToNext()) {
            LandOwnerInfo currentCandy = new LandOwnerInfo(Integer.parseInt(cursor.getString(0)),

                    cursor.getString(1), cursor.getString(2), cursor.getString(3) );

            tasks.add(currentCandy);
        }
        db.close();
        return tasks;
    }



}
