package com.mingrisoft.dao;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mingrisoft.model.Tb_inaccount;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/27.
 */
public class inaccountDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    public inaccountDAO(Context context){
        helper=new DBOpenHelper(context);
    }
    public void add(Tb_inaccount tb_inaccount){
        db=helper.getWritableDatabase();
        db.execSQL("insert into tb_inaccount(_id,money,time,type,handler,mark) values(?,?,?,?,?,?)",
                new Object[]{tb_inaccount.get_id(),tb_inaccount.getMoney(),tb_inaccount.getTime(),
                tb_inaccount.getType(),tb_inaccount.getHandler(),tb_inaccount.getMark()});
    }




    public void update(Tb_inaccount tb_inaccount) {
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        // 执行修改收入信息操作
        db.execSQL(
                "update tb_inaccount set money = ?,time = ?,type = ?,handler = ?,mark = ? where _id = ?",
                new Object[] { tb_inaccount.getMoney(), tb_inaccount.getTime(),
                        tb_inaccount.getType(), tb_inaccount.getHandler(),
                        tb_inaccount.getMark(), tb_inaccount.get_id() });
    }


    public void delete(Integer... ids){
        if(ids.length>0){
            StringBuffer sb=new StringBuffer();
            for(int i=0;i<ids.length;i++){
                sb.append("?").append(",");

            }
            sb.deleteCharAt(sb.length()-1);
            db=helper.getWritableDatabase();
            db.execSQL("delete from tb_inaccount where _id in ("+sb+")",(Object[])ids);
        }

    }



    public Tb_inaccount find(int id){
        db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select _id,money,time,type,handler,mark from tb_inaccount where _id=?",new String[]
                {String.valueOf(id)});
        while(cursor.moveToNext()){
            return new Tb_inaccount(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("handler")),
                    cursor.getString(cursor.getColumnIndex("mark")));

        }
        return  null;
    }


    public List<Tb_inaccount> getScrollData(int start, int count){
        List<Tb_inaccount> tb_inaccount=new ArrayList<Tb_inaccount>();
        db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from tb_inaccount limit ?,?",new String[]
                {String.valueOf(start),String.valueOf(count)});
        while(cursor.moveToNext()){
             tb_inaccount.add(new Tb_inaccount(cursor.getInt(cursor.getColumnIndex("_id")),
                     cursor.getDouble(cursor.getColumnIndex("money")),
                     cursor.getString(cursor.getColumnIndex("time")),
                     cursor.getString(cursor.getColumnIndex("type")),
                     cursor.getString(cursor.getColumnIndex("handler")),
                     cursor.getString(cursor.getColumnIndex("mark"))));

        }
        return  tb_inaccount;
    }
    public long getCount(){
        db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select count(_id)from tb_inaccount",null);
        while(cursor.moveToNext())
            return cursor.getLong(0);
        return 0;
    }

    public int getMaxId(){
        db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select max(_id) from tb_inaccount",null);
        while(cursor.moveToLast()){
            return cursor.getInt(0);

        }
        return  0;
    }

}
