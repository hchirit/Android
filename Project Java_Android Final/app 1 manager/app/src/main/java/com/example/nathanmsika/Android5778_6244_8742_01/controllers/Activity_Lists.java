package com.example.nathanmsika.Android5778_6244_8742_01.controllers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nathanmsika.Android5778_6244_8742_01.model.backend.DB_manager;
import com.example.nathanmsika.Android5778_6244_8742_01.model.backend.FactoryDB_manager;
import com.example.nathanmsika.Android5778_6244_8742_01.model.backend.FactoryDB_manager_;
import com.example.nathanmsika.Android5778_6244_8742_01.model.constants.DataBase_Client;
import com.example.nathanmsika.Android5778_6244_8742_01.model.constants.DataBase_branch;
import com.example.nathanmsika.Android5778_6244_8742_01.model.constants.DataBase_car;
import com.example.nathanmsika.Android5778_6244_8742_01.model.constants.DataBase_model;
import com.example.nathanmsika.Android5778_6244_8742_01.model.datasource.Liste_DBManagers;
import com.example.nathanmsika.Android5778_6244_8742_01.model.datasource.MySQL_DBManager;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Branch;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Car;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Client;
import com.example.nathanmsika.Android5778_6244_8742_01.model.entites.Models;
import com.example.nathanmsika.my_navigation_drawer.R;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nathanmsika on 08/11/2017.
 */

public class Activity_Lists extends AppCompatActivity {


    private static final String TAG ="MyApp";
    final String  LOG = "Lists";

