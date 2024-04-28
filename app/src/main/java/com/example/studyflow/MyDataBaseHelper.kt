package com.example.studyflow

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

private const val dataBaseName: String = "Plans"
private const val tableName: String = "toDoPlans"
private const val col_plan: String = "Plan"


class MyDataBaseHelper(private var context: Context): SQLiteOpenHelper(context, dataBaseName,
        null,1) {

    override fun onCreate(db: SQLiteDatabase?) {

        val quarry: String = " CREATE TABLE " +
                tableName + " (" + col_plan + " TEXT);"
        db?.execSQL(quarry)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun addItem(plan: String) {

        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(col_plan,plan)
        val result = db.insert(tableName,null,cv)
        if (result == (-1).toLong()) {
            Toast.makeText(context,"Add Operation is Denied", Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText(context,"Add Operation is successfully", Toast.LENGTH_LONG).show()
        }

    }

    fun readAllElements(): Cursor? {

        val db = this.readableDatabase
        val quary = " SELECT * FROM " + tableName
        var cursor: Cursor? = null
        if (db != null) {
            cursor = db.rawQuery(quary,null)
        }
        return cursor
    }

}