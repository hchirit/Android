package com.example.samuel.android5778_6244_8742_03.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.samuel.android5778_6244_8742_03.Activity.Menu_fragment.Menu_button;
import com.example.samuel.android5778_6244_8742_03.Activity.setting.SettingsActivity;
import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.FactoryDBManager;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Client;
import com.example.samuel.android5778_6244_8742_03.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String PREFS = "PREFS";
    private static final String PREFS_NAME = "PREFS_NAME";
    private static final String PREFS_PASSWORD = "PREFS_PASSWORDE";
    SharedPreferences sharedPreferences;
    Intent i;
    Button cancel,submit,Register_ ;
    private static final String TAG ="MyApp" ;
    EditText userName,userPassword;
    String name_;
    String  pass_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_navigation();
        button_();
    }


    public void init_navigation()
    {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Send Email", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                func_email();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
    public void func_email()
    {
        try{
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setData(Uri.parse("email"));
            String[] s={"nathanmsika@gmail.com"};
            i.putExtra(Intent.EXTRA_EMAIL,s);
            i.putExtra(Intent.EXTRA_SUBJECT, "Society Take & Go");
            i.putExtra(Intent.EXTRA_TEXT,"Samuel Benebrie and Nathan Msika");
            i.setType("message/rfc822");
            Intent chosser = Intent.createChooser(i,"launch Email");
            startActivity(chosser);
        }
        catch (Exception ex)
        {
            Log.e(TAG,"error trouver: " +getClass().getName()+" "+ ex.getMessage()  );
        }
    }

    public void button_()
    {
        userName = (EditText) findViewById(R.id.userName);
        userPassword = (EditText) findViewById(R.id.userPassword);

        submit = (Button) findViewById(R.id.submit) ;
        Register_ = (Button) findViewById(R.id.button);
        cancel = (Button) findViewById(R.id.cancel);
        set_user_pass();
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String user = userName.getText().toString();
                String password_ = userPassword.getText().toString();
                Boolean is = FactoryDBManager.getInstance().existClient(user,password_);


                if (sharedpreference()== false && is)
                {
                    sharedPreferences = getBaseContext().getSharedPreferences(PREFS, MODE_PRIVATE);
                    sharedPreferences
                            .edit()
                            .putString(PREFS_NAME, userName.getText().toString())
                            .putString(PREFS_PASSWORD, userPassword.getText().toString())
                            .apply();
                    for(Client item : FactoryDBManager.getInstance().AllClient())
                        if(item.getPassword().equals(password_) && item.getName().equals(user)) {
                            FactoryDBManager.getInstance().add_id_user(Integer.valueOf(item.getID()));
                        }
                    Intent intent = new Intent(MainActivity.this, Menu_button.class);
                    startActivity(intent);
                 }
               else if(sharedpreference() && is)
                {
                    for(Client item : FactoryDBManager.getInstance().AllClient())
                        if(item.getPassword().equals(password_) && item.getName().equals(user)) {
                            FactoryDBManager.getInstance().add_id_user(Integer.valueOf(item.getID()));
                        }
                    Intent intent = new Intent(MainActivity.this, Menu_button.class);
                    startActivity(intent);
                 }
                 else
                    toast_submit();
            }
        });

        Register_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //si aucun utilisateur n'est sauvegardé, on ajouter [24,florent]
                Intent intent = new Intent(MainActivity.this, AddClient.class);
                startActivity(intent);
                toast_register();
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    userName.setText("");
                    userPassword.setText("");
                    final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                } catch (Exception ex) {
                    Log.d("APP", "error: " + ex.getMessage());
                }
            }
        });


    }

    public void toast_submit()
    {
        Toast.makeText(this, "Wrong User name or Password  "
                + "or  you need to register", Toast.LENGTH_LONG).show();
    }

    public void toast_register()
    {
        Toast.makeText(this, "you registerig ", Toast.LENGTH_LONG).show();
    }

    public Boolean sharedpreference()
    {
        sharedPreferences = getBaseContext().getSharedPreferences(PREFS, MODE_PRIVATE);

        if (sharedPreferences.contains(PREFS_NAME) && sharedPreferences.contains(PREFS_NAME)) {
            String user = userName.getText().toString();
            String password_ = userPassword.getText().toString();
            String name = sharedPreferences.getString(PREFS_NAME, null);
            String password = sharedPreferences.getString(PREFS_PASSWORD, null);
            if(user.equals(name) && password_.equals(password))
                return true;
            else return false;
        }
        else
           return false;
    }


    public void set_user_pass()
    {
        sharedPreferences = getBaseContext().getSharedPreferences(PREFS, MODE_PRIVATE);
        if (sharedPreferences.contains(PREFS_NAME) && sharedPreferences.contains(PREFS_NAME)) {
            userName.setText(sharedPreferences.getString(PREFS_NAME, null));
            userPassword.setText(sharedPreferences.getString(PREFS_PASSWORD, null));
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
           contact_func();
            return true;
        }
        else if (id == R.id.action_settings) {
            i = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
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

    public void fun_dialog()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getBaseContext()); alertDialogBuilder.setTitle("login");

// Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();

// Inflate and set the layout for the dialog // Pass null as the parent view because its going in the dialog layout
       alertDialogBuilder.setView(inflater.inflate(R.layout.dialog_layout, null));

        Button b_email = (Button)findViewById(R.id.button_email);
        b_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    func_email();
                } catch (Exception ex) {
                    Log.d("APP", "error: " + ex.getMessage());
                }
            }
        });
        Button b_web = (Button)findViewById(R.id.button_web);
        b_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                } catch (Exception ex) {
                    Log.d("APP", "error: " + ex.getMessage());
                }
            }
        });
        Button b_phone = (Button)findViewById(R.id.button_phone);
        b_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                } catch (Exception ex) {
                    Log.d("APP", "error: " + ex.getMessage());
                }
            }
        });
// Add action buttons
    //    alertDialogBuilder.setPositiveButton("Sign in", onClickListener);
     //   alertDialogBuilder.setNegativeButton("Cancel",onClickListener);

        alertDialogBuilder.show();
    }

    public void func_dialog_(){
        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_layout);
        dialog.setTitle("Title...");

        Button b_email = (Button)findViewById(R.id.button_email);
        b_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    func_email();
                } catch (Exception ex) {
                    Log.d("APP", "error: " + ex.getMessage());
                }
            }
        });

        dialog.show();
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_car) {
            i = new Intent(MainActivity.this, Activity_Lists.class);
            i.putExtra("Value","cars");
            startActivity(i);
        } else if (id == R.id.nav_gallery) {
            i = new Intent(MainActivity.this, Activity_Lists.class);
            i.putExtra("Value","branch");
            startActivity(i);
        } else if (id == R.id.nav_slideshow) {
            i = new Intent(MainActivity.this, Activity_Lists.class);
            i.putExtra("Value","image");
            startActivity(i);
        } else if (id == R.id.nav_manage) {
            i = new Intent(MainActivity.this, Menu_button.class);
           // i.putExtra("Value","image");
            startActivity(i);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
