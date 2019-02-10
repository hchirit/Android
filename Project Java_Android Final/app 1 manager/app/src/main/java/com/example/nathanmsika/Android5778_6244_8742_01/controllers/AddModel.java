package com.example.nathanmsika.Android5778_6244_8742_01.controllers;

import android.content.ContentValues;
import android.net.Uri;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nathanmsika.Android5778_6244_8742_01.model.backend.AcademyConst;
import com.example.nathanmsika.Android5778_6244_8742_01.model.backend.FactoryDB_manager;
import com.example.nathanmsika.Android5778_6244_8742_01.model.constants.DataBase_model;
import com.example.nathanmsika.Android5778_6244_8742_01.model.datasource.Liste_DBManagers;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Models;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.gearbox;
import com.example.nathanmsika.my_navigation_drawer.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.nathanmsika.Android5778_6244_8742_01.model.backend.AcademyConst.ModelToContentValues;


/**
 * Created by nathanmsika on 14/11/2017.
 */

public class AddModel extends AppCompatActivity {


    EditText model,manufacture , name , enginecapacite ,placenum , doornumber;
    CheckBox gps ,luggage,airconditionner;
    Button btnClick , retourne;
    Models m;
    Spinner gearbox_;
    Liste_DBManagers db;
    gearbox gb ;
    public final String TAG = "MyApp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmodel);

        init_db();
        init_button();
        init_tv_spinner_checkbox();

    }


    public void init_button()
    {
        btnClick = (Button) findViewById(R.id.button_add_model) ;
        retourne = (Button) findViewById(R.id.button_return_model);
        btnClick.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Clic_add();
            }
        });
        retourne.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                finish();}
        });
    }


    public void init_db()
    {
        db = new Liste_DBManagers();
        db.db_model = new DataBase_model(this);
    }


    public void init_tv_spinner_checkbox()
    {
        model = (EditText) findViewById(R.id.editText5);
        manufacture = (EditText) findViewById(R.id.editText6);
        name = (EditText) findViewById(R.id.editText7);
        enginecapacite = (EditText) findViewById(R.id.editText10);
        placenum = (EditText) findViewById(R.id.editText9);
        doornumber = (EditText) findViewById(R.id.editText14);
        gearbox_ = (Spinner) findViewById(R.id.spinner_gearbox);

        gps = (CheckBox) findViewById(R.id.checkBox2);
        luggage = (CheckBox) findViewById(R.id.checkBox3);
        airconditionner = (CheckBox) findViewById(R.id.checkBox);



        gearbox_.setAdapter(new ArrayAdapter<gearbox>(this, android.R.layout.simple_spinner_item, gearbox.values()));

    }

    protected void Clic_add()
    {

        try
        {
            final Uri uri_ = AcademyConst.ModelstConst.ACTIVITY_URI;
            Log.e(TAG,"model add 1");
            float engine = Float.valueOf(enginecapacite.getText().toString());
            int place = Integer.parseInt(placenum.getText().toString());
            int door = Integer.parseInt(doornumber.getText().toString());
         //   gb =  Enum.valueOf(gearbox.class, gearbox_.getTransitionName().toString());
            Log.e(TAG,"model add 2");

            final ContentValues contentValues ;

            String geb = gearbox_.getSelectedItem().toString();
            gb.valueOf(geb);

            m = new Models(model.getText().toString() ,manufacture.getText().toString(),
            name.getText().toString(), engine , place ,  door ,
                    luggage.isChecked(),gps.isChecked(),airconditionner.isChecked() ,gb
                );



            contentValues = ModelToContentValues(m);
           // String result = db.addmodel(contentValues);

            new AsyncTask<Void, Void, Uri>() {
                @Override
                protected Uri doInBackground(Void... params) {
                    getContentResolver().insert(uri_, contentValues);
                    return null;
                }

                @Override
                protected void onPostExecute(Uri result) {
                    try {
                        Thread.sleep(300);
                        toast();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            }.execute();

            Log.e(TAG,"add model po");
            Thread.sleep(500);
            finish();

        }
        catch (Exception ex)
        {
            Log.e(TAG,"2" + ex.getMessage());
        }
    }

    public void toast()
    {
        Toast.makeText(this, "Id Model : "+ m.getModel()
                + " Insert Successfully", Toast.LENGTH_LONG).show();
    }


}
