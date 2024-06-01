
package com.example.recyclingapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Login.db", null, 1) {

    override fun onCreate(MyDB: SQLiteDatabase?) {
        MyDB!!.execSQL("create Table users(id TEXT primary key, password TEXT)")
    }

    override fun onUpgrade(MyDB: SQLiteDatabase?, i: Int, i1: Int) {
        MyDB!!.execSQL("drop Table if exists users")
    }

    fun insertData(id: String?, password: String?): Boolean {
        val MyDB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("id", id)
        contentValues.put("password", password)
        val result = MyDB.insert("users", null, contentValues)
        MyDB.close()
        return result != -1L
    }

    @SuppressLint("Recycle")
    fun checkUser(id: String?): Boolean {
        val MyDB = this.readableDatabase
        val cursor = MyDB.rawQuery("Select * from users where id =?", arrayOf(id))
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }

    fun checkUserpass(id: String, password: String): Boolean {
        val MyDB = this.writableDatabase
        val cursor = MyDB.rawQuery(
            "Select * from users where id = ? and password = ?",
            arrayOf(id, password)
        )
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }

    @SuppressLint("Range")
    fun findPasswordByUserId(userId: String): String? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT password FROM users WHERE id = ?", arrayOf(userId))
        var password: String? = null

        if (cursor.moveToFirst()) {
            password = cursor.getString(cursor.getColumnIndex("password"))
        }

        cursor.close()
        return password
    }

    companion object {
        const val DBNAME = "Login.db"
    }
}



