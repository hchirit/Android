package com.example.app_3.Models.BackEnd;


import com.example.app_3.Models.DataSource.MySQL_DBManager;

/**
 * Created by samuel on 26-Nov-17.
 */

public class FactoryDBManager {
    static DBManager  ourInstance = null;

    public static DBManager getInstance() {
        if(ourInstance == null)
            ourInstance = new MySQL_DBManager();
        return ourInstance;
    }

}
