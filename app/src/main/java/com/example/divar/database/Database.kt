package com.example.divar.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.divar.more.place

class Database(context:Context)
    :SQLiteOpenHelper(context,"MYDIVARDATABASE",null,1)
{
    companion object {
        val table_place = "place"
        val Title = "title"
        val ID = "id"
        val price = "price"
        val Imageadrese = "imageadrese"
        val Metr = "metr"
        val Createdat = "createdat"
    }

    override fun onCreate(dp: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE ${table_place} ( $ID integer primary key autoincrement, " +
                    "${Title} text, "+
                    "${price} integer, "+
                    "${Imageadrese} integer, "+
                    "${Metr} integer, "+
                    "${Createdat} text ) "
        dp?.execSQL(createTable)
        Log.d("TAGXX","onResponse")
    }


    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }


    fun addplace(place:place) {
        var db= this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Title,place.title)
        contentValues.put(price,place.price)
        contentValues.put(Imageadrese,place.imageadrese)
        contentValues.put(Metr,place.metr)

        db.insert(table_place,null,contentValues)
    }
//    fun deleteUserr(id: String){
//        val db= this.writableDatabase
//        db.delete(table_user,"${ID}=?", arrayOf(id))
//
//    }
//    fun updateuser(id: String,user: User) {
//        val db = this.writableDatabase
//        val contentValues = ContentValues()
//        contentValues.put(ID, id)
//        contentValues.put(Username, user.username)
//        db.update(table_user, contentValues, "${ID}=?", arrayOf(id))
//    }
//        fun getuserbyid(id: Int):String {
//            var query = "select * from ${table_user} where ${ID}=${id}"
//            var db = this.readableDatabase
//            var curosr = db.rawQuery(query, null)
//            curosr.moveToNext()
//            val username=curosr.getString(curosr.getColumnIndexOrThrow(Username))
//            return username
//        }
//
//    fun getallusername():List<String>{
//        var query = "select * from ${table_user}"
//        var db = this.readableDatabase
//        val usernamearray= mutableListOf<String>()
//        var curosr = db.rawQuery(query, null)
//        while (curosr.moveToNext()){
//                val username = curosr.getString(curosr.getColumnIndexOrThrow(Username))
//                usernamearray.add(username)
//            }
//        return usernamearray
//
//    }
}
