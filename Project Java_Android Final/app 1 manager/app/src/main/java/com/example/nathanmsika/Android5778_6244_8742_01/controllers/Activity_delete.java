package com.example.nathanmsika.Android5778_6244_8742_01.controllers;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nathanmsika.Android5778_6244_8742_01.model.backend.DB_manager;
import com.example.nathanmsika.Android5778_6244_8742_01.model.backend.FactoryDB_manager;
import com.example.nathanmsika.Android5778_6244_8742_01.model.constants.DataBase_Client;
import com.example.nathanmsika.Android5778_6244_8742_01.model.constants.DataBase_branch;
import com.example.nathanmsika.Android5778_6244_8742_01.model.constants.DataBase_car;
import com.example.nathanmsika.Android5778_6244_8742_01.model.constants.DataBase_model;
import com.example.nathanmsika.Android5778_6244_8742_01.model.datasource.Liste_DBManagers;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Branch;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Car;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Client;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Models;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.gearbox;
import com.example.nathanmsika.my_navigation_drawer.R;

import java.util.ArrayList;
import java.util.List;

public class Activity_delete extends AppCompatActivity {


    Spinner client_id,branch_id,car_id,model_id;
    DB_manager db;
    Button b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_activity_delete);

        db = FactoryDB_manager.getinstance();
        db.db_all(this);

        try {
            init_spinner();
            set_spinner_client_id();
            set_spinner_car_id();
            set_spinner_branch_id();
            set_spinner_model_id();
        }
        catch (Exception ex)
        {
           Log.e("MyApp",ex.getMessage());
        }
    }

    private void init_spinner() {
        client_id = (Spinner) findViewById(R.id.spinner2);
        branch_id = (Spinner) findViewById(R.id.spinner_delete_branch);
        model_id = (Spinner) findViewById(R.id.spinner_delete_model);
        car_id = (Spinner) findViewById(R.id.spinner_delete_car);


        b = (Button) findViewById(R.id.button_delete_client);
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                ClickMe();
            }
        });
    }


    public void set_spinner_client_id() {
        List<String> c = new ArrayList<>();

        for (Client item : db.allclient()) {
            c.add(item.getName());
        }

        client_id.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c));
    }

    public void set_spinner_car_id() {
        List<String> c = new ArrayList<>();

        for (Car item : db.allcars()) {
            c.add(item.getCarNumber());
        }

        car_id.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c));
    }

    public void set_spinner_branch_id() {
        List<String> c = new ArrayList<>();

        for (Branch item : db.allbranch()) {
            c.add(Integer.toString(item.getBranchNumber()));
        }

        branch_id.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c));
    }

    public void set_spinner_model_id() {
        List<String> c = new ArrayList<>();

        for (Models item : db.allmodels()) {
            c.add(item.getModel());
        }

        model_id.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c));
    }



    void ClickMe()
    {
        try
        {
        int id = client_id.getSelectedItemPosition();
        Toast.makeText(this, "Id client : " + id + " delete successful", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Id client : " + ex.getMessage() + " delete failed", Toast.LENGTH_SHORT).show();
        }
    }




}
