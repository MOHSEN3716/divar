package com.example.divar.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class Databasehandler(context:Context)
    :SQLiteOpenHelper(context,"MYDATABASE",null,1)
{
    companion object {
        val table_user = "user"
        val ID = "id"
        val username = "username"
    }

    override fun onCreate(dp: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE $table_user($ID integer primary key autoincrement,$username TEXT)"
        dp?.execSQL(createTable)
        Log.d("TAGXX","onResponse")
    }


    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun addUser(user: User) {
        var db= this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(username,user.username)
        db.insert(table_user,null,contentValues)
    }

}
