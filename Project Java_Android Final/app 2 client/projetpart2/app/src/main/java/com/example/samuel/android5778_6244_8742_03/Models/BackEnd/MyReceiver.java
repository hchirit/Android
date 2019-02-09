package com.example.samuel.android5778_6244_8742_03.Models.BackEnd;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by nathanmsika on 06/12/2017.
 */

public class MyReceiver extends BroadcastReceiver {

    public MyReceiver() {
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Log.d("my service" , "onReceive");
        try
        {
            ComponentName componentName = new ComponentName
                    (
                            "com.example.samuel.android5778_6244_8742_03",
                            "com.example.samuel.android5778_6244_8742_03.Models.BackEnd.Myservice"
                    );

            Intent intent_2 = new Intent();
            intent_2.setComponent(componentName);
            context.startService(intent_2);
        }
        catch (Exception ex) {
            Log.e("my service","erreur trouver ::" + ex.getMessage());
        }

      //  Toast.makeText(context,intent.getAction(),Toast.LENGTH_LONG).show();


        //    throw new UnsupportedOperationException("Not yet implemented");
    }




}
