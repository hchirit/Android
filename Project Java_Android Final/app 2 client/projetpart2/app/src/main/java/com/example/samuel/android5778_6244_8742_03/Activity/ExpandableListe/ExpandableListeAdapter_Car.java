package com.example.samuel.android5778_6244_8742_03.Activity.ExpandableListe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samuel.android5778_6244_8742_03.Activity.ImageSaver;
import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.AcademyConst;
import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.DBManager;
import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.FactoryDBManager;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Branch;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Car;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Reservation;
import com.example.samuel.android5778_6244_8742_03.Models.entites.UrlCarImage;
import com.example.samuel.android5778_6244_8742_03.R;

import java.util.List;
import java.util.Random;


/**
 * Created by nathanmsika on 06/12/2017.
 */

public class ExpandableListeAdapter_Car extends BaseExpandableListAdapter {


    public Context context;
    public List<Car> listcarperbranch;
    public List<Branch> listbranch;
    public List<UrlCarImage> urlCarImages;
    DBManager db;
    volatile boolean someBoolean;

    public ExpandableListeAdapter_Car(Context context, List<Car> listcarperbranch, List<Branch> listbranch) {
        this.context = context;
        this.listcarperbranch = listcarperbranch;
        this.listbranch = listbranch;
        this.urlCarImages = FactoryDBManager.getInstance().Allurl();
        db = FactoryDBManager.getInstance();
    }

    @Override
    public int getGroupCount() {
        return listcarperbranch.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return listcarperbranch.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return FactoryDBManager.getInstance().branchByFreeCarsofModels(listcarperbranch.get(i).getBranchParkNumber());
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        Car car = (Car) getGroup(i);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.car_listview, null);
        }

        TextView product_branch = (TextView) view.findViewById(R.id.id_branchparknum); //int

        TextView product_km = (TextView) view.findViewById(R.id.id_km_); //float

        TextView product_carnum = (TextView) view.findViewById(R.id.id_carnumber_);

        TextView product_class = (TextView) view.findViewById(R.id.id_class_);

        ImageView image_ = (ImageView) view.findViewById(R.id.imageView_car);

        String url_image = FactoryDBManager.getInstance().get_url_image(car.getCarNumber());
        if (url_image != null) {
            Bitmap bitmap = new ImageSaver(context).
                    setFileName(car.getCarNumber()).
                    setDirectoryName("images").
                    load();
            image_.setImageBitmap(bitmap);
        }

        product_branch.setText(Integer.toString(car.getBranchParkNumber()));

        product_km.setText(Float.toString(car.getKm()));

        product_carnum.setText(car.getCarNumber());

        product_class.setText(car.get_class());

        return view;
    }

    @Override
    public View getChildView(int j, int i1, boolean b, View convertView, ViewGroup viewGroup) {

        final int i =j;
        Branch branches = (Branch) getChild(i, i1);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.branch_list, null);
        }

        //     TextView product_address= (TextView) convertView.findViewById(R.id.id_address__); //string

        TextView product_parkplace = (TextView) convertView.findViewById(R.id.id_parkplace__); //int

        TextView product_branchnum = (TextView) convertView.findViewById(R.id.id_branchnumber__); //int


        TextView product_city = (TextView) convertView.findViewById(R.id.id_addresscity); //string

        TextView product_street = (TextView) convertView.findViewById(R.id.id_addressstreet); //string

        TextView product_number = (TextView) convertView.findViewById(R.id.id_addressnumber); //string

        Button button_go = (Button) convertView.findViewById(R.id.button_go);
        Button button_check = (Button) convertView.findViewById(R.id.button_toreserv);


        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_maps(i);
            }
        });
        button_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_toreserv(i);
            }
        });


        product_parkplace.setText(Integer.toString(branches.getParkplace()));

        product_branchnum.setText(Integer.toString(branches.getBranchNumber()));

        product_city.setText(branches.getAddress_().getCity());

        product_street.setText(branches.getAddress_().getStreet());

        product_number.setText(branches.getAddress_().getPhoneNumber());


        return convertView;

    }

    public void init_toreserv(int i) {

        final Car car = listcarperbranch.get(i);
       // Intent intent = new Intent(context, Activity_AddReservation.class);
       // intent.putExtra("id_car",car.getCarNumber().toString());
       // intent.putExtra("km_car",Float.toString(car.getKm()));
      //  context.startActivity(intent);


        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("Confirm Reservation");
        // Icon Of Alert Dialog
        alertDialogBuilder.setIcon(R.drawable.ic_check_black_24dp);
        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage("Are you sure,You want to reserve this car");
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(context,"Wait a few minutes ...",Toast.LENGTH_LONG).show();
                    try {
                        someBoolean = false;
                        add_reservation(car.getCarNumber().toString(), Float.toString(car.getKm()));
                        Thread someThread = new Thread() {

                            @Override
                            public void run() {
                                //some actions
                                while (!someBoolean) ; //wait for condition
                                //some actions
                            }

                        };
                        arg0.cancel();
                    } catch (Exception ex) {
                        Toast.makeText(context, "Reservation problem ...", Toast.LENGTH_SHORT).show();
                    }
                }

        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context,"Reservation canceled ...",Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(300);
                    dialog.cancel();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }


    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }




    public void init_maps(final int i) {

        final Branch b =FactoryDBManager.getInstance().branchByFreeCarsofModels(listcarperbranch.get(i).getBranchParkNumber());

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("Confirm map");
        // Icon Of Alert Dialog
        alertDialogBuilder.setIcon(R.drawable.ic_check_black_24dp);
        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage("Are you sure,You want to go map");
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(context,"Wait a few minutes ...",Toast.LENGTH_LONG).show();
                try {
                    Uri uri = Uri.parse("google.navigation:q=" + b.getAddress_().getCity() + " " + b.getAddress_().getStreet() +
                            " " + b.getAddress_().getPhoneNumber());
                    Intent it = new Intent(Intent.ACTION_VIEW, uri);
                    context.startActivity(it);
                } catch (ActivityNotFoundException e) {
                    Log.e("MyApp", e.getMessage() + "problem avec google map");
                    Toast.makeText(context,"problem avec google map",Toast.LENGTH_LONG).show();
                }
            }

        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context,"canceled ...",Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(300);
                    dialog.cancel();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }

    public void add_reservation(String car_num , String km_b){

        final Reservation reservation = new Reservation();
        Random randomGenerator = new Random();
        reservation.setReservation_Number(db.getIdUser() + randomGenerator.nextInt(111));
        reservation.setId_client(db.getIdUser());

        reservation.setStatus(true);

        String idCar = car_num;
        reservation.setCars_number(Integer.parseInt(idCar));

        // initdate();
        String format = "dd/MM/yy H:mm:ss";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
        java.util.Date date = new java.util.Date();
        reservation.setDateBegining(formater.format( date ));
        //   reservation.setDateEnd("");
        reservation.setKmBegin(Float.parseFloat(km_b));
        //  reservation.setBills(0);
        //  reservation.setBills(0);

        final ContentValues contentvalue = AcademyConst.ReservationToContentValues(reservation);
        // progressBar.setVisibility(View.VISIBLE);
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
                //  toast(reservation.getReservation_Number());
                try {
                    Thread.sleep(100);
                    someBoolean = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //    finish();
            }
        }.execute();

    }



}

