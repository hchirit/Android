package com.example.nathanmsika.Android5778_6244_8742_01.model.constants;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.nathanmsika.Android5778_6244_8742_01.model.backend.AcademyConst;

/**
 * Created by nathanmsika on 20/11/2017.
 */

public class DataBase_model extends SQLiteOpenHelper {


    public final String TAG = "MyApp";

    public static final String DATABASE_NAME_MODEL = "Models.db";
    public static final String TABLE_NAME_MODEL = "Models";

    String CREATE_PERSON_TABLE_CAR  ="CREATE TABLE "+  TABLE_NAME_MODEL
            +" ("+ AcademyConst.ModelstConst.NAME+" VARCHAR(15)  PRIMARY KEY  NOT NULL,"
            +AcademyConst.ModelstConst.MODEL+" TEXT,"
            +AcademyConst.ModelstConst.MANUFACTURER+" TEXT,"
            +AcademyConst.ModelstConst.CAPACITY+" REAL,"
           // +AcademyConst.ModelstConst.GEARBOX+" VARCHAR(15)),"
            +AcademyConst.ModelstConst.PLACENUMBER+" INTEGER,"
            +AcademyConst.ModelstConst.DOORNUMBERS+" INTEGER,"
            +AcademyConst.ModelstConst.LUGGAGE+" BOOLEAN,"
            +AcademyConst.ModelstConst.GPS+" BOOLEAN,"
            +AcademyConst.ModelstConst.AIRCONDITIONNER+" BOOLEAN)"
            ;


    public DataBase_model(Context context) {
        super(context, DATABASE_NAME_MODEL , null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_PERSON_TABLE_CAR);

   }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_NAME_MODEL);
    }



    ////////branch
    public String insertmodel(ContentValues c)
    {
        Log.e(TAG,"database MODEL 11");
        SQLiteDatabase db = this.getWritableDatabase();
        Log.e(TAG,"database MODEL 12");
        long result = db.insert(TABLE_NAME_MODEL,null,c);
        Log.e(TAG,"database MODEL 13");
        db.close();
        if(result==-1)
            return null;
        else
            return "true";
    }

    public Cursor getallmodel(){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("Select * from " + TABLE_NAME_MODEL,null);
        return res;
    }
    /////////////



}
