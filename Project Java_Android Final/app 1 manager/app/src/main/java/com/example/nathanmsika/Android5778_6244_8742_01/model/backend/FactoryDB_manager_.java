package com.example.nathanmsika.Android5778_6244_8742_01.model.backend;

import com.example.nathanmsika.Android5778_6244_8742_01.model.datasource.Liste_DBManagers;
import com.example.nathanmsika.Android5778_6244_8742_01.model.datasource.MySQL_DBManager;

/**
 * Created by nathanmsika on 01/12/2017.
 */

public class FactoryDB_manager_ {

    static DB_manager manager = null;


    public static DB_manager getinstance() {

        if (manager == null)
            manager = new MySQL_DBManager() {
            };
        return manager;
    }

}