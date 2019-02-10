package com.example.nathanmsika.Android5778_6244_8742_01.model.backend;

import android.content.ContentValues;

import com.example.nathanmsika.Android5778_6244_8742_01.model.datasource.Liste_DBManagers;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Branch;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Car;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Client;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Models;

import java.util.List;

/**
 * Created by nathanmsika on 14/11/2017.
 */

public class FactoryDB_manager {

    static DB_manager manager = null;


    public static DB_manager getinstance() {

        if (manager == null)
            manager = new Liste_DBManagers() {
            };
        return manager;
    }

}


