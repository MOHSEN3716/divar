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
        val Username = "username"
    }

    override fun onCreate(dp: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE $table_user($ID integer primary key autoincrement,$Username TEXT)"
        dp?.execSQL(createTable)
        Log.d("TAGXX","onResponse")
    }


    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun addUser(user: User) {
        var db= this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Username,user.username)
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
        contentValues.put(Username, user.username)
        db.update(table_user, contentValues, "${ID}=?", arrayOf(id))
    }
        fun getuserbyid(id: Int):String {
            var query = "select * from ${table_user} where ${ID}=${id}"
            var db = this.readableDatabase
            var curosr = db.rawQuery(query, null)
            curosr.moveToNext()
            val username=curosr.getString(curosr.getColumnIndexOrThrow(Username))
            return username
        }

    fun getallusername():List<String>{
        var query = "select * from ${table_user}"
        var db = this.readableDatabase
        val usernamearray= mutableListOf<String>()
        var curosr = db.rawQuery(query, null)
        while (curosr.moveToNext()){
                val username = curosr.getString(curosr.getColumnIndexOrThrow(Username))
                usernamearray.add(username)
            }
        return usernamearray

    }
}
