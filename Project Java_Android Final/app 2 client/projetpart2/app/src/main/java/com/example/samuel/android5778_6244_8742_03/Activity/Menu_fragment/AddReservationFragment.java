package com.example.samuel.android5778_6244_8742_03.Activity.Menu_fragment;

import android.app.Fragment;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.AcademyConst;
import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.DBManager;
import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.FactoryDBManager;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Reservation;
import com.example.samuel.android5778_6244_8742_03.R;

import java.util.Random;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by nathanmsika on 11/01/2018.
 */

public class AddReservationFragment extends Fragment {
    public final String TAG ="MyApp";
    Context MyContext;
    View view;
    TextView r_id , client_id  ,car_id,date_b,km_b ;
    EditText date_e;
    String  r_id_ , client_id_  ,car_id_,date_b_,km_b_ ;
    DBManager db = FactoryDBManager.getInstance();
    Button btnClick , add_r;
    CheckBox checkBox;
    ProgressBar progressBar;
    public AddReservationFragment(Context myContext,String car,String km) {
        MyContext = myContext;
        car_id_= car;
        km_b_ = km;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_reservation, container, false);

        try {
            add_r = (Button) view.findViewById(R.id.button3);
            init_textview();
            init_button();
        }
        catch (Exception ex)
        {
            Log.e(TAG,"error trouver: " +getClass().getName()+" "+ ex.getMessage()  );
        }
        return view;
    }
    private void init_textview() {
        try {
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar_add);
            progressBar.setVisibility(View.INVISIBLE);
            // checkBox = (CheckBox) findViewById(R.id.checkBox_statut);
            r_id = (TextView) view.findViewById(R.id.editText_reservation_id);
            client_id = (TextView) view.findViewById(R.id.editText_client_id);
            car_id = (TextView) view.findViewById(R.id.editText_car_id);
            date_b = (TextView) view.findViewById(R.id.editText_date_begin);
            km_b = (TextView) view.findViewById(R.id.editText_km_begin);

            client_id_= Integer.toString(db.getIdUser());
            r_id_ =  Integer.toString(db.getIdUser() + 128);

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

        reservation.setCars_number(Integer.parseInt(car_id_));

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

            }
        }.execute();
    }

    public void toast(int id)
    {
        Toast.makeText(MyContext,"Reservation : "+ id+" add Successfully" ,LENGTH_LONG).show();
    }
    public void toast_()
    {
        Toast.makeText(MyContext,"wait" ,LENGTH_LONG).show();
    }
    public void notif_send()
    {
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(MyContext)
                        .setSmallIcon(R.drawable.ic_drive_eta_black_24dp)
                        .setContentTitle(car_id_)
                        .setContentText("la reservation a etait ajouter");
        NotificationManager notificationManager = (NotificationManager) MyContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,mBuilder.build());
    }




}
