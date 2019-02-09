package com.example.samuel.android5778_6244_8742_03.Activity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.AcademyConst;
import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.DBManager;
import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.FactoryDBManager;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Branch;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Car;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Reservation;
import com.example.samuel.android5778_6244_8742_03.R;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nathanmsika on 09/12/2017.
 */

public class Activity_StatutReseration extends AppCompatActivity {

    final String  LOG = "MyApp";
    TextView id,Amount,timeUse,timeend ;
    EditText     kilometre ;
    DBManager db = FactoryDBManager.getInstance();
    int id_client ;
    Spinner spinner;
    ListView listView;
    List<Car> car_list;
    String date_now;
    int id_car , km_before;
    int amount_;

    ProgressBar progressBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG,"classe"+ this.getClass().toString());
        setContentView(R.layout.statut_reservation);

        try {
            init_textview();
            init_button();
            init_spinner();
        }catch (Exception ex)
        {
            Log.e(LOG,"error :: " + ex.getMessage()+" :: classe"+ this.getClass().toString());
        }

    }

    public void init_lisview(int id_) {
        try {
            car_list =new ArrayList<>();
            for(Car item : db.Allcars())
                if(item.getCarNumber().equals(Integer.toString(id_)))
                   car_list.add(item);
            initItemByListView_car(car_list);
        }

        catch (Exception ex) {
        Log.e("MyApp", "error" + ex.getMessage());
        }
    }

    public void init_spinner(){
        try {
            car_list = new ArrayList<>();
            List<Integer> id_car = new ArrayList<>();
            int i =0;

            for (Reservation item : db.Allreservation()) {
                if (item.getId_client() == db.getIdUser() && item.isStatus()==true) {
                    id_car.add(item.getCars_number());
                }
            }
            for (Car item : db.Allcars())
                for(int value : id_car) {
                    if (item.getCarNumber().equals(Integer.toString(value)))
                        car_list.add(item);
                }
        }

        catch (Exception ex) {
            Log.e("MyApp", "error" + ex.getMessage());
        }
        final List<String> list = new ArrayList<String>();
        for(Car item : db.Allcars()) {
            if (car_list.contains(item))
                list.add(item.getCarNumber());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
               id_car = Integer.parseInt(spinner.getSelectedItem().toString());
                for(Car item : db.Allcars()) {
                    if (item.getCarNumber().equals(String.valueOf(id_car)))
                        km_before = (int) item.getKm();
                }
                init_lisview(id_car);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
              //  id_car = Integer.parseInt(spinner.getSelectedItem().toString());
            }

        });
    }

    private void init_textview() {
        try {
            progressBar = (ProgressBar) findViewById(R.id.progressBar_statut);
            progressBar.setVisibility(View.INVISIBLE);
            spinner = (Spinner)findViewById(R.id.spinner_cars_id);
            id = (TextView) findViewById(R.id.textView22);
            Amount = (TextView) findViewById(R.id.textView9);
            kilometre = (EditText) findViewById(R.id.editText12);
            id_client = db.getIdUser();
            id.setText("ID Client :" + String.valueOf(id_client));
            listView = (ListView) findViewById(R.id.list_view_reservation);
            initdate ();
            fermer_clavier();
        }
        catch (Exception ex) {
            Log.e("MyApp", "error" + ex.getMessage());
        }
    }


    void fermer_clavier(){
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    void initdate ()
    {
        try {
            String date_b = null;
            String format = "dd/MM/yy H:mm:ss";
            java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
            java.util.Date date = new java.util.Date();
            date_now = formater.format( date );
            timeend.setText(date_now);

            for (Reservation item : db.Allreservation())
                if(item.getId_client()== id_car)
                    date_b = item.getDateBegining();

            int years = Integer.parseInt(date_now.substring(7,8)) -Integer.parseInt(date_b.substring(7,8))   ;
            int month = Integer.parseInt(date_now.substring(4,5)) -Integer.parseInt(date_b.substring(4,5))   ;
            int day = Integer.parseInt(date_now.substring(1,2)) -Integer.parseInt(date_b.substring(1,2))   ;

            //    int hours = Integer.parseInt(date_now.substring(10,11)) -Integer.parseInt(date_b.substring(10,11))   ;
            //    int min = Integer.parseInt(date_now.substring(13,14)) -Integer.parseInt(date_b.substring(13,14))   ;
            //   int sec = Integer.parseInt(date_now.substring(16,17)) -Integer.parseInt(date_b.substring(16,17))   ;

            String time_use = "Time Use: Day:"+day+" Month:"+month+" Year:"+years; //+"H:"+hours +"M:"+min +"S:"+sec ;

            //  amount_ = Integer.parseInt(date_b.substring(1,2)) * 180 ; // 180 shekel for 1 day
            //  Amount.setText(String.valueOf(amount_));

            timeUse.setText(time_use);

        }catch (Exception ex)
        {
        Log.e(LOG,"error :: " + ex.getMessage()+" :: classe"+ this.getClass().toString());
        }
    }

    void init_button()
    {
       Button pay = (Button) findViewById(R.id.button2);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Setting Alert Dialog Title
                alertDialogBuilder.setTitle("Confirm to pay ");
                // Icon Of Alert Dialog
                alertDialogBuilder.setIcon(R.drawable.ic_check_black_24dp);
                // Setting Alert Dialog Message
                alertDialogBuilder.setMessage("The amount is " + "220 $" );
                alertDialogBuilder.setCancelable(false);

                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        init_reservation();
                    }

                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                     dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            }
        });

    }

    private void init_reservation() {

        if(kilometre.length()!= 0  && car_list.size()!=0) {

          ContentValues contentvalue = new ContentValues();


            for (Reservation item : db.Allreservation()) {
                if(item.getCars_number() == id_car && item.getId_client()==id_client && item.isStatus()) {
                    contentvalue = new ContentValues();
                    item.setDateEnd(date_now);
                    contentvalue = AcademyConst.ReservationToContentValues(item);
                }
            }

            final float value_ =  Float.valueOf(kilometre.getText().toString());
            final ContentValues finalContentvalue = contentvalue;
            progressBar.setVisibility(View.VISIBLE);
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    FactoryDBManager.getInstance().endReservation(finalContentvalue,value_);
                    FactoryDBManager.getInstance().initAllList();
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Toast_end();
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            }.execute();
        }
        else
            Toast.makeText(this, "imput not correct",Toast.LENGTH_LONG).show();

    }




    public void toast()
    {
        Toast.makeText(this,"you doesn't have a resevation id " + id_client,Toast.LENGTH_LONG).show();
    }

    private void Toast_end() {
        Toast.makeText(this,"end reservation successful " + id_client,Toast.LENGTH_LONG).show();
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
                    convertView = View.inflate(Activity_StatutReseration.this,
                            R.layout.car_listview,null);
                }

                TextView product_branch= (TextView) convertView.findViewById(R.id.id_branchparknum); //int

                TextView product_km = (TextView) convertView.findViewById(R.id.id_km_); //float

                TextView product_carnum = (TextView) convertView.findViewById(R.id.id_carnumber_);

                TextView product_class= (TextView) convertView.findViewById(R.id.id_class_);

               ImageView image_ = (ImageView) convertView.findViewById(R.id.imageView_car);

                String url_image = FactoryDBManager.getInstance().get_url_image(car.get(position).getCarNumber());
                if (url_image != null) {
                    Bitmap bitmap = new ImageSaver(this.getContext()).
                            setFileName(car.get(position).getCarNumber()).
                            setDirectoryName("images").
                            load();
                    image_.setImageBitmap(bitmap);
                }

                product_branch.setText(Integer.toString(cars.get(position).getBranchParkNumber()));

                product_km.setText(Float.toString(cars.get(position).getKm()));

                product_carnum.setText(cars.get(position).getCarNumber());

                product_class.setText(cars.get(position).get_class());

                return convertView;
            }

        };

        listView.setAdapter(adapter);
        Log.e(LOG,"liste car fait");
        //  this.setContentView(listView);
    }



}
