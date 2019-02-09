package com.example.samuel.android5778_6244_8742_03.Models.DataSource;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.AcademyConst;
import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.DBManager;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Branch;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Car;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Client;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Models;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Reservation;
import com.example.samuel.android5778_6244_8742_03.Models.entites.UrlCarImage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.util.Patterns.WEB_URL;

/**
 * Created by samuel on 26-Nov-17.
 */

public class MySQL_DBManager implements DBManager {

    private static final String LOG = "MyApp";
    private final String UserName = "nmsika";
    private final String WEB_URL = "http://" + UserName + ".vlab.jct.ac.il/Academy/";

    static int id_users;

    static List<Models> models;
    static List<Client> clients;
    static List<Car> cars;
    static List<Branch> branches;
    static List<Reservation> reservation;
    static List<UrlCarImage> urlCarImages;

    static {
        clients = new ArrayList<>();
        cars = new ArrayList<>();
        branches = new ArrayList<>();
        models = new ArrayList<>();
        reservation = new ArrayList<>();
        urlCarImages = new ArrayList<>();
    }

    private boolean updateFlag = false;


    public void printLog(String message) {
        Log.d(LOG, "\n" + message);
    }

    public void printLog(Exception message) {
        Log.d(LOG, "Exception-->\n" + message);
    }

    @Override
    public boolean existClient(String name, String pass) {
        for(Client item : clients)
            if(item.getPassword().equals(pass) && item.getName().equals(name))
                return true;
        return false;
    }

    @Override
    public void addClient(ContentValues values) {
        try {
            String result = PHPtools.POST(WEB_URL + "/Addclient.php", values);
            this.allClient();
        } catch (IOException e) {
            printLog("addClient Exception:\n" + e);

        }

    }


    @Override
    public void updatekmCar(float km, ContentValues values) {
        try {

            Reservation reservation = AcademyConst.ContentValuesToreservation(values);
            Car car_ = new Car();
            for (Car item : Allcars())
            {
                if (item.getCarNumber().equals(String.valueOf(reservation.getCars_number())))
                    car_ = item;
            }
            car_.setKm(km);
            String result = PHPtools.POST(WEB_URL + "/update_km_car.php", AcademyConst.CarToContentValues(car_));
            SetUpdate();
        } catch (IOException e) {
            printLog("updatekm Exception:\n" + e);
        }
    }

    @Override
    public String get_url_image(String num) {
        for(UrlCarImage item : urlCarImages)
            if(item.getId_car() == Integer.parseInt(num))
                return item.getUrl();
        return null;
    }


    @Override
    public List<Car> freeCars() {
        List<Car> list_ = new ArrayList<>();
        try {
            for (Car item : cars) {
                if (!check_if_have_reservation(Integer.parseInt(item.getCarNumber())))
                    list_.add(item);
            }
            return list_;
        } catch (Exception e) {
            printLog(e);
        }
        return list_;
    }

    @Override
    public List<Car> freeCarsforbranch(int idBranch) {
        try {
            List<Car> car = new ArrayList<>();
            for (Car item : freeCars()) {
                if (item.getBranchParkNumber() == idBranch)
                    car.add(item);
            }
            return car;

        } catch (Exception e) {
            printLog(e);
        }
        return null;
    }

    @Override
    public List<Car> freeCarsformodel(String model) {
        try {
            List<Car> car = new ArrayList<>();
            for (Car item : freeCars()) {
                if (item.get_class().equals(model))
                    car.add(item);
            }
            return car;

        } catch (Exception e) {
            printLog(e);
        }
        return null;
    }

    @Override
    public List<Car> freeNearsCars(String city) {
        try {
            List<Car> car = new ArrayList<>();
            for (Branch branch : branchbycity(city)) {
                for (Car item : freeCarsforbranch(branch.getBranchNumber()))
                    car.add(item);
            }
            return car;

        } catch (Exception e) {
            printLog(e);
        }
        return null;
    }

