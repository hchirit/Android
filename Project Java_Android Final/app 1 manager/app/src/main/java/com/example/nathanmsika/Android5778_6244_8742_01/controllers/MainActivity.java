package com.example.nathanmsika.Android5778_6244_8742_01.controllers;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nathanmsika.Android5778_6244_8742_01.model.backend.FactoryDB_manager_;
import com.example.nathanmsika.Android5778_6244_8742_01.model.constants.KeyValues;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Client;
import com.example.nathanmsika.my_navigation_drawer.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG ="MyApp" ;
    EditText userName,userPassword;
    Button cancel,submit,Register_ ;
    Client c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fun_mail();
            }
        });


        setSupportActionBar(toolbar);
        intit_Drawerlayout(toolbar);
        Button_onClick();



    }




    public void intit_Drawerlayout(Toolbar toolbar)
    {
        try {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
        }
        catch (Exception ex)
        {
            Log.e(TAG,"error trouver: " +getClass().getName()+" "+ ex.getMessage()  );
        }
    }



    public void Button_onClick()
    {
        try {
            userName = (EditText) findViewById(R.id.userName);
            userPassword = (EditText) findViewById(R.id.userPassword);

            cancel = (Button) findViewById(R.id.cancel);
            submit = (Button) findViewById(R.id.submit);
            Register_ = (Button) findViewById(R.id.button);


            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        userName.setText("");
                        userPassword.setText("");
                    } catch (Exception ex) {
                        Log.d("APP", "error: " + ex.getMessage());
                    }
                }
            });

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String user = userName.getText().toString();
                    String password = userPassword.getText().toString();
                    c = new Client(user, null, null, null, password);

                    new AsyncTask<Void,Void,Boolean>(){
                        @Override
                        protected Boolean doInBackground(Void... voids) {
                            return FactoryDB_manager_.getinstance().check_exist_client(c);
                        }

                        @Override
                        protected void onPostExecute(Boolean aBoolean) {
                            if (aBoolean == true) {
                                Intent intent = new Intent(MainActivity.this, Activity_menu.class);
                                startActivity(intent);
                                userName.setText("");
                                userPassword.setText("");
                            } else {
                                Toast.makeText(MainActivity.this, R.string.alerts_wrong_credentials, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }.execute();


                }
            });

            Register_.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, AddClient.class);
                    intent.putExtra(KeyValues.KEY1, KeyValues.VALUE1);
                    startActivity(intent);
                }
            });

        }
        catch (Exception ex)
        {
            Log.e(TAG,"error trouver: " +getClass().getName()+" "+ ex.getMessage()  );
        }




    }


    public void fun_mail()
    {
        try{
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setData(Uri.parse("email"));
        String[] s={"nathanmsika@gmail.com"};
        i.putExtra(Intent.EXTRA_EMAIL,s);
        i.putExtra(Intent.EXTRA_SUBJECT, "this is a subject");
        i.putExtra(Intent.EXTRA_TEXT,"Hii this is email body");
        i.setType("message/rfc822");
        Intent chosser = Intent.createChooser(i,"launch Email");
        startActivity(chosser);
        }
        catch (Exception ex)
        {
            Log.e(TAG,"error trouver: " +getClass().getName()+" "+ ex.getMessage()  );
        }
    }


    @Override
    public void onBackPressed() {
        try{
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        }
        catch (Exception ex)
        {
            Log.e(TAG,"error trouver: " +getClass().getName()+" "+ ex.getMessage()  );
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        try{
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
        }
        catch (Exception ex)
        {
            Log.e(TAG,"error trouver: " +getClass().getName()+" "+ ex.getMessage()  );
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try{
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


            switch (id) {

                case R.id.id_action_contact:
                    contact_func();

                case R.id.id_action_aboutas:

            }

        return super.onOptionsItemSelected(item);
        }
        catch (Exception ex)
        {
            Log.e(TAG,"error trouver: " +getClass().getName()+" "+ ex.getMessage()  );
            return false;
        }
    }

    public void contact_func()
    {
        //TODO: creer un button pour appeler se numero
        AlertDialog deleteAlert = new AlertDialog.Builder(this).create();
        deleteAlert.setTitle("Contact");
        deleteAlert.setMessage("Email: takengo@gmail.com" +
                "\n" + "\n" +
                "WebSite: takengo.co.il"    +
                "\n" + "\n" +
                "Tel: 0584838662"
        );
        deleteAlert.show();
    }

    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        try{
        // Handle navigation view item clicks here.
        int id = item.getItemId();

            Intent i;
            switch (id) {

                case R.id.nav_camera:
                    i = new Intent(this,AddClient.class);
                    startActivity(i);
                    break;

                case R.id.nav_gallery:
                    i = new Intent(this,Addcar.class);
                    startActivity(i);
                    break;

                case R.id.nav_slideshow:
                    i = new Intent(this,AddModel.class);
                    startActivity(i);
                    break;

                case R.id.nav_manage:
                    i = new Intent(this,Addbranch.class);
                    startActivity(i);
                    break;

                case R.id.nav_share:
                    i = new Intent(MainActivity.this, Activity_Lists.class);
                    i.putExtra("Value","models");
                    startActivity(i);
                    break;

                case R.id.nav_send:
                    i = new Intent(MainActivity.this, Activity_Lists.class);
                    i.putExtra("Value","cars");
                    startActivity(i);
                    break;

                case R.id.nav_branch:
                    i = new Intent(MainActivity.this, Activity_Lists.class);
                    i.putExtra("Value","branch");
                    startActivity(i);
                    break;

                case R.id.nav_client:
                    i= new Intent(MainActivity.this, Activity_Lists.class);
                    i.putExtra("Value","client");
                    startActivity(i);
                    break;

            }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
        }
        catch (Exception ex)
        {
            Log.e(TAG,"error trouver: " +getClass().getName()+" "+ ex.getMessage()  );
            return false;
        }
    }



}
