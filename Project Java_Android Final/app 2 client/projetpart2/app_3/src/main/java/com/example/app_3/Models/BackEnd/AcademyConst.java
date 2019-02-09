package com.example.app_3.Models.BackEnd;

import android.content.ContentValues;
import android.net.Uri;

import com.example.app_3.Models.entites.Address;
import com.example.app_3.Models.entites.Branch;
import com.example.app_3.Models.entites.Car;
import com.example.app_3.Models.entites.UrlCarImage;

/**
 * Created by nathanmsika on 13/11/2017.
 */

public class AcademyConst {

    /**
     * The authority for the contacts provider
     */
    public static final String AUTHORITY = "com.example.nathanmsika.Android5778_6244_8742_01";
    /**
     * A content:// style uri to the authority for the contacts provider
     */
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);






    public static class CarConst {
        public static final String IDCAR = "_idnumber";
        public static final String BRANCHPARKNUM = "branchParkNumber";
        public static final String KM = "km";
        public static final String CLASS = "_class";

        /**
         * The content:// style URI for this table
         */
        public static final Uri ACTIVITY_URI = Uri.withAppendedPath(AUTHORITY_URI, "cars");
    }




    public static class BranchConst {

        public static final String branchnumber = "branchnumber";
        public static final String ParkPlace = "parkplace";
        public static final String a_city = "cityaddress";
        public static final String a_street = "streetaddress";
        public static final String a_number = "numberaddress";



        /**
         * The content:// style URI for this table
         */
        public static final Uri ACTIVITY_URI = Uri.withAppendedPath(AUTHORITY_URI, "branches");
    }





    public static class UrlCarImageConst {

        public static final String id_car = "id";
        public static final String url_ = "url";

        /**
         * The content:// style URI for this table
         */
        public static final Uri ACTIVITY_URI = Uri.withAppendedPath(AUTHORITY_URI, "url");
    }








        /////car
        public static ContentValues CarToContentValues(Car c) {

            ContentValues contentValues = new ContentValues();
            contentValues.put(CarConst.IDCAR, c.getCarNumber());
            contentValues.put(CarConst.BRANCHPARKNUM, c.getBranchParkNumber());
            contentValues.put(CarConst.KM, c.getKm());
            contentValues.put(CarConst.CLASS, c.get_class());
            return contentValues;
        }

        public static Car ContentValuesTocar(ContentValues contentValues)
        {
            Car c = new Car();
            c.setCarNumber(contentValues.getAsString(CarConst.IDCAR));
            c.setBranchParkNumber(contentValues.getAsInteger(CarConst.BRANCHPARKNUM));
            c.setKm(contentValues.getAsFloat(CarConst.KM));
            c.set_class(contentValues.getAsString(CarConst.CLASS));
            return c;
        }
        //////



        /////branch
        public static ContentValues branchToContentValues(Branch b) {

            Address a = b.getAddress_();

            ContentValues contentValues = new ContentValues();
            contentValues.put(BranchConst.branchnumber, b.getBranchNumber());
            contentValues.put(BranchConst.ParkPlace ,b.getParkplace());
            contentValues.put(BranchConst.a_city, a.getCity());
            contentValues.put(BranchConst.a_street,a.getStreet());
            contentValues.put(BranchConst.a_number, a.getPhoneNumber());
            return contentValues;
        }

        public static Branch ContentValuesTobranch(ContentValues contentValues)
        {
            Branch b =new Branch();
            Address a = new Address();
            b.setBranchNumber(contentValues.getAsInteger(BranchConst.branchnumber));
            b.setParkplace(contentValues.getAsInteger(BranchConst.ParkPlace));

            a.setCity(contentValues.getAsString(BranchConst.a_city));
            a.setStreet(contentValues.getAsString(BranchConst.a_street));
            a.setPhoneNumber(contentValues.getAsString(BranchConst.a_number));

            b.setAddress_(a);
            return b;
        }
        //////


    /////url
    public static ContentValues urlimageToContentValues(UrlCarImage u) {


        ContentValues contentValues = new ContentValues();
        contentValues.put(UrlCarImageConst.id_car, u.getId_car());
        contentValues.put(UrlCarImageConst.url_ ,u.getUrl());
        return contentValues;
    }

    public static UrlCarImage ContentValuesTourlimage(ContentValues contentValues)
    {
        UrlCarImage u =new UrlCarImage();


        u.setId_car(contentValues.getAsInteger(UrlCarImageConst.id_car));
        u.setUrl(contentValues.getAsString(UrlCarImageConst.url_));

        return u;
    }
    //////

    }

