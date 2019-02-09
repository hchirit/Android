package com.example.samuel.android5778_6244_8742_03.Activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.samuel.android5778_6244_8742_03.Activity.ExpandableListe.ExpandableListeAdapter_Car;
import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.DBManager;
import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.FactoryDBManager;
import com.example.samuel.android5778_6244_8742_03.Models.DataSource.MySQL_DBManager;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Branch;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Car;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Client;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Models;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Reservation;
import com.example.samuel.android5778_6244_8742_03.Models.entites.UrlCarImage;
import com.example.samuel.android5778_6244_8742_03.R;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static android.support.v7.appcompat.R.id.info;

/**
 * Created by nathanmsika on 09/11/2017.
 */

public class SplashScreen extends Activity{

    final String  LOG = "MyApp";
    DBManager db ;
    ImageView image_;
    List<UrlCarImage> url_;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);
        Log.d(LOG,"page de demmarage");

        db= FactoryDBManager.getInstance();

        try {
            if(haveInternetConnection()) {
                init_all_list();
                runService();

            }
            else
            {
                not_connection();
            }
        }catch (Exception ex)
        {
            Log.e(LOG,"error init list :: " + ex.getMessage());
        }

    }

    private void not_connection() throws InterruptedException {
        Toast.makeText(this, "your not connected to internet check your connection", Toast.LENGTH_LONG).show();
        Thread.sleep(2000);
        finish();
    }


    private void init_all_list() {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                db.allcars();
                db.allClient();
                db.allBranchs();
                db.allModels();
                db.allreservation();
                db.allurl();
                image_storage(); // load image from internet if not downlaod befor
                return null;
            }


            @Override
            protected void onPostExecute(Void aVoid) {
                next_activity();
            }
        }.execute();
    }


    private void next_activity()
    {
        Intent i = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void runService() {
        //Intent intent = new Intent(this, MyService.class);
        //  startService(intent);

        ComponentName componentName = new ComponentName
                (
                        "com.example.samuel.android5778_6244_8742_03",
                        "com.example.samuel.android5778_6244_8742_03.Models.BackEnd.Myservice"
                );

        Intent intent_2 = new Intent();
        intent_2.setComponent(componentName);
        startService(new Intent(intent_2));

        //IllegalArgumentException --->  Service Intent must be explicit
        //   startService(new Intent("com.oshri.academy.SERVICE_UPDATE"));
    }


    private boolean haveInternetConnection(){
        // Fonction haveInternetConnection : return true si connecté, return false dans le cas contraire
        NetworkInfo network = ((ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (network==null || !network.isConnected())
        return false;
        else
            return true;

    }

    public void image_storage()
    {
        Log.e("MyApp","1");
        image_ = null;
        url_= FactoryDBManager.getInstance().Allurl();
        for(UrlCarImage item : url_)
        {
            if(null == new ImageSaver(this).setFileName(Integer.toString(item.getId_car())).
                    setDirectoryName("images").load() )
            {
                ImageDownloaderTask class_image = (ImageDownloaderTask) new ImageDownloaderTask(image_)
                        .execute(item.getUrl(), Integer.toString(item.getId_car()));
            }
        }
    }

    public void load_image(){
        try{
        ImageView image_c = (ImageView)findViewById(R.id.imageView_car);

        Bitmap bitmap = new ImageSaver(this).
                setFileName("1").
                setDirectoryName("images").
                load();
        image_c.setImageBitmap(bitmap);
        }
        catch (Exception ex)
        {
            Log.e(LOG,"load image error :: " + ex.getMessage());
        }
    }

    public void store_image(Bitmap bitmap ,String id){
        Log.e("MyApp","image id : "+id);
        new ImageSaver(this).
                setFileName(id).
                setDirectoryName("images").
                save(bitmap);
        Log.e("MyApp","finit : "+ id);
    }


   class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {

        private final WeakReference<ImageView> imageViewReference;
       String id;

        public ImageDownloaderTask(ImageView imageView) {
            imageViewReference = new WeakReference<ImageView>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            id = params[1];
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
            store_image(bitmap,id);
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
