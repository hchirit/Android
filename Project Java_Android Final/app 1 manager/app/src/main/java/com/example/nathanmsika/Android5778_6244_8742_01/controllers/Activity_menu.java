package com.example.nathanmsika.Android5778_6244_8742_01.controllers;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by nathanmsika on 03/11/2017.
 */
import com.example.nathanmsika.my_navigation_drawer.R;
import com.squareup.picasso.Picasso;

public class Activity_menu extends AppCompatActivity {

    ListView mListView;
    Button client,branch,cars,models ,client_,branch_,cars_,models_;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        init_button();

    }


    public void init_button()
    {
        client = (Button) findViewById(R.id.button18);
        branch = (Button) findViewById(R.id.button17);
        models = (Button) findViewById(R.id.button16);
        cars = (Button) findViewById(R.id.button15);

        client_ = (Button) findViewById(R.id.button_add_client);
        branch_ = (Button) findViewById(R.id.button_add_branch);
        models_ = (Button) findViewById(R.id.button_add_model);
        cars_ = (Button) findViewById(R.id.button_add_car);

        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_menu.this, Activity_Lists.class);
                intent.putExtra("Value","client");
                startActivity(intent);
            }
        });
        branch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_menu.this, Activity_Lists.class);
                intent.putExtra("Value","branch");
                startActivity(intent);
            }
        });
        models.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_menu.this, Activity_Lists.class);
                intent.putExtra("Value","models");
                startActivity(intent);
            }
        });
        cars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_menu.this, Activity_Lists.class);
                intent.putExtra("Value","cars");
                startActivity(intent);
            }
        });

        client_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_menu.this, AddClient.class);
                startActivity(intent);
            }
        });

        branch_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_menu.this, Addbranch.class);
                startActivity(intent);
            }
        });
        models_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_menu.this, AddModel.class);
                startActivity(intent);
            }
        });
        cars_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_menu.this, Addcar.class);
                startActivity(intent);
            }
        });

    }




}
