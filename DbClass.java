package com.example.databasecrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbClass extends SQLiteOpenHelper {
    public DbClass( Context context) {
        super(context, "Userdata", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table UserDetails(name TEXT primary key,contact TEXT,dob TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists UserDetails");
    }

    public Boolean insertUserData(String name,String contact,String dob){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("contact",contact);
        contentValues.put("dob",dob);
        long result=DB.insert("UserDetails",null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }


    public Boolean updateUserData(String name,String contact,String dob){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("contact",contact);
        contentValues.put("dob",dob);
        Cursor cursor=DB.rawQuery("select * from UserDetails where name=?",new String[]{name});
        if(cursor.getCount()>0){
            long result=DB.update("UserDetails",contentValues,"name=?",new String[]{name});
            if(result==-1){
                return false;
            }else{
                return true;
            }
        }
        else{
            return false;
        }
    }


    public Boolean DeleteUserData(String name){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("select * from UserDetails where name=?",new String[]{name});
        if(cursor.getCount()>0){
            long result=DB.delete("UserDetails","name=?",new String[]{name});
            if(result==-1){
                return false;
            }else{
                return true;
            }
        }
        else{
            return false;
        }
    }

    public Cursor getdata(){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("select * from UserDetails",null);
        return cursor;
    }
}
