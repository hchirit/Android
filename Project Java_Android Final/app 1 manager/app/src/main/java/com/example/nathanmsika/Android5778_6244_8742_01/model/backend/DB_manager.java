package com.example.nathanmsika.Android5778_6244_8742_01.model.backend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.nathanmsika.Android5778_6244_8742_01.model.constants.DataBase_Client;
import com.example.nathanmsika.Android5778_6244_8742_01.model.constants.DataBase_branch;
import com.example.nathanmsika.Android5778_6244_8742_01.model.constants.DataBase_car;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Branch;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Car;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Client;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Models;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.UrlCarImage;

import java.util.List;

/**
 * Created by nathanmsika on 13/11/2017.
 */

public interface DB_manager {


    boolean check_exist_client(Client c);


    ///add function content value
    String addclient(ContentValues client);
    String addmodel(ContentValues model);
    int addcar(ContentValues car);
    int addbranch(ContentValues car);
    void add_url_image(ContentValues contentValues);



    ////list function
    List<Models> allmodels();
    List<Client> allclient();
    List<Branch> allbranch();
    List<Car> allcars();
    public void allurl();



    /// instance of all database
    void db_all(Context context);


    ///cursor list function
    Cursor Allmodels();
    Cursor Allclient();
    Cursor Allcar();
    Cursor Allbranch();

    public String get_url_image(String num);

    ///delete function
    int delete_client(int id);
    int delete_model(int id);
    int delete_branch(int id);
    int delete_car(int id);



}
