package com.example.samuel.android5778_6244_8742_03.Activity.ExpandableListe;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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

import com.example.samuel.android5778_6244_8742_03.Models.BackEnd.FactoryDBManager;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Branch;
import com.example.samuel.android5778_6244_8742_03.Models.entites.Car;
import com.example.samuel.android5778_6244_8742_03.Models.entites.UrlCarImage;
import com.example.samuel.android5778_6244_8742_03.R;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by nathanmsika on 07/12/2017.
 */

public class ExpandableListeAdapter_Branch extends BaseExpandableListAdapter {


    public Context context;
    public List<Car> listcar ;
    public List<Branch> listbranch ;
    public List<UrlCarImage> urlCarImages= FactoryDBManager.getInstance().Allurl();

    public ExpandableListeAdapter_Branch(Context context, List<Branch> listbranch, List<Car> listcarperbranch) {
        this.context = context;
        this.listbranch = listbranch;
        this.listcar = listcarperbranch;
    }

    @Override
    public int getGroupCount() {
        return listbranch.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return listbranch.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listcar.get(1);
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
    public View getGroupView(final int i, boolean b, View convertView, ViewGroup viewGroup) {
        Branch branches = (Branch) getGroup(i);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.branch_list_nd, null);
        }

        TextView product_parkplace = (TextView) convertView.findViewById(R.id.id_parkplace__); //int

        TextView product_branchnum = (TextView) convertView.findViewById(R.id.id_branchnumber__); //int


        TextView product_city = (TextView) convertView.findViewById(R.id.id_addresscity); //string

        TextView product_street = (TextView) convertView.findViewById(R.id.id_addressstreet); //string

        TextView product_number = (TextView) convertView.findViewById(R.id.id_addressnumber); //string

        Button button = (Button) convertView.findViewById(R.id.button_go);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 init_maps(i);
            }
        });




        product_parkplace.setText(Integer.toString(branches.getParkplace()));

        product_branchnum.setText(Integer.toString(branches.getBranchNumber()));

        product_city.setText(branches.getAddress_().getCity());

        product_street.setText(branches.getAddress_().getStreet());

        product_number.setText(branches.getAddress_().getPhoneNumber());


        return convertView;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        Car car = (Car) getChild(i,i1);

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
                ImageDownloaderTask class_image = (ImageDownloaderTask) new ImageDownloaderTask(image_)
                        .execute(url_image);
            }


            product_branch.setText(Integer.toString(car.getBranchParkNumber()));

            product_km.setText(Float.toString(car.getKm()));

            product_carnum.setText(car.getCarNumber());

            product_class.setText(car.get_class());

            return view;

    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }


    /*
    @Override
    public int getGroupCount() {
        return listbranch.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listcar.size();
    }

    @Override
    public Object getGroup(int i) {
        return listbranch.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        if (listcar.get(i1).getBranchParkNumber() == listbranch.get(i).getBranchNumber())
            return listcar.get(i1);
        return null;
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
    public View getChildView(int i , int i1, boolean b, View view, ViewGroup viewGroup) {

        Car car = (Car) getChild(i,i1);

        if(car !=null) {
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
                ExpandableListeAdapter_Car.ImageDownloaderTask class_image = (ExpandableListeAdapter_Car.ImageDownloaderTask) new ImageDownloaderTask(image_)
                        .execute(url_image);
            }


            product_branch.setText(Integer.toString(car.getBranchParkNumber()));

            product_km.setText(Float.toString(car.getKm()));

            product_carnum.setText(car.getCarNumber());

            product_class.setText(car.get_class());

            return view;
        }
        else
        return null;
    }

    @Override
    public View getGroupView(int j, boolean b, View convertView, ViewGroup viewGroup) {

        final int i =j;
        Branch branches = (Branch) getGroup(i);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.branch_list, null);
        }

        TextView product_parkplace = (TextView) convertView.findViewById(R.id.id_parkplace__); //int

        TextView product_branchnum = (TextView) convertView.findViewById(R.id.id_branchnumber__); //int


        TextView product_city = (TextView) convertView.findViewById(R.id.id_addresscity); //string

        TextView product_street = (TextView) convertView.findViewById(R.id.id_addressstreet); //string

        TextView product_number = (TextView) convertView.findViewById(R.id.id_addressnumber); //string

        Button button = (Button) convertView.findViewById(R.id.button_go);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_maps(i);
            }
        });
        Button button_check = (Button) convertView.findViewById(R.id.button_toreserv);

       /// delete the button check for  this list
           button_check.setEnabled(false);
           button_check.setTextColor(Color.WHITE);
           button_check.setBackgroundColor(Color.WHITE);
        ////


        product_parkplace.setText(Integer.toString(branches.getParkplace()));

        product_branchnum.setText(Integer.toString(branches.getBranchNumber()));

        product_city.setText(branches.getAddress_().getCity());

        product_street.setText(branches.getAddress_().getStreet());

        product_number.setText(branches.getAddress_().getPhoneNumber());


        return convertView;

    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }




*/
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


    public void init_maps(final int i) {

        Branch b =FactoryDBManager.getInstance().branchByFreeCarsofModels(listcar.get(i).getBranchParkNumber());

        try {
            Uri uri = Uri.parse("geo:0,0?q=" + b.getAddress_().getCity() + " " + b.getAddress_().getStreet() +
                    " " + b.getAddress_().getPhoneNumber());
            Intent it = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(it);
        } catch (ActivityNotFoundException e) {
            Log.e("MyApp", e.getMessage() + "problem avec google map");
        }

    }
}


