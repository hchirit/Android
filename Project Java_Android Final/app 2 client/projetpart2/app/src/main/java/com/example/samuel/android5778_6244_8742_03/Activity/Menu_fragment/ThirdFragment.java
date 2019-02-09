package com.example.samuel.android5778_6244_8742_03.Activity.Menu_fragment;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.example.samuel.android5778_6244_8742_03.Activity.ImageSaver;
import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.AcademyConst;
import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.DBManager;
import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.FactoryDBManager;
import com.example.samuel.android5778_6244_8742_03.Models.DataSource.MySQL_DBManager;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Car;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Reservation;
import com.example.samuel.android5778_6244_8742_03.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nathanmsika on 11/01/2018.
 */

public class ThirdFragment  extends Fragment {
    public final String LOG ="MyApp";
    View view;
    TextView id,Amount,timeUse,timeend ;
    EditText kilometre ;
    DBManager db ;
    int id_client ;
    Spinner spinner;
    ListView listView;
    List<Car> car_list;
    String date_now;
    int id_car ;
    int amount_;
    ProgressBar progressBar;
    Button pay;
    Context MyContext;


    public ThirdFragment(Context myContext) {
        MyContext = myContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.statut_reservation, container, false);

        try {
            db = FactoryDBManager.getInstance();
            init_textview();
            init_button();
            init_spinner();
        }catch (Exception ex)
        {
            Log.e(LOG,"error :: " + ex.getMessage()+" :: classe"+ this.getClass().toString());
        }


        return view;
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
        List<String> list = new ArrayList<String>();
        try {

            for (Reservation item : db.Allreservation()) {
                if (item.getId_client() == db.getIdUser() && item.isStatus() == true) {
                    list.add(String.valueOf(item.getCars_number()));
                }
            }
        }
        catch (Exception ex) {
            Log.e("MyApp", "error" + ex.getMessage());
        }


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MyContext,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                id_car = Integer.parseInt(spinner.getSelectedItem().toString());
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
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar_statut);
            progressBar.setVisibility(view.INVISIBLE);
            spinner = (Spinner)view.findViewById(R.id.spinner_cars_id);
            Amount = (TextView) view.findViewById(R.id.textView9);
            kilometre = (EditText) view.findViewById(R.id.editText12);
            id_client = db.getIdUser();
            listView = (ListView) view.findViewById(R.id.list_view_reservation);
         //   initdate ();
            //fermer_clavier();
        }
        catch (Exception ex) {
            Log.e("MyApp", "error" + ex.getMessage());
        }
    }




    void initdate ()
    {
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

        amount_ = Integer.parseInt(date_b.substring(1,2)) * 180 ; // 180 shekel for 1 day
        Amount.setText(String.valueOf(amount_));

        timeUse.setText(time_use);
    }

    void init_button()
    {
        pay = (Button) view.findViewById(R.id.button2);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_reservation();

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
            pay.setEnabled(false);
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
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast_end();
                        pay.setEnabled(true);
                        getActivity().finish();
                }
            }.execute();
        }
        else
            Toast.makeText(MyContext, "imput not correct",Toast.LENGTH_LONG).show();

    }




    public void toast()
    {
        Toast.makeText(MyContext,"you doesn't have a resevation id " + id_client,Toast.LENGTH_LONG).show();
    }

    private void Toast_end() {
        Toast.makeText(MyContext,"end reservation successful " + id_client,Toast.LENGTH_LONG).show();
    }

    void initItemByListView_car(final List<Car> car)
    {

        Log.e(LOG,"liste car");
        final List<Car> cars = car;

        ArrayAdapter<Car> adapter = new ArrayAdapter<Car>(MyContext,
                R.layout.car_listview, cars)
        {

            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                if (convertView == null)
                {
                    convertView = View.inflate(MyContext,
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

        if(car!=null) {
            listView.setAdapter(adapter);
            Log.e(LOG, "liste car fait");
            //  this.setContentView(listView);
        }
    }


}