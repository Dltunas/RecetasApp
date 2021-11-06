package com.example.apprecetas
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase.CursorFactory


class AdminSqlLite (context: Context, name: String, factory: CursorFactory?, version: Int): SQLiteOpenHelper(context, name, factory, version){

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table recetas (titulo text primary key, cuerpo text, referencia text, tipo text)")
    }

    override fun onUpgrade(db:SQLiteDatabase, oldVersion:Int, KotlinVersion: Int){

    }

}
