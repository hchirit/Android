package com.example.nathanmsika.Android5778_6244_8742_01.model.constants;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.nathanmsika.Android5778_6244_8742_01.model.backend.AcademyConst;

/**
 * Created by nathanmsika on 16/11/2017.
 */

public class DataBase_Client extends SQLiteOpenHelper{

    public final String TAG = "MyApp";

    public static final String DATABASE_NAME = "c.db";
    public static final String TABLE_NAME = "c";


    public DataBase_Client(Context context) {

        super(context,DATABASE_NAME , null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone TEXT, email TEXT, password TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_NAME);
    }


    ////////client
    public boolean insertClient(ContentValues c)
    {
        Log.e(TAG,"create");
        SQLiteDatabase db_ = this.getWritableDatabase();
        Log.e(TAG,"create writable data base");
        long result = db_.insert(TABLE_NAME,null,c);
        db_.close();
        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor getallClient(){
        SQLiteDatabase db_ = this.getWritableDatabase();
        Cursor res = db_.rawQuery("Select * from " + TABLE_NAME,null);
        return res;
    }
    //////////////

    public Integer delete_client(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABLE_NAME,"_id=?",new String[]{id});
        return i;
    }


}


