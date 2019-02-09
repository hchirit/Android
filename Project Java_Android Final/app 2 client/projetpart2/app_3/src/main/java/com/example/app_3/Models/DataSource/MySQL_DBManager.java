package com.example.app_3.Models.DataSource;

import android.content.ContentValues;
import android.util.Log;

import com.example.app_3.Models.BackEnd.AcademyConst;
import com.example.app_3.Models.BackEnd.DBManager;
import com.example.app_3.Models.entites.Branch;
import com.example.app_3.Models.entites.Car;
import com.example.app_3.Models.entites.UrlCarImage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by samuel on 26-Nov-17.
 */

public class MySQL_DBManager implements DBManager {

    private static final String LOG = "MyApp";
    private final String UserName = "nmsika";
    private final String WEB_URL = "http://" + UserName + ".vlab.jct.ac.il/Academy/";

    static int id_users;

    static List<Car> cars;
    static List<Branch> branches;
    static List<UrlCarImage> urlCarImages;

    static {
        cars = new ArrayList<>();
        branches = new ArrayList<>();
        urlCarImages = new ArrayList<>();
    }






    @Override
    public String get_url_image(String num) {
        for(UrlCarImage item : urlCarImages)
            if(item.getId_car() == Integer.parseInt(num))
                return item.getUrl();
        return null;
    }




    @Override
    public Branch branchByFreeCarsofModels(int brachnumber) {
        try {
            for (Branch item : branches) {
                if (item.getBranchNumber()== brachnumber) {
                    return item;
                }
            }

        } catch (Exception e) {
        }
        return null;
    }


    @Override
    public List<Branch> allbranch() {
        return branches;
    }



    @Override
    public List<Car> allcars() {
        return cars;
    }











    @Override
    public void Allbranch() {
        try {
            branches = new ArrayList<>();
            String str = PHPtools.GET(WEB_URL + "/getBranchs.php");
            JSONArray array = new JSONObject(str).getJSONArray("branchs");

            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Branch branchs = AcademyConst.ContentValuesTobranch(contentValues);
                branches.add(branchs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Allcars() {
        try {
            cars = new ArrayList<>();
            List<Car> car = new ArrayList<>();
            String str = PHPtools.GET(WEB_URL + "/getCar.php");
            JSONArray array = new JSONObject(str).getJSONArray("cars");

            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Car c = AcademyConst.ContentValuesTocar(contentValues);
                cars.add(c);
            }
        } catch (Exception e) {
            Log.d(LOG, "error  all cars: " + e.getMessage());
        }
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




}


