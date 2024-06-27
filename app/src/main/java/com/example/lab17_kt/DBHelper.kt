package com.example.lab17_kt

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DBHelper(private val context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null,
    DATABASE_VERSION
) {
    companion object {
        const val DATABASE_NAME = "lab17.sqlite"
        const val DATABASE_VERSION = 2
        const val INCOME_CREATE_DDL = ("CREATE TABLE INCOME_MAIN ("
                + "_ID INTEGER PRIMARY KEY,"
                + "INCOME_DESCRIPTION TEXT);")
        const val INCOME_DELETE_DDL = "DROP TABLE IF EXISTS INCOME_MAIN;"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        Toast.makeText(context, "db created, check sandbox", Toast.LENGTH_LONG).show()
        db!!.execSQL(INCOME_CREATE_DDL);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Toast.makeText(context, "db upgraded, check sandbox", Toast.LENGTH_LONG).show()
        db!!.execSQL(INCOME_DELETE_DDL);
        db.execSQL(INCOME_CREATE_DDL)
    }
}