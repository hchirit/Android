package com.example.samuel.android5778_6244_8742_03.Activity;

import android.content.ActivityNotFoundException;
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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samuel.android5778_6244_8742_03.Activity.ExpandableListe.ExpandableListeAdapter_Car;
import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.DBManager;
import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.FactoryDBManager;
import com.example.samuel.android5778_6244_8742_03.Models.DataSource.MySQL_DBManager;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Branch;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Car;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Client;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Models;
import com.example.samuel.android5778_6244_8742_03.Models.entites.UrlCarImage;
import com.example.samuel.android5778_6244_8742_03.R;

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
    final String  LOG = "MyApp";

    ListView liste = null;
    String list_;
    ImageView image_;
    GridView gridView;
    ImageDownloaderTask class_image;
    TextView tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_list_layout);
        liste = (ListView) findViewById(R.id.ma_listview);
        gridView = (GridView)findViewById(R.id.gridView_car);
        list_ = getIntent().getExtras().getString("Value").toString();

        try{
            init_button();
            ClickMe();
        }
        catch (Exception ex)
        {
            Log.e(TAG,"error trouver: " +getClass().getName()+" "+ ex.getMessage()  );
        }

    }

    public void init_button()
    {
        Button btnClick = (Button) findViewById(R.id.button_return_liste);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void ClickMe() {

        try {
            if(list_.equals("cars")) {
                        initItemByListView_car(FactoryDBManager.getInstance().Allcars());
            }

            else if (list_.equals("branch"))
            {
                        initItemByListView_brach(FactoryDBManager.getInstance().AllBranchs());
            }
            else if (list_.equals("image"))
            initItemByListView_image_car(FactoryDBManager.getInstance().Allcars());

        }
        catch (Exception ex)
        {
            Log.d("MyApp","error: " + ex.getMessage());
        }

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

        Log.e(LOG,"liste car fait");
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
                            R.layout.branch_list_nd,null);
                }

                //     TextView product_address= (TextView) convertView.findViewById(R.id.id_address__); //string

                TextView product_parkplace = (TextView) convertView.findViewById(R.id.id_parkplace__); //int

                TextView product_branchnum = (TextView) convertView.findViewById(R.id.id_branchnumber__); //int


                TextView product_city = (TextView) convertView.findViewById(R.id.id_addresscity); //string

                TextView product_street = (TextView) convertView.findViewById(R.id.id_addressstreet); //string

                TextView product_number = (TextView) convertView.findViewById(R.id.id_addressnumber); //string


                Button button_go = (Button) convertView.findViewById(R.id.button_go);

                final int p_ = position;

                button_go.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        init_maps(p_);
                    }
                });

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

    void initItemByListView_image_car(final List<Car> car)
    {

        ImageView imageView = null;
        ImageView [] data = new ImageView[0];
        int i=0;
        for(UrlCarImage item : FactoryDBManager.getInstance().Allurl())
        {
            Bitmap bitmap = new ImageSaver(this).
                    setFileName(Integer.toString(item.getId_car())).
                    setDirectoryName("images").
                    load();
            imageView.setImageBitmap(bitmap);
            imageView = null;
            data [i] = imageView;
            i++;
        }

        ArrayAdapter<ImageView> adapter1 = new ArrayAdapter<ImageView>(this,android.R.layout.simple_list_item_1,data);
        gridView.setAdapter(adapter1);
    }

    public void init_maps(final int i) {
        Branch b = FactoryDBManager.getInstance().AllBranchs().get(i);
        try {
            Uri uri = Uri.parse("geo:0,0?q=" + b.getAddress_().getCity() + " " + b.getAddress_().getStreet() +
                    " " + b.getAddress_().getPhoneNumber());
            Intent it = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(it);
        } catch (ActivityNotFoundException e) {
            Log.e("MyApp", e.getMessage() + "problem avec google map");
        }

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
