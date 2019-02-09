package com.example.samuel.android5778_6244_8742_03.Models.entites;

/**
 * Created by nathanmsika on 07/12/2017.
 */

public class UrlCarImage {

    int id_car;
    String Url;


    public UrlCarImage(int id_car, String url) {
        this.id_car = id_car;
        Url = url;
    }


    public int getId_car() {
        return id_car;
    }

    public void setId_car(int id_car) {
        this.id_car = id_car;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public UrlCarImage() {
    }



}
