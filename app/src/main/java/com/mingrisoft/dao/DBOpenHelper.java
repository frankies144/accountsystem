package com.mingrisoft.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/2/27.
 */
public class DBOpenHelper extends SQLiteOpenHelper{
    private static final int VERSION=1;
    private static  final String NAME="account.db";
    public DBOpenHelper(Context context){
        super(context,NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tb_flag(_id integer primary key,flag varchar(200))");
        db.execSQL("create table tb_inaccount(_id integer primary key,money decimal,time varchar(10)," +
                "type varchar(10),handler varchar(100),mark varchar(20))");
        db.execSQL("create table tb_outaccount(_id integer primary key,money decimal,time varchar(10)," +
                "type varchar(10),address varchar(100),mark varchar(20))");
        db.execSQL("create table tb_pwd(password varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
