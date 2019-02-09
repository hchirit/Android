package com.example.samuel.android5778_6244_8742_03.Activity.Menu_fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.example.samuel.android5778_6244_8742_03.R;

public class Menu_button extends AppCompatActivity {
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    loadFragment(new SecondFragment(context));
                    return true;
                case R.id.navigation_dashboard:
                    loadFragment(new FirstFragment(context));
                    return true;
                case R.id.navigation_notifications:
                    loadFragment(new ThirdFragment(context));
                    return true;
            }
            return false;
        }

    };


    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_button);

        context = this;


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }


    private void loadFragment(Fragment fragment) {
  // create a FragmentManager
        FragmentManager fm = getFragmentManager();
  // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
  // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit(); // save the changes
    }
}
