package com.example.bikematerialiser

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class LocationDatabase(context: Context) : SQLiteOpenHelper(context, "locations", null, 1) {
    /**
     * Called when the database is created for the first time.
     * A table called locations is created.
     * It contains an id column, a location column and a datetime column.
     *
     * @param db The database.
     */
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE locations(id INTEGER PRIMARY KEY AUTOINCREMENT, location TEXT, datetime TEXT)")
        db?.execSQL("INSERT INTO locations(location, datetime) VALUES('Stieltjesweg (TNO side)', '20:04 24/04/2023')")
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     *
     *
     * The SQLite ALTER TABLE documentation can be found
     * [here](http://sqlite.org/lang_altertable.html). If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     *
     *
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     *
     *
     * @param db The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}