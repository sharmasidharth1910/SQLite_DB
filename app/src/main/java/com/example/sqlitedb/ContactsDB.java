package com.example.sqlitedb;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactsDB
{
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "person_name";
    public static final String KEY_CELL = "_cell";

    private final String DATABASE_NAME = "ContactsDB";
    private final String DATABASE_TABLE = "ContactsTable";
    private final int DATABASE_VERSION = 1;

    private final Context ourContext;
    private DBHelper ourHelper;
    private SQLiteDatabase ourDatabase;

    public ContactsDB(Context context)
    {
        ourContext = context;
    }

    private class DBHelper extends SQLiteOpenHelper
    {

        public DBHelper (Context context)
        {
            super (context, DATABASE_NAME,null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            //CREATE TABLE ContactsTable (_id INTEGER PRIMARY KEY AUTOINCREMENT,
            //person_name TEXT NOT NULL, _cell TEXT NOT NULL);

            String sqlCode = "CREATE TABLE " + DATABASE_TABLE + " (" +
                    KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_NAME + " TEXT NOT NULL, " +
                    KEY_CELL + " TEXT NOT NULL);";
            db.execSQL(sqlCode);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);

        }
    }

    public ContactsDB open() throws SQLException
    {
        ourHelper = new DBHelper(ourContext);
        ourDatabase = ourHelper.getReadableDatabase();
        return this;
    }

    public void close()
    {
        ourHelper.close();
    }

    
}
