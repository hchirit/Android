package com.example.samuel.android5778_6244_8742_03.Activity;

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

import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.AcademyConst;
import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.FactoryDBManager;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Client;
import com.example.samuel.android5778_6244_8742_03.R;

import static com.example.samuel.android5778_6244_8742_03.Models.BackEnd.AcademyConst.ClientToContentValues;

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
        setContentView(R.layout.addclient);

        try {
            init_button();
        }
        catch (Exception ex) {
          Log.e(LOG,"error add client " + this.getClass().toString());
        }

    }




    public void init_button()
    {
        txtName = (EditText) findViewById(R.id.idName);
        phonenum = (EditText) findViewById(R.id.idSurname);
        txtEmail = (EditText) findViewById(R.id.idMarks);
        txtid =(EditText) findViewById(R.id.id_id);
        btnClick = (Button) findViewById(R.id.idBtn);
        retourne = (Button) findViewById(R.id.button_return_liste);
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


            final ContentValues contentValues ;
            String name = txtName.getText().toString();
            String phonenum_ = phonenum.getText().toString();
            String email = txtEmail.getText().toString();
            String id_ = txtid.getText().toString();
            String id_pass = txtpassword.getText().toString();
            c = new Client(name,id_,phonenum_,email,id_pass);
            contentValues = ClientToContentValues(c);


            new AsyncTask<Void, Void, Uri>() {
                @Override
                protected Uri doInBackground(Void... params) {
                    FactoryDBManager.getInstance().addClient(contentValues);
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
