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
    fun deleteUserr(id: String){
        val db= this.writableDatabase
        db.delete(table_user,"${ID}=?", arrayOf(id))

    }
    fun updateuser(id: String,user: User) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, id)
        contentValues.put(username, user.username)
        db.update(table_user, contentValues, "${ID}=?", arrayOf(id))

        fun getuserbyid(id: Int) {
            var query = "select * from ${table_user} where ${ID}=${id}"
            var db = this.readableDatabase
            var curosr = db.rawQuery(query, null)
        }
    }

}
