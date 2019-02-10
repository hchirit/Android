package com.example.nathanmsika.Android5778_6244_8742_01.model.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.nathanmsika.Android5778_6244_8742_01.model.backend.AcademyConst;
import com.example.nathanmsika.Android5778_6244_8742_01.model.backend.DB_manager;
import com.example.nathanmsika.Android5778_6244_8742_01.model.constants.DataBase_Client;
import com.example.nathanmsika.Android5778_6244_8742_01.model.constants.DataBase_branch;
import com.example.nathanmsika.Android5778_6244_8742_01.model.constants.DataBase_car;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Branch;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Car;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Client;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Models;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.UrlCarImage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nathanmsika on 18/11/2017.
 */

public class MySQL_DBManager implements DB_manager{

    private static final String TAG = "MyApp";
    private final String UserName="nmsika";
    private final String WEB_URL = "http://"+UserName+".vlab.jct.ac.il/Academy/";
    static List<UrlCarImage> urlCarImages;

    static {
        urlCarImages = new ArrayList<>();
    }
    private boolean updateFlag = false;


    public void printLog(String message)
    {
        Log.d(this.getClass().getName(),"\n"+message);
    }
    public void printLog(Exception message)
    {
        Log.d(TAG,"Exception-->\n"+message);
    }

    @Override
    public boolean check_exist_client(Client c) {
        for(Client item : this.allclient()){
            if(item.getName().equals(c.getName())&&
                    item.getPassword().equals(c.getPassword()) )
                return true;
        }
        return false;
    }


    ///add to list

    @Override
    public String addclient(ContentValues client) {
        try {
            String result = PHPtools.POST(WEB_URL + "/Addclient.php", client);
            return result;
        } catch (IOException e) {
            printLog("addClient Exception:\n" + e);
            return "false";
        }
    }

    @Override
    public String addmodel(ContentValues model) {
        try {
            String result = PHPtools.POST(WEB_URL + "/addmodel.php", model);
            return result;
        } catch (IOException e) {
            printLog("addmodel Exception:\n" + e);
            return "false";
        }
    }

    @Override
    public int addcar(ContentValues car) {
        try {
            String result = PHPtools.POST(WEB_URL + "/addcar.php", car);
            return Integer.parseInt(result);
        } catch (IOException e) {
            printLog("addUser Exception:\n" + e);
            return 0;
        }
    }

    @Override
    public int addbranch(ContentValues branch) {
        try {
            String result = PHPtools.POST(WEB_URL + "/addbranch.php", branch);
            return 1;
        } catch (IOException e) {
            printLog("addUser Exception:\n" + e);
            return 0;
        }
    }

    @Override
    public void add_url_image(ContentValues contentValues) {
        try {
            String result = PHPtools.POST(WEB_URL + "/addurl.php", contentValues);

        } catch (IOException e) {
            Log.e(TAG,"Exception_url-->\n"+e.getMessage());
        }
    }


    /// all list

    @Override
    public List<Models> allmodels() {
        List<Models> result = new ArrayList<Models>();
        try {

            String str = PHPtools.GET(WEB_URL + "/getModel.php");
            JSONArray array = new JSONObject(str).getJSONArray("models");

            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Models models = AcademyConst.contentValuesTomodel(contentValues);
                result.add(models);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> allclient() {

        List<Client> result = new ArrayList<Client>();
        try {
            String str = PHPtools.GET(WEB_URL + "/getClients.php");
            JSONArray array = new JSONObject(str).getJSONArray("clients");

            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Client client = AcademyConst.ContentValuesToclient(contentValues);
                result.add(client);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Branch> allbranch() {
        List<Branch> result = new ArrayList<Branch>();
        try {

            String str = PHPtools.GET(WEB_URL + "/getBranchs.php");
            JSONArray array = new JSONObject(str).getJSONArray("branchs");

            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Branch branch = AcademyConst.ContentValuesTobranch(contentValues);
                result.add(branch);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> allcars() {
        List<Car> result = new ArrayList<Car>();
        try {

            String str = PHPtools.GET(WEB_URL + "/getCar.php");
            JSONArray array = new JSONObject(str).getJSONArray("cars");

            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Car car = AcademyConst.ContentValuesTocar(contentValues);
                result.add(car);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void allurl() {
        try {
            String str = PHPtools.GET(WEB_URL + "/geturl.php");
            JSONArray array = new JSONObject(str).getJSONArray("urls");

            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                UrlCarImage r = AcademyConst.ContentValuesTourlimage(contentValues);
                urlCarImages.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void db_all(Context context) {

    }

    @Override
    public Cursor Allmodels() {
        return null;
    }

    @Override
    public Cursor Allclient() {
        return null;
    }

    @Override
    public Cursor Allcar() {
        return null;
    }

    @Override
    public Cursor Allbranch() {
        return null;
    }

    @Override
    public String get_url_image(String num) {
        for(UrlCarImage item : urlCarImages)
            if(item.getId_car() == Integer.parseInt(num))
                return item.getUrl();
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

    private void SetUpdate()
    {
        updateFlag = true;
    }

}
