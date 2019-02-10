package com.example.nathanmsika.Android5778_6244_8742_01.model.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.nathanmsika.Android5778_6244_8742_01.controllers.Addcar;
import com.example.nathanmsika.Android5778_6244_8742_01.model.backend.DB_manager;
import com.example.nathanmsika.Android5778_6244_8742_01.model.constants.DataBase_Client;
import com.example.nathanmsika.Android5778_6244_8742_01.model.constants.DataBase_branch;
import com.example.nathanmsika.Android5778_6244_8742_01.model.constants.DataBase_car;
import com.example.nathanmsika.Android5778_6244_8742_01.model.constants.DataBase_model;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Address;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Branch;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Car;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Client;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Models;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.UrlCarImage;

import static com.example.nathanmsika.Android5778_6244_8742_01.model.backend.AcademyConst.ContentValuesTobranch;
import static com.example.nathanmsika.Android5778_6244_8742_01.model.backend.AcademyConst.ContentValuesTocar;
import static com.example.nathanmsika.Android5778_6244_8742_01.model.backend.AcademyConst.ContentValuesToclient;
import static com.example.nathanmsika.Android5778_6244_8742_01.model.backend.AcademyConst.contentValuesTomodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nathanmsika on 13/11/2017.
 */

public class Liste_DBManagers  implements DB_manager{

    final String  LOG = "MyApp";


    static List<Client> clients;
    static List<Car> cars;
    static List<Models> modeles;
    static List<Branch> branches;

    public DataBase_Client db_client ;
    public DataBase_branch db_branch ;
    public DataBase_car db_car;
    public DataBase_model db_model;


    static {
        clients = new ArrayList<>();
        cars = new ArrayList<>();
        modeles = new ArrayList<>();
        branches = new ArrayList<>();
    }


    public Liste_DBManagers() {

    }

    public void db_all(Context context) {
        this.db_client = new DataBase_Client(context);
        this.db_branch = new DataBase_branch(context);
        this.db_model = new DataBase_model(context);
        this.db_car = new DataBase_car(context);
    }



    @Override
    public Cursor Allmodels() {
        return db_model.getallmodel();
    }

    @Override
    public Cursor Allclient() {
        return db_client.getallClient();
    }

    @Override
    public Cursor Allcar() {
        return db_car.getallcar();
    }

    @Override
    public Cursor Allbranch() {
        return db_branch.getallBrancht();
    }

    @Override
    public String get_url_image(String num) {
        return null;
    }

    @Override
    public int delete_client(int id) {
        return 0;
    }

    @Override
    public int delete_model(int id) {
        return 0;
    }

    @Override
    public int delete_branch(int id) {
        return 0;
    }

    @Override
    public int delete_car(int id) {
        return 0;
    }

    @Override
    public boolean check_exist_client(Client c)
    {
        for(Client item : this.allclient()){
            if(item.getName().equals(c.getName())&&
                    item.getPassword().equals(c.getPassword()) )
                return true;
        }
        return false;
    }

    @Override
    public String addclient(ContentValues client) {
        Log.e(LOG,"11");
        Client client1 = ContentValuesToclient(client);
       // clients.add(client1);
        boolean result = db_client.insertClient(client);
        Log.e(LOG,"12");
        return client1.getID();
    }

    @Override
    public String addmodel(ContentValues model) {
        Models m = contentValuesTomodel(model);
     //   modeles.add(m);
        Log.e(LOG,"model 11");
        String result = db_model.insertmodel(model);
        return result;
    }


    @Override
    public int addcar(ContentValues car) {
        Car c = ContentValuesTocar(car);
       // cars.add(c);
        Log.e(LOG,"car addcar 11");
        int result = db_car.insertcar(car);
        Log.e(LOG,"car addcar 12");
        return result;
    }

    @Override
    public int addbranch(ContentValues branch) {
        Branch b = ContentValuesTobranch(branch);
       // branches.add(b);
        int result = db_branch.insertBranch(branch);
        return result;
    }

    @Override
    public void add_url_image(ContentValues contentValues) {

    }


    @Override
    public List<Models> allmodels() {
        Cursor res = db_model.getallmodel();
        modeles =new ArrayList<>();
        if (res != null && res.getCount() > 0) {
            while (res.moveToNext()) {
                Models m= new Models(
                        res.getString(1),
                        res.getString(2),
                        res.getString(0),
                        Float.parseFloat(res.getString(3)) ,
                        Integer.parseInt(res.getString(4)),
                        Integer.parseInt(res.getString(5)),
                        Boolean.parseBoolean(res.getString(6)),
                        Boolean.parseBoolean(res.getString(7)),
                        Boolean.parseBoolean(res.getString(8)));

                modeles.add(m);
            }
        }
        return modeles;
    }


    @Override
    public List<Client> allclient() {

        Cursor res = db_client.getallClient();
        clients =new ArrayList<>();
        if (res != null && res.getCount() > 0) {
            while (res.moveToNext()) {
                Client c = new Client(
                        res.getString(1), res.getString(0), res.getString(2), res.getString(3), res.getString(4));
                clients.add(c);
            }
        }

        return clients;
    }



    @Override
    public List<Branch> allbranch() {
        Cursor res = db_branch.getallBrancht();
        branches =new ArrayList<>();
        if (res != null && res.getCount() > 0) {
            while (res.moveToNext()) {
                Branch c = new Branch(
                        Integer.parseInt(res.getString(1)), Integer.parseInt(res.getString(0)),
                        new Address(res.getString(2),res.getString(3),res.getString(4))
                );
                branches.add(c);
            }
        }
        return branches;
    }


    @Override
    public List<Car> allcars() {
        Cursor res = db_car.getallcar();
        cars =new ArrayList<>();
        if (res != null && res.getCount() > 0) {
            while (res.moveToNext()) {
                Car c = new Car(
                        Integer.parseInt(res.getString(3)), Float.parseFloat(res.getString(2)) ,
                        res.getString(0),res.getString(1));
                cars.add(c);
            }
        }
        return cars;
    }

    @Override
    public void allurl() {

    }


}
