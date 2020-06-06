package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class DatabaseAcces {
    private SQLiteAssetHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAcces instance;
    Cursor c=null;

    //private constructor so that object creation from outside the class is avoided
    private DatabaseAcces(Context context){
       this.openHelper=new DatabaseOpenHelper(context);
    }

    //to return the instance of database

    public  static DatabaseAcces getInstance(Context context){
        if(instance==null){
            instance=new DatabaseAcces(context);
        }
        return  instance;
    }

    // to open the database
    public  void open(){
        this.db=openHelper.getWritableDatabase();
    }

    //closing the databse connetion
    public  void close(){
        if(db!=null){
            this.db.close();
        }
    }

    //now lets create a method to query sentence from database

    //we will query WrongSentence by passing quizID
    public  String getSentence(String sentenceType,int quizID){
        if(sentenceType=="wrong") {
            c = db.rawQuery("select wrongSentence from quiz where quizID='" + quizID + "'", new String[]{});
        }
        else{
            c= db.rawQuery("select rightSentence from quiz where quizID='" + quizID + "'", new String[]{});
        }
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()){
            String address= c.getString(0);
            buffer.append(""+address);
        }
        return buffer.toString();
    }
}
