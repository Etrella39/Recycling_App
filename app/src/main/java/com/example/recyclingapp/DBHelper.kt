package com.example.recyclingapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Login.db", null, 2) {

    // users 테이블 생성
    override fun onCreate(MyDB: SQLiteDatabase?) {
        MyDB!!.execSQL("CREATE TABLE users(id TEXT PRIMARY KEY, password TEXT, joined_date TEXT)")
    }


    // 정보 갱신
    override fun onUpgrade(MyDB: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            MyDB!!.execSQL("ALTER TABLE users ADD COLUMN joined_date TEXT")
        }
    }

    // id, password 삽입 (성공시 true, 실패시 false)
    fun insertData(id: String?, password: String?): Boolean {
        val MyDB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("id", id)
        contentValues.put("password", password)

        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val currentDate = sdf.format(Date())
        contentValues.put("joined_date", currentDate)

        val result = MyDB.insert("users", null, contentValues)
        MyDB.close()
        return result != -1L
    }

    //joined date
    @SuppressLint("Range")
    fun getJoinedDate(userId: String): String? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT joined_date FROM users WHERE id = ?", arrayOf(userId))
        var joinedDate: String? = null

        if (cursor.moveToFirst()) {
            joinedDate = cursor.getString(cursor.getColumnIndex("joined_date"))
        }

        cursor.close()
        return joinedDate
    }



    // 사용자 아이디가 없으면 false, 이미 존재하면 true
    @SuppressLint("Recycle")
    fun checkUser(id: String?): Boolean {
        val MyDB = this.readableDatabase
        var res = true
        val cursor = MyDB.rawQuery("Select * from users where id =?", arrayOf(id))
        if (cursor.count <= 0) res = false
        cursor.close()
        return res
    }

    // 해당 id, password가 있는지 확인 (없다면 false)
    fun checkUserpass(id: String, password: String): Boolean {
        val MyDB = this.writableDatabase
        var res = true
        val cursor = MyDB.rawQuery(
            "Select * from users where id = ? and password = ?",
            arrayOf(id, password)
        )
        if (cursor.count <= 0) res = false
        cursor.close()
        return res
    }

    // db for finding password
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

//    private fun connectToDatabase(): Connection? {
//        val url = resources.getString(R.string.db_url)
//        val user = resources.getString(R.string.db_user)
//        val password = resources.getString(R.string.db_password)
//
//        return try {
//            DriverManager.getConnection(url, user, password)
//        } catch (e: SQLException) {
//            null
//        }
//    }

    // id 삭제
    fun deleteUser(id: String): Boolean {
        val MyDB = this.writableDatabase
        val result = MyDB.delete("users", "id = ?", arrayOf(id))
        MyDB.close()
        return result > 0
    }

    // DB name을 Login.db로 설정
    companion object {
        const val DBNAME = "Login.db"
    }
}
