package com.example.nathanmsika.Android5778_6244_8742_01.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.solver.SolverVariable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nathanmsika.Android5778_6244_8742_01.model.backend.AcademyConst;
import com.example.nathanmsika.Android5778_6244_8742_01.model.backend.DB_manager;
import com.example.nathanmsika.Android5778_6244_8742_01.model.backend.FactoryDB_manager;
import com.example.nathanmsika.Android5778_6244_8742_01.model.backend.FactoryDB_manager_;
import com.example.nathanmsika.Android5778_6244_8742_01.model.constants.DataBase_car;
import com.example.nathanmsika.Android5778_6244_8742_01.model.datasource.Liste_DBManagers;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Car;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Client;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Models;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.UrlCarImage;
import com.example.nathanmsika.my_navigation_drawer.R;

import static com.example.nathanmsika.Android5778_6244_8742_01.model.backend.AcademyConst.CarToContentValues;
import static com.example.nathanmsika.Android5778_6244_8742_01.model.backend.AcademyConst.ClientToContentValues;
import static com.example.nathanmsika.Android5778_6244_8742_01.model.backend.AcademyConst.ModelToContentValues;
import static com.example.nathanmsika.Android5778_6244_8742_01.model.backend.AcademyConst.urlimageToContentValues;

/**
 * Created by nathanmsika on 15/11/2017.
 */

public class Addcar extends AppCompatActivity {
    final String  LOG = "Lists";

    EditText branch_num, km , car_num , classe,url_ ;
    Button btnClick,rt;
    Car c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        init_button();
    }


    public void init_button()
    {

        branch_num = (EditText) findViewById(R.id.editText20);
        km = (EditText) findViewById(R.id.editText19);
        car_num = (EditText) findViewById(R.id.editText18);
        classe =(EditText) findViewById(R.id.editText17);
        url_ =(EditText) findViewById(R.id.editText_url);

        btnClick = (Button) findViewById(R.id.button14);
        rt= (Button) findViewById(R.id.button20);
        rt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                finish();
            }
        });
        btnClick.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                ClickMe();
            }
        });

    }

    // button add
    private void ClickMe() {

        if( branch_num.length()==0 || km.length()==0 || car_num.length()==0 || classe.length()==0 || url_.length()==0) {
            Toast.makeText(this, "u have to fill the whole field", Toast.LENGTH_LONG).show();
        }
        else{
            try{
                UrlCarImage urlCarImage = new UrlCarImage(Integer.parseInt(car_num.getText().toString()), url_.getText().toString());
                final ContentValues ct = urlimageToContentValues(urlCarImage);
                int n_branch = Integer.parseInt(branch_num.getText().toString());
                float k_m = Float.valueOf(km.getText().toString());
                final Uri uri_ = AcademyConst.CarConst.ACTIVITY_URI;
                final ContentValues contentValues;
                Log.e(LOG, "add car 11");
                c = new Car(n_branch, k_m, car_num.getText().toString(), classe.getText().toString());
                contentValues = CarToContentValues(c);

                new AsyncTask<Void, Void, Uri>() {
                    @Override
                    protected Uri doInBackground(Void... params) {
                        getContentResolver().insert(uri_, contentValues);
                        FactoryDB_manager_.getinstance().add_url_image(ct);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Uri result) {
                        try {
                            Thread.sleep(500);
                            toast();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        finish();
                    }
                }.execute();
                Log.e(LOG, "add car 12");

            } catch (Exception ex) {
                Log.e("MyApp", "error trouver: " + ex.getMessage());
            }
        }
    }


    public void toast()
    {
        Toast.makeText(this, "Id car : "+ c.getCarNumber()
                + " Insert Successfully", Toast.LENGTH_LONG).show();
    }




}
