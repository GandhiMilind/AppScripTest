package com.example.appscriptgame.DatabasePack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.appscriptgame.ModelClass.GameData;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static String DATABSENAME = "AppScriptGame.db";
    private static int VERSION = 1;
    private static String tableName = "GameScrore";

    private static String index = "gameid";
    private static String name = "Name";
    private static String time = "Time";
    private static String ans1 = "Ans1";
    private static String ans2 = "Ans2";

    private static String CREAT_TABLE = "CREATE TABLE "+ tableName +"("
            + index + " INTEGER PRIMARY KEY AUTOINCREMENT," + name + " TEXT,"
            + time + " TEXT,"
            + ans1 + " TEXT,"
            + ans2 + " TEXT)";


    // drop area table sql query
    private String dropTable = "DROP TABLE IF EXISTS " + tableName;





    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABSENAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(dropTable);
        onCreate(db);

    }


    public void addData(GameData gameData){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(name,gameData.getName());
        value.put(time,gameData.getTime());
        value.put(ans1,gameData.getAns1());
        value.put(ans2,gameData.getAns2());
        db.insert(tableName,null,value);
        db.close();

    }


    public ArrayList<GameData> getData(){

        ArrayList<GameData> dataList = new ArrayList<>();
        String[] column = {
                index,
                name,
                time,
                ans1,
                ans2};

        String sortOrder =
                index + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(tableName,column,null,null,null,
                null,sortOrder);

       while (cursor.moveToNext()){
           GameData gameData = new GameData();
           gameData.setIndex(Integer.parseInt(cursor.getString(cursor.getColumnIndex(index))));
           gameData.setName(cursor.getString(cursor.getColumnIndex(name)));
           gameData.setTime(cursor.getString(cursor.getColumnIndex(time)));
           gameData.setAns1(cursor.getString(cursor.getColumnIndex(ans1)));
           gameData.setAns2(cursor.getString(cursor.getColumnIndex(ans2)));
           dataList.add(gameData);
       }

       return dataList;
    }


}
