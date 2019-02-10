package com.example.nathanmsika.Android5778_6244_8742_01.model.constants;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.nathanmsika.Android5778_6244_8742_01.model.backend.AcademyConst;

public class DataBase_branch extends SQLiteOpenHelper {


    public final String TAG = "MyApp";


    public static final String DATABASE_NAME = "branch.db";
    public static final String TABLE_NAME_BRANCHE = "branch";

    String CREATE_PERSON_TABLE  ="CREATE TABLE "+  TABLE_NAME_BRANCHE
            +"("+ AcademyConst.BranchConst.branchnumber+" INTEGER  PRIMARY KEY AUTOINCREMENT,"
            +AcademyConst.BranchConst.ParkPlace+" INTEGER  NOT NULL,"
            +AcademyConst.BranchConst.a_city+" TEXT  NOT NULL,"
            +AcademyConst.BranchConst.a_street+" TEXT  NOT NULL,"
            +AcademyConst.BranchConst.a_number+" TEXT  NOT NULL) ";


    public DataBase_branch(Context context) {
        super(context,DATABASE_NAME , null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
     //   db.execSQL("CREATE TABLE " +TABLE_NAME_BRANCHE +
               // "(branchnumber INTEGER PRIMARY KEY, address TEXT, parkplace INTEGER)");
        db.execSQL(CREATE_PERSON_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_NAME_BRANCHE);
    }



    ////////branch
    public int insertBranch(ContentValues c)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(TABLE_NAME_BRANCHE,null,c);
        db.close();
        if(result==-1)
            return 0;
        else
            return 1;
    }

    public Cursor getallBrancht(){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("Select * from " + TABLE_NAME_BRANCHE,null);
        return res;
    }
    /////////////




}
