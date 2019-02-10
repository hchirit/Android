package com.example.nathanmsika.Android5778_6244_8742_01.model.constants;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.nathanmsika.Android5778_6244_8742_01.model.backend.AcademyConst;

/**
 * Created by nathanmsika on 19/11/2017.
 */

public class DataBase_car  extends SQLiteOpenHelper {


    public final String TAG = "MyApp";

    public static final String DATABASE_NAME_CAR = "Cars.db";
    public static final String TABLE_NAME_CAR = "Cars";

    String CREATE_PERSON_TABLE_CAR  ="CREATE TABLE "+  TABLE_NAME_CAR
            +" ("+ AcademyConst.CarConst.IDCAR+" VARCHAR(15)  PRIMARY KEY  NOT NULL,"
            +AcademyConst.CarConst.CLASS+" VARCHAR(15)  NOT NULL,"
           +AcademyConst.CarConst.KM+" REAL  NOT NULL,"
            +AcademyConst.CarConst.BRANCHPARKNUM+" INTEGER  NOT NULL)";


    public DataBase_car(Context context) {
        super(context,DATABASE_NAME_CAR , null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PERSON_TABLE_CAR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_NAME_CAR);
    }



    ////////branch
    public int insertcar(ContentValues c)
    {
        Log.e(TAG,"database car 11");
        SQLiteDatabase db = this.getWritableDatabase();
        Log.e(TAG,"database car 12");
        long result = db.insert(TABLE_NAME_CAR,null,c);
        Log.e(TAG,"database car 13");
        db.close();
        if(result==-1)
            return 0;
        else
            return 1;
    }

    public Cursor getallcar(){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("Select * from " + TABLE_NAME_CAR,null);
        return res;
    }
    /////////////



}
