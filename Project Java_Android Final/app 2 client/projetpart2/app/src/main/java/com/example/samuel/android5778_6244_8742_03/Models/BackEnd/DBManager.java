package com.example.samuel.android5778_6244_8742_03.Models.BackEnd;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.samuel.android5778_6244_8742_03.Models.entites.Branch;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Car;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Client;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Models;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Reservation;
import com.example.samuel.android5778_6244_8742_03.Models.entites.UrlCarImage;

import java.util.List;

/**
 * Created by samuel on 26-Nov-17.
 */

public interface DBManager {

    // function of client
    public boolean existClient(String name ,String id);
    public void addClient(ContentValues values);
    public void addReservation(ContentValues values);
    public void updatekmCar(float km, ContentValues values);
    public String get_url_image(String num);



    // to full the static list from php table
    public void allClient();
    public void allModels();
    public void allBranchs();
    public void allcars();
    public void allreservation();
    public void allurl();


    // cars list with condition
    public List<Car> freeCars();
    public List<Car> freeCarsforbranch(int idBranch);
    public List<Car> freeCarsformodel(String model);
    public List<Car> freeNearsCars(String city);


    // branch by condition
    public List<Branch> branchbycity(String city);
    public Branch branchByFreeCarsofModels(int  model);


    // all func for user
    public List<Reservation> notClosedReservation();
    public void endReservation(ContentValues values,float km);
    public boolean closedReservation(ContentValues values);
    public boolean check_if_have_reservation(int id_car);
    public boolean check_if_Client_have_reservation(int id_client);
    public void  add_id_user(int id);


    // ID of the User
    public int getIdUser();


    // get static list
    public List<Client> AllClient();
    public List<Models> AllModels();
    public List<Branch> AllBranchs();
    public List<Car> Allcars();
    public List<Reservation> Allreservation();
    public List<UrlCarImage> Allurl();

    // to initialize all list
    public void initAllList();


    boolean isUpdatet();
}
