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

import com.example.samuel.android5778_6244_8742_03.Activity.ExpandableListe.ExpandableListeAdapter_Branch;
import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.DBManager;
import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.FactoryDBManager;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Branch;
import com.example.samuel.android5778_6244_8742_03.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nathanmsika on 10/01/2018.
 */

public class FirstFragment extends Fragment {
        public final String TAG ="MyApp";
    public ExpandableListView listView;
    ExpandableListeAdapter_Branch listeAdapter;
    DBManager manager ;
    Spinner spinner;
    View view;
    Context MyContext;



    public FirstFragment(Context myContext) {
        MyContext = myContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.carperbranch, container, false);


        try {
            manager = FactoryDBManager.getInstance();
            spinner = (Spinner)view.findViewById(R.id.spinner_city);
            listView = (ExpandableListView) view.findViewById(R.id.id_expandable_2);
            init_spinner();
        }
        catch (Exception ex)
        {
            Log.e(TAG,"error trouver 1 fragment: "+ ex.getMessage()  );
        }
        return view;
    }


    public void init_listview()
    {
        try {
            listeAdapter = new ExpandableListeAdapter_Branch(MyContext,
                    manager.branchbycity(spinner.getSelectedItem().toString()),
                    manager.Allcars());
            listView.setAdapter(listeAdapter);
        }
        catch (Exception ex)
        {
            Log.e(TAG,"error trouver:  f1 " +getClass().getName()+" "+ ex.getMessage()  );
        }
    }

    public void init_spinner()
    {
        List<String> list = new ArrayList<String>();
        for(Branch item : manager.AllBranchs()) {
            if(!list.contains(item.getAddress_().getCity()))
                list.add(item.getAddress_().getCity());
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