    @Override
    public List<Branch> branchbycity(String city) {
        List<Branch> branch = new ArrayList<>();
        try {
                for (Branch item : branches) {
                    if (item.getAddress_().getCity().equals(city)) {
                        branch.add(item);
                    }
                }
                return branch;

            } catch (Exception e) {
                Log.e("MyApp","error : branchbycity" + e.getMessage());
            }
            return branch;
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
            printLog(e);
        }
        return null;
    }

    @Override
    public List<Reservation> notClosedReservation() {
        List<Reservation> _reservation = new ArrayList<>();
        for (Reservation item : reservation) {
            if (item.isStatus() == true)
                _reservation.add(item);
        }
        return _reservation;
    }

    @Override
    public void addReservation(ContentValues values) {
        try {
            String result = PHPtools.POST(WEB_URL + "/AddReservation.php", values);
            SetUpdate();
        } catch (IOException e) {
            printLog("addReservation Exception:\n" + e);

        }

    }

    @Override
    public void endReservation(ContentValues values, float km) {
        try {
            this.updatekmCar(km, values);
            Reservation reservation = AcademyConst.ContentValuesToreservation(values);
            reservation.setStatus(false);
            reservation.setKmEnd(km);

            String result = PHPtools.POST(WEB_URL + "/update_statut_reservation.php", AcademyConst.ReservationToContentValues(reservation));
            SetUpdate();
        } catch (IOException e) {
            printLog("addReservation Exception:\n" + e);
        }
    }

    @Override
    public boolean closedReservation(ContentValues values) {
        Reservation reservation = AcademyConst.ContentValuesToreservation(values);

        if (reservation.isStatus() == true)
            return false;
        else
            return true;
    }

    @Override
    public boolean check_if_have_reservation(int id_car) {
     //   this.initAllList();
        for(Reservation item : reservation)
            if(item.getCars_number() == id_car && item.isStatus())
            return true;
        return false;
    }

    @Override
    public boolean check_if_Client_have_reservation(int id_client) {
        for(Reservation item : reservation)
            if(item.getId_client() == id_client )
                return true;
        return false;
    }

    @Override
    public void add_id_user(int id) {
        id_users = id;
    }

    @Override
    public int getIdUser() {
        return id_users;
    }


    //// all return list

    @Override
    public List<Client> AllClient() {
        return clients;
    }

    @Override
    public List<Models> AllModels() {
        return models;
    }

    @Override
    public List<Branch> AllBranchs() {
        return branches;
    }

    @Override
    public List<Car> Allcars() {
        return cars;
    }

    @Override
    public List<Reservation> Allreservation() {
        return reservation;
    }

    @Override
    public List<UrlCarImage> Allurl() {
        return urlCarImages;
    }

    @Override
    public void initAllList() {
        this.allurl();
        this.allModels();
        this.allBranchs();
        this.allreservation();
        this.allClient();
        this.allcars();
    }

    @Override
    public boolean isUpdatet() {
        if(updateFlag)
        {
            updateFlag=false;
            return  true;
        }
        return  false;
    }

    /////

    private void SetUpdate()
    {
        initAllList();
        updateFlag = true;
    }





    //// add_static_list  with service
    @Override
    public void allBranchs() {
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
    public void allcars() {
        try {
            cars = new ArrayList<>();
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
    public void allreservation() {
        try {
            reservation = new ArrayList<>();
            String str = PHPtools.GET(WEB_URL + "/getReservation.php");
            JSONArray array = new JSONObject(str).getJSONArray("reservations");

            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Reservation r = AcademyConst.ContentValuesToreservation(contentValues);
                reservation.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    @Override
    public void allModels() {
        try {
            models = new ArrayList<>();
            String str = PHPtools.GET(WEB_URL + "/getModel.php");
            JSONArray array = new JSONObject(str).getJSONArray("models");

            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Models _models = AcademyConst.contentValuesTomodel(contentValues);
                models.add(_models);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void allClient() {

        try {
            clients = new ArrayList<>();
            String str = PHPtools.GET(WEB_URL + "/getClients.php");
            JSONArray array = new JSONObject(str).getJSONArray("clients");

            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Client client = AcademyConst.ContentValuesToclient(contentValues);
                clients.add(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //////



}


