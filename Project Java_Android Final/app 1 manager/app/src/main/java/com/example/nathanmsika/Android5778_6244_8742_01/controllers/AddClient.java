package com.example.nathanmsika.Android5778_6244_8742_01.controllers;

import android.content.ContentValues;
import android.net.Uri;
import android.nfc.Tag;
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
import com.example.nathanmsika.Android5778_6244_8742_01.model.datasource.Liste_DBManagers;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Client;
import com.example.nathanmsika.my_navigation_drawer.R;

import static com.example.nathanmsika.Android5778_6244_8742_01.model.backend.AcademyConst.ClientToContentValues;
/**
 * Created by nathanmsika on 13/11/2017.
 */

public class AddClient extends AppCompatActivity {


    EditText txtid,txtName , phonenum , txtEmail ,txtpassword;
    Button btnClick ,retourne;
    Client c;
    final String  LOG = "MyApp";

    public final String TAG = "MyApp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);


        init_button();


    }




    public void init_button()
    {
        txtName = (EditText) findViewById(R.id.idName);
        phonenum = (EditText) findViewById(R.id.idSurname);
        txtEmail = (EditText) findViewById(R.id.idMarks);
        txtid =(EditText) findViewById(R.id.id_id);
        btnClick = (Button) findViewById(R.id.idBtn);
        retourne = (Button) findViewById(R.id.button20);
        txtpassword =(EditText) findViewById(R.id.idpassword);

        btnClick.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                ClickMe();
            }
        });
        retourne.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }


    // button add
    private void ClickMe() {



        try {
            final Uri uri_ = AcademyConst.ClientConst.ACTIVITY_URI;


            final ContentValues contentValues ;
            String name = txtName.getText().toString();
            String phonenum_ = phonenum.getText().toString();
            String email = txtEmail.getText().toString();
            String id_ = txtid.getText().toString();
            String id_pass = txtpassword.getText().toString();
            c = new Client(name,id_,phonenum_,email,id_pass);
            contentValues = ClientToContentValues(c);

          //  String result = FactoryDB_manager.getinstance().addclient(contentValues);
         //   Boolean result_ = db.insertClient(contentValues);
         //  String name_ = ldb.addclient(contentValues);


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
            Log.d(TAG,"error trouver: " + ex.getMessage());
        }
    }

    public void toast()
    {
        Toast.makeText(this, "Id Client : "+ c.getID()
                + " Insert Successfully", Toast.LENGTH_LONG).show();
    }





}
