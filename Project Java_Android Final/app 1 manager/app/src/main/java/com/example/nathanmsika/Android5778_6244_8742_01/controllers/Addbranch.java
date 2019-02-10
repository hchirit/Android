package com.example.nathanmsika.Android5778_6244_8742_01.controllers;

import android.content.ContentValues;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nathanmsika.Android5778_6244_8742_01.model.backend.AcademyConst;
import com.example.nathanmsika.Android5778_6244_8742_01.model.backend.FactoryDB_manager;
import com.example.nathanmsika.Android5778_6244_8742_01.model.constants.DataBase_Client;
import com.example.nathanmsika.Android5778_6244_8742_01.model.constants.DataBase_branch;
import com.example.nathanmsika.Android5778_6244_8742_01.model.datasource.Liste_DBManagers;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Address;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Branch;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Client;
import com.example.nathanmsika.my_navigation_drawer.R;

import static com.example.nathanmsika.Android5778_6244_8742_01.model.backend.AcademyConst.branchToContentValues;


/**
 * Created by nathanmsika on 16/11/2017.
 */

public class Addbranch extends AppCompatActivity {


    EditText parkplace,branchnumber ,a_city,a_street,a_number;
    Button btnClick,rt;
    Branch b;
    Address address_ ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);

        init_button();

    }



    public void init_button(){

        parkplace = (EditText) findViewById(R.id.editText12);
        branchnumber = (EditText) findViewById(R.id.editText13);
        a_city = (EditText) findViewById(R.id.id_addrss_city);
        a_street = (EditText) findViewById(R.id.id_addrss_street);
        a_number = (EditText) findViewById(R.id.id_addrss_number);



        btnClick = (Button) findViewById(R.id.btn_branch);
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

        try {
            final ContentValues contentValues ;
            final Uri uri_ = AcademyConst.BranchConst.ACTIVITY_URI;

            address_ = new Address(a_city.getText().toString(),a_street.getText().toString(),a_number.getText().toString());

           // b = new Branch(address.getText().toString(),Integer.parseInt(parkplace.getText().toString()),Integer.parseInt(branchnumber.getText().toString()));

            b = new Branch(Integer.parseInt(parkplace.getText().toString())
                    ,Integer.parseInt(branchnumber.getText().toString()),address_);


            contentValues = branchToContentValues(b);

            //ldb.addbranch(contentValues);

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

            Thread.sleep(300);
            finish();


        }
        catch (Exception ex)
        {
            Log.d("APP","error trouver: " + ex.getMessage());
        }
    }


    public void toast()
    {
        Toast.makeText(this, "Id Branch : "+ b.getBranchNumber()
                + " Insert Successfully", Toast.LENGTH_LONG).show();
    }





}