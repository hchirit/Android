package com.example.samuel.android5778_6244_8742_03.Models.BackEnd;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by nathanmsika on 06/12/2017.
 */

public class Myservice extends Service {

    final String TAG = "Myservice";
    static boolean ServiceRun;// = false;
    DBManager db= FactoryDBManager.getInstance();

    static {
        ServiceRun = false;
    }

    public Myservice() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

      //  Toast.makeText(this, "start service", Toast.LENGTH_SHORT).show();
        Thread t = new Thread() {
            @Override
            public void run() {

                while (true) {
                    try {
                        Thread.sleep(10000);
                        Log.d(TAG, "thread run ..");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                   if (FactoryDBManager.getInstance().isUpdatet()) {
                       Log.d(TAG, "isUpdatet run ..");
                       init_all_list();
                   }
                }
            }
        };
        t.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        // TODO: Return the communication channel to the service.
        return null;
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");

        if (!ServiceRun) {
            ServiceRun = true;
           // Toast.makeText(this, "run servicek", Toast.LENGTH_LONG).show();
            return START_STICKY;
        }

       /// Toast.makeText(this, "The service is already running", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    private void init_all_list() {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                db.initAllList();
                return null;
            }
        }.execute();
    }
}