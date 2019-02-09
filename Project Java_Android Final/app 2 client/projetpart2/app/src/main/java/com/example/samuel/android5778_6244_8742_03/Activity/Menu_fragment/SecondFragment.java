package com.example.samuel.android5778_6244_8742_03.Activity.Menu_fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import com.example.samuel.android5778_6244_8742_03.Activity.ExpandableListe.ExpandableListeAdapter_Car;
import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.DBManager;
import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.FactoryDBManager;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Car;
import com.example.samuel.android5778_6244_8742_03.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nathanmsika on 10/01/2018.
 */

public class SecondFragment extends Fragment {

    View view;
    public final String TAG ="MyApp";
    public ExpandableListView listView;
    ExpandableListeAdapter_Car listeAdapter;
    DBManager manager ;
    Spinner spinner;
    Context MyContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.branchpremodel, container, false);


        try {
            manager = FactoryDBManager.getInstance();
            spinner = (Spinner)view.findViewById(R.id.spinner);
            listView = (ExpandableListView) view.findViewById(R.id.id_expandable);
            init_spinner();
        }
        catch (Exception ex)
        {
            Log.e(TAG,"error trouver: f2_" +getClass().getName()+" "+ ex.getMessage()  );
        }


        return view;
    }

    public SecondFragment(Context myContext) {
        MyContext = myContext;
    }
    public void init_listview()
    {
        try {
            listeAdapter = new ExpandableListeAdapter_Car(MyContext,
                    manager.freeCarsformodel(spinner.getSelectedItem().toString())
                    ,manager.AllBranchs());
            listView.setAdapter(listeAdapter);
        }
        catch (Exception ex)
        {
            Log.e(TAG,"error trouver: " +getClass().getName()+" "+ ex.getMessage()  );
        }
    }

    public void init_spinner()
    {
        List<String> list = new ArrayList<String>();
        for(Car item : manager.Allcars())
        {
            if(!list.contains(item.get_class()))
                list.add(item.get_class());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MyContext,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                init_listview();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                init_listview();
            }

        });
    }
}