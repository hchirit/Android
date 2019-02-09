package com.example.app_3.Models.BackEnd;

import android.content.ContentValues;


import com.example.app_3.Models.entites.Branch;
import com.example.app_3.Models.entites.Car;
import com.example.app_3.Models.entites.UrlCarImage;

import java.util.List;

/**
 * Created by samuel on 26-Nov-17.
 */

public interface DBManager {



    public String get_url_image(String num);

    public Branch branchByFreeCarsofModels(int branchnumber);

    public void Allcars();

    public List<Car> allcars();

    public void Allbranch();

    public List<Branch> allbranch();

    public void allurl();


}
