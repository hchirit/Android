package com.example.samuel.android5778_6244_8742_03.Activity;

import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.AcademyConst;
import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.DBManager;
import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.FactoryDBManager;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Car;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Reservation;
import com.example.samuel.android5778_6244_8742_03.R;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by nathanmsika on 09/12/2017.
 */

public class Activity_AddReservation extends AppCompatActivity {

    final String  LOG = "MyApp";
    TextView r_id , client_id  ,car_id,date_b,km_b ;
    EditText date_e;
    String  r_id_ , client_id_  ,car_id_,date_b_,km_b_ ;
    DBManager db = FactoryDBManager.getInstance();
    Button btnClick , add_r ;
    CheckBox checkBox;
    ProgressBar progressBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG,"classe"+ this.getClass().toString());
        setContentView(R.layout.activity_reservation);
        btnClick = (Button) findViewById(R.id.button_return_1);

        add_r = (Button) findViewById(R.id.button3);

        try {
            init_textview();
            init_button();

        }catch (Exception ex)
        {
            Log.e(LOG,"error :: " + ex.getMessage()+" :: classe "+ this.getClass().toString());
        }

    }


    private void init_textview() {
        try {
            progressBar = (ProgressBar) findViewById(R.id.progressBar_add);
            progressBar.setVisibility(View.INVISIBLE);
            // checkBox = (CheckBox) findViewById(R.id.checkBox_statut);
            r_id = (TextView) findViewById(R.id.editText_reservation_id);
            client_id = (TextView) findViewById(R.id.editText_client_id);
            car_id = (TextView) findViewById(R.id.editText_car_id);
            date_b = (TextView) findViewById(R.id.editText_date_begin);
            km_b = (TextView) findViewById(R.id.editText_km_begin);

            client_id_= Integer.toString(db.getIdUser());
            r_id_ =  Integer.toString(db.getIdUser() + 128);
            car_id_= getIntent().getExtras().getString("id_car").toString();
            km_b_ = getIntent().getExtras().getString("km_car").toString();

            client_id.setText("Client Id : "+client_id_);
            r_id.setText("Reservation id : "+r_id_);
            car_id.setText("Car Id : "+car_id_);
            km_b.setText("Km Begin : " + km_b_);
            initdate();
        }
        catch (Exception ex) {
            Log.e("MyApp", "error" + ex.getMessage());
        }
    }

    void initdate ()
    {

        String format = "dd/MM/yy H:mm:ss";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
        java.util.Date date = new java.util.Date();
        date_b_ =formater.format( date );
        date_b.setText( "Date begin : " + date_b_);
    }

    void init_button()
    {
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        add_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_reservation();
            }
        });
    }
    private void init_reservation() {
        final Reservation reservation = new Reservation();

        Random randomGenerator = new Random();
        reservation.setReservation_Number(db.getIdUser() + randomGenerator.nextInt(111));
        reservation.setId_client(db.getIdUser());

       reservation.setStatus(true);

        String idCar = getIntent().getExtras().getString("id_car");
        reservation.setCars_number(Integer.parseInt(idCar));

        initdate();
        reservation.setDateBegining(date_b_);
     //   reservation.setDateEnd("");
        reservation.setKmBegin(Float.valueOf(km_b_.toString()));
      //  reservation.setBills(0);
      //  reservation.setBills(0);

        final ContentValues contentvalue = AcademyConst.ReservationToContentValues(reservation);
        progressBar.setVisibility(View.VISIBLE);
        new  AsyncTask<Void,Void,Void>()
        {
            @Override
            protected Void doInBackground(Void... voids) {
                FactoryDBManager.getInstance().addReservation(contentvalue);
                FactoryDBManager.getInstance().initAllList();
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                toast(reservation.getReservation_Number());
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish();
            }
        }.execute();
    }

    public void toast(int id)
    {
        Toast.makeText(this,"Reservation : "+ id+" add Successfully" ,LENGTH_LONG).show();
    }
    public void toast_()
    {
        Toast.makeText(this,"wait" ,LENGTH_LONG).show();
    }
    public void notif_send()
    {
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_drive_eta_black_24dp)
                .setContentTitle("Reservation Car id : " + getIntent().getExtras().getString("id_car"))
                .setContentText("la reservation a etait ajouter");
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,mBuilder.build());
    }



}