    Button btnClick;
    ListView liste = null;
    String list_;
    DB_manager db;
    TextView tx;
    ProgressBar progressBar;
    int i;
    ImageView image_;
    ImageDownloaderTask class_image;
    Map<Integer, String> map_car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_list_layout);

        try{
        init_db();
        init_button();
        init_list();
      //  init_map_image();
        ClickMe();
        }
        catch (Exception ex)
        {
            Log.e(TAG,"error trouver: " +getClass().getName()+" "+ ex.getMessage()  );
        }

    }

    public void init_map_image()
    {
        new AsyncTask<Void, Void, List<Car>>() {
            @Override
            protected List<Car> doInBackground(Void... params) {
                try {
                    List<Car> carList = FactoryDB_manager_.getinstance().allcars();
                    return carList;
                }
                catch (Exception ex)
                {
                    Log.d("APP","error: " + ex.getMessage());
                }
                return null;
            }
            @Override
            protected void onPostExecute(List<Car> List_c) {
                map_car = new HashMap<>();
               for(Car item : List_c)
                   map_car.put(Integer.getInteger(item.getCarNumber()),"https://static.rentacar.fr/images/cms_uploaded/pages/hub-vehicule/vp/location-voiture-luxe-rentacar.png");
            }
        }.execute();
    }

    public void init_db()
    {
        db = FactoryDB_manager.getinstance();
        db.db_all(this);
    }
    public void init_button()
    {
        btnClick = (Button) findViewById(R.id.button_return_liste);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        progressBar = (ProgressBar) findViewById(R.id.progressBar_);
    }

    public void init_list()
    {
        liste = (ListView) findViewById(R.id.ma_listview);
        list_ = getIntent().getExtras().getString("Value").toString();
        tx = (TextView)findViewById(R.id.textView22) ;
    }
    private void ClickMe() {

        try {

            tx.setText("List " + list_);

              if(list_.equals("cars")) {
                  new AsyncTask<Void, Void, List<Car>>() {
                      @Override
                      protected List<Car> doInBackground(Void... params) {
                          try {
                              List<Car> carList = FactoryDB_manager_.getinstance().allcars();
                              FactoryDB_manager_.getinstance().allurl();
                              return carList;
                          }
                          catch (Exception ex)
                          {
                              Log.d("APP","error: " + ex.getMessage());
                          }
                          return null;
                      }
                      @Override
                      protected void onPostExecute(List<Car> List_c) {
                          initItemByListView_car(List_c);
                          progressBar.setVisibility(View.INVISIBLE);
                          Toast_();
                      }
                  }.execute();
              }
              else if (list_.equals("models"))
              {
                  new AsyncTask<Void, Void, List<Models>>() {
                      @Override
                      protected List<Models> doInBackground(Void... params) {
                          try {
                              List<Models> modelList = FactoryDB_manager_.getinstance().allmodels();
                              return modelList;
                          }
                          catch (Exception ex)
                              {
                                  Log.d("APP","error: " + ex.getMessage());
                              }
                              return null;
                      }
                      @Override
                      protected void onPostExecute(List<Models> List_m) {
                          initItemByListView_model(List_m);
                          progressBar.setVisibility(View.INVISIBLE);
                          Toast_();
                      }
                  }.execute();
              }
              else if (list_.equals("branch"))
              {
                  new AsyncTask<Void, Void, List<Branch>>() {
                      @Override
                      protected List<Branch> doInBackground(Void... params) {
                          try {
                              List<Branch> branchList = FactoryDB_manager_.getinstance().allbranch();
                              return branchList;
                          }
                          catch (Exception ex)
                          {
                              Log.d("APP","error: " + ex.getMessage());
                          }
                          return null;
                      }
                      @Override
                      protected void onPostExecute(List<Branch> List_b) {
                          initItemByListView_brach(List_b);
                          progressBar.setVisibility(View.INVISIBLE);
                          Toast_();
                      }
                  }.execute();
              }
              else if (list_.equals("client")) {

                  new AsyncTask<Void, Void, List<Client>>() {
                      @Override
                      protected List<Client> doInBackground(Void... params) {
                          try {
                              List<Client> clientList = FactoryDB_manager_.getinstance().allclient();
                              return clientList;
                          }
                          catch (Exception ex)
                          {
                              Log.d("APP","error: " + ex.getMessage());
                          }
                          return null;
                      }
                      @Override
                      protected void onPostExecute(List<Client> List_client) {
                          initItemByListView_client(List_client);
                          progressBar.setVisibility(View.INVISIBLE);
                          Toast_();
                      }
                  }.execute();
              }



        }
        catch (Exception ex)
        {
            Log.d("MyApp","error: " + ex.getMessage());
        }


    }

    private void Toast_() {
        Toast.makeText(this, "Data " + list_+
                " Retrieved Successfully", Toast.LENGTH_SHORT).show();
    }


    void initItemByListView_client(List<Client> Clients)
    {
        Log.e(LOG,"liste client");
        final List<Client> clients = Clients;

        ArrayAdapter<Client> adapter = new ArrayAdapter<Client>(this,
                R.layout.client, clients)
        {

            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                if (convertView == null)
                {
                    convertView = View.inflate(Activity_Lists.this,
                            R.layout.client,null);
                }

                TextView productId= (TextView) convertView.findViewById(R.id.id_id_);

                TextView productName = (TextView) convertView.findViewById(R.id.id_name);

                TextView productionphone = (TextView) convertView.findViewById(R.id.id_phone);

                TextView productionemail= (TextView) convertView.findViewById(R.id.id_email_);

                TextView productionDpass = (TextView) convertView.findViewById(R.id.id_pass);

                productId.setText(clients.get(position).getID());
                productName.setText(clients.get(position).getName());
                productionphone.setText(clients.get(position).getPhoneNumber());
                productionemail.setText(clients.get(position).getEmailAddress());
                productionDpass.setText(clients.get(position).getPassword());

                return convertView;
            }

        };

        liste.setAdapter(adapter);

      //  this.setContentView(listView);
    }


    void initItemByListView_model(List<Models> modele)
    {
         Log.e(LOG,"liste model ");
         final List<Models> models = modele;
         //liste = null;
         ArrayAdapter<Models> adapter = new ArrayAdapter<Models>(this,
                R.layout.model_listview, models)
        {

            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                if (convertView == null)
                {
                    convertView = View.inflate(Activity_Lists.this,
                            R.layout.model_listview,null);
                }

                TextView product_model= (TextView) convertView.findViewById(R.id.id_model_);

                TextView product_manu = (TextView) convertView.findViewById(R.id.id_maufacturer_);

                TextView product_name = (TextView) convertView.findViewById(R.id.id_name_model_);

                TextView product_engineca= (TextView) convertView.findViewById(R.id.id_enginecapacity_);

                TextView product_placenum = (TextView) convertView.findViewById(R.id.id_placenumber_);

                TextView product_doornum = (TextView) convertView.findViewById(R.id.id_doornumber_);

                TextView p_gps = (TextView) convertView.findViewById(R.id.id_check_gps_);
                TextView p_luggage = (TextView) convertView.findViewById(R.id.id_check_luggage_);
                TextView p_air = (TextView) convertView.findViewById(R.id.id_check_air_);




                product_model.setText(models.get(position).getModel());
                product_manu.setText(models.get(position).getManufacturer());
                product_name.setText(models.get(position).getName());
                product_engineca.setText(Float.toString(models.get(position).getEngineCapacity()));
                product_placenum.setText(Integer.toString(models.get(position).getPlacenumbers()));
                product_doornum.setText(Integer.toString(models.get(position).getDoornumbers()));



                p_gps.setText(Boolean.toString(models.get(position).isGps()));
                p_luggage.setText(Boolean.toString(models.get(position).isLuggage()));
                p_air.setText(Boolean.toString(models.get(position).isAirConditionner()));


                return convertView;
            }

        };

        Log.e(LOG,"liste model fait");
        liste.setAdapter(adapter);

        //  this.setContentView(listView);
    }

    void initItemByListView_brach(final List<Branch> branch)
    {
        Log.e(LOG,"liste branch");
        final List<Branch> branchs = branch;

        ArrayAdapter<Branch> adapter = new ArrayAdapter<Branch>(this,
                R.layout.branch_list, branchs)
        {

            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                if (convertView == null)
                {
                    convertView = View.inflate(Activity_Lists.this,
                            R.layout.branch_list,null);
                }

                //     TextView product_address= (TextView) convertView.findViewById(R.id.id_address__); //string

                TextView product_parkplace = (TextView) convertView.findViewById(R.id.id_parkplace__); //int

                TextView product_branchnum = (TextView) convertView.findViewById(R.id.id_branchnumber__); //int


                TextView product_city = (TextView) convertView.findViewById(R.id.id_addresscity); //string

                TextView product_street = (TextView) convertView.findViewById(R.id.id_addressstreet); //string

                TextView product_number = (TextView) convertView.findViewById(R.id.id_addressnumber); //string






                product_parkplace.setText(Integer.toString(branchs.get(position).getParkplace()));

                product_branchnum.setText(Integer.toString(branchs.get(position).getBranchNumber()));

                product_city.setText(branchs.get(position).getAddress_().getCity());

                product_street.setText(branchs.get(position).getAddress_().getStreet());

                product_number.setText(branchs.get(position).getAddress_().getPhoneNumber());


                return convertView;
            }

        };

        Log.e(LOG,"liste branch fait");
        liste.setAdapter(adapter);

        //  this.setContentView(listView);
    }

    void initItemByListView_car(final List<Car> car)
    {
        Log.e(LOG,"liste car");
        final List<Car> cars = car;

        ArrayAdapter<Car> adapter = new ArrayAdapter<Car>(this,
                R.layout.car_listview, cars)
        {

            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                if (convertView == null)
                {
                    convertView = View.inflate(Activity_Lists.this,
                            R.layout.car_listview,null);
                }

                TextView product_branch= (TextView) convertView.findViewById(R.id.id_branchparknum); //int

                TextView product_km = (TextView) convertView.findViewById(R.id.id_km_); //float

                TextView product_carnum = (TextView) convertView.findViewById(R.id.id_carnumber_);

                TextView product_class= (TextView) convertView.findViewById(R.id.id_class_);

                image_ = (ImageView) convertView.findViewById(R.id.imageView_car);


                String url_image = FactoryDB_manager_.getinstance().get_url_image(car.get(position).getCarNumber());
                if (url_image != null) {
                    ImageDownloaderTask class_image = (ImageDownloaderTask) new ImageDownloaderTask(image_)
                            .execute(url_image);
                }

                product_branch.setText(Integer.toString(cars.get(position).getBranchParkNumber()));

                product_km.setText(Float.toString(cars.get(position).getKm()));

                product_carnum.setText(cars.get(position).getCarNumber());

                product_class.setText(cars.get(position).get_class());


                return convertView;
            }

        };

        Log.e(LOG,"liste car fait");
        liste.setAdapter(adapter);

        //  this.setContentView(listView);
    }








    // to load image from url

    class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {

        private final WeakReference<ImageView> imageViewReference;

        public ImageDownloaderTask(ImageView imageView) {
            imageViewReference = new WeakReference<ImageView>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            return downloadBitmap(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (isCancelled()) {
                bitmap = null;
            }

            if (imageViewReference != null) {
                ImageView imageView = imageViewReference.get();
                if (imageView != null) {
                    if (bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    } else {
                        Drawable placeholder = null;
                        imageView.setImageDrawable(placeholder);
                    }
                }
            }
        }

        private Bitmap downloadBitmap(String url) {
            HttpURLConnection urlConnection = null;
            try {
                URL uri = new URL(url);
                urlConnection = (HttpURLConnection) uri.openConnection();

                final int responseCode = urlConnection.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    return null;
                }

                InputStream inputStream = urlConnection.getInputStream();
                if (inputStream != null) {
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    return bitmap;
                }
            } catch (Exception e) {
                urlConnection.disconnect();
                Log.w("ImageDownloader", "Errore durante il download da " + url);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return null;
        }
    }

}
