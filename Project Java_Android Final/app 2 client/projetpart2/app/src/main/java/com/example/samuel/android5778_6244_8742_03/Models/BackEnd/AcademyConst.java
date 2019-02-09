package com.example.samuel.android5778_6244_8742_03.Models.BackEnd;

import android.content.ContentValues;
import android.net.Uri;


import com.example.samuel.android5778_6244_8742_03.Models.entites.Address;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Branch;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Car;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Client;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Models;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Reservation;
import com.example.samuel.android5778_6244_8742_03.Models.entites.UrlCarImage;

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




    public static class ClientConst {
        public static final String ID = "_id";
        public static final String NAME = "name";
        public static final String PHONE = "phone";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";

        /**
         * The content:// style URI for this table
         */
        public static final Uri ACTIVITY_URI = Uri.withAppendedPath(AUTHORITY_URI, "clients");
    }


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

    public static class ModelstConst {
        public static final String MODEL = "model";
        public static final String MANUFACTURER = "manufacturer";
        public static final String NAME = "name";
        public static final String CAPACITY = "engineCapacity";
        public static final String GEARBOX = "_gearbox";
        public static final String PLACENUMBER = "placenumbers";
        public static final String DOORNUMBERS = "doornumbers";
        public static final String LUGGAGE = "luggage";
        public static final String GPS = "gps";
        public static final String AIRCONDITIONNER = "airConditionner";

        /**
         * The content:// style URI for this table
         */
        public static final Uri ACTIVITY_URI = Uri.withAppendedPath(AUTHORITY_URI, "models");

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



    public static class ReservationtConst {

        public static final String RESERVATION_NUMBER = "reservation_Number";
        public static final String ID_CLIENT = "id_client";
        public static final String STATUT = "status";
        public static final String CAR_NUM = "cars_number";
        public static final String DATE_B = "dateBegining";
        public static final String DATE_E = "dateEnd";
        public static final String KM_B = "kmBegin";
        public static final String KM_E = "kmEnd";
        public static final String GAS_STATUTS = "gasStatus";
        public static final String GAS_LITER = "gasLiter";
        public static final String BILLS = "bills";
        /**
         * The content:// style URI for this table
         */
        public static final Uri ACTIVITY_URI = Uri.withAppendedPath(AUTHORITY_URI, "reservation");

    }

    public static class UrlCarImageConst {

        public static final String id_car = "id";
        public static final String url_ = "url";

        /**
         * The content:// style URI for this table
         */
        public static final Uri ACTIVITY_URI = Uri.withAppendedPath(AUTHORITY_URI, "url");
    }








    /////client
        public static ContentValues ClientToContentValues(Client c) {

            ContentValues contentValues = new ContentValues();
            contentValues.put(ClientConst.ID, c.getID());
            contentValues.put(ClientConst.NAME, c.getName());
            contentValues.put(ClientConst.PHONE, c.getPhoneNumber());
            contentValues.put(ClientConst.EMAIL, c.getEmailAddress());
            contentValues.put(ClientConst.PASSWORD, c.getPassword());
            return contentValues;


        }

        public static Client ContentValuesToclient(ContentValues contentValues)
        {
            Client client = new Client();
            client.setID(contentValues.getAsString(ClientConst.ID));
            client.setName(contentValues.getAsString(ClientConst.NAME));
            client.setPhoneNumber(contentValues.getAsString(ClientConst.PHONE));
            client.setEmailAddress(contentValues.getAsString(ClientConst.EMAIL));
            client.setPassword(contentValues.getAsString(ClientConst.PASSWORD));
            return client;
        }
        //////

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

    /////modele
    public static ContentValues ModelToContentValues(Models m) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ModelstConst.MODEL, m.getModel());
        contentValues.put(ModelstConst.MANUFACTURER, m.getManufacturer());
        contentValues.put(ModelstConst.NAME, m.getName());
        contentValues.put(ModelstConst.CAPACITY, m.getEngineCapacity());
        //  contentValues.put(ModelstConst.GEARBOX, m.get_gearbox().toString()); //
        contentValues.put(ModelstConst.PLACENUMBER, m.getPlacenumbers());
        contentValues.put(ModelstConst.DOORNUMBERS, m.getDoornumbers());
        contentValues.put(ModelstConst.LUGGAGE, m.isLuggage());
        contentValues.put(ModelstConst.GPS, m.isGps());
        contentValues.put(ModelstConst.AIRCONDITIONNER, m.isAirConditionner());
        return contentValues;
    }

    public static Models contentValuesTomodel(ContentValues contentValues)
    {
        Models M = new Models();
        M.setAirConditionner(contentValues.getAsBoolean(ModelstConst.AIRCONDITIONNER));
        M.setModel(contentValues.getAsString(ModelstConst.MODEL));
        M.setGps(contentValues.getAsBoolean(ModelstConst.GPS));
        M.setLuggage(contentValues.getAsBoolean(ModelstConst.LUGGAGE));
        M.setEngineCapacity(contentValues.getAsFloat(ModelstConst.CAPACITY));
        M.setDoornumbers(contentValues.getAsInteger(ModelstConst.DOORNUMBERS));
        //  M.set_gearbox(gearbox.valueOf(ModelstConst.GEARBOX));
        M.setManufacturer(contentValues.getAsString(ModelstConst.MANUFACTURER));
        M.setName(contentValues.getAsString(ModelstConst.NAME));
        M.setPlacenumbers(contentValues.getAsInteger(ModelstConst.PLACENUMBER));
        return M;
    }
    //////


    /////Reservation
    public static ContentValues ReservationToContentValues(Reservation r) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ReservationtConst.RESERVATION_NUMBER, r.getReservation_Number() );
        contentValues.put(ReservationtConst.ID_CLIENT,r.getId_client() );
        contentValues.put(ReservationtConst.STATUT,r.isStatus() );
        contentValues.put(ReservationtConst.CAR_NUM,r.getCars_number() );
        contentValues.put(ReservationtConst.DATE_B,r.getDateBegining() );
        contentValues.put(ReservationtConst.DATE_E,r.getDateEnd() );
        contentValues.put(ReservationtConst.KM_B,r.getKmBegin() );
        contentValues.put(ReservationtConst.KM_E,r.getKmEnd() );
        contentValues.put(ReservationtConst.GAS_STATUTS,r.isGasStatus() );
        contentValues.put(ReservationtConst.GAS_LITER,r.getGasLiter() );
        contentValues.put(ReservationtConst.BILLS,r.getBills() );

        return contentValues;


    }

    public static Reservation ContentValuesToreservation(ContentValues contentValues)
    {
        Reservation reservatione = new Reservation();
        reservatione.setReservation_Number(contentValues.getAsInteger(ReservationtConst.RESERVATION_NUMBER));
        reservatione.setId_client(contentValues.getAsInteger(ReservationtConst.ID_CLIENT));
        reservatione.setStatus(contentValues.getAsBoolean(ReservationtConst.STATUT));
        reservatione.setCars_number(contentValues.getAsInteger(ReservationtConst.CAR_NUM));
        reservatione.setDateBegining(contentValues.getAsString(ReservationtConst.DATE_B));
        reservatione.setDateEnd(contentValues.getAsString(ReservationtConst.DATE_E));
        reservatione.setKmBegin(contentValues.getAsFloat(ReservationtConst.KM_B));
        reservatione.setKmEnd(contentValues.getAsFloat(ReservationtConst.KM_E));
        reservatione.setGasStatus(contentValues.getAsBoolean(ReservationtConst.GAS_STATUTS));
        reservatione.setGasLiter(contentValues.getAsFloat(ReservationtConst.GAS_LITER));
        reservatione.setBills(contentValues.getAsFloat(ReservationtConst.BILLS));

        return reservatione;
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

