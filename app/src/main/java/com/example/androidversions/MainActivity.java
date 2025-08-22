package com.example.androidversions;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidversions.databinding.ActivityMainBinding;

import java.util.ArrayList;

import adapters.AndroidVersionAdapter;
import models.AndroidVersion;

public class MainActivity extends AppCompatActivity {
    public static ActivityMainBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Prepare the data (the list of Android versions)
        ArrayList<AndroidVersion> androidVersionList = createData();

        //Reference to RecyclerView from the layout
        final RecyclerView rvAndroidVersions = mainBinding.rvAndroidVersions;

        //Set RecyclerView layout manager (vertical scrolling list)
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        rvAndroidVersions.setLayoutManager(layoutManager);

        //Create adapter and attach it to RecyclerView
        AndroidVersionAdapter androidVersionAdapter = new AndroidVersionAdapter(this, androidVersionList);
        rvAndroidVersions.setAdapter(androidVersionAdapter);

        mainBinding.btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < androidVersionList.size(); i++) {
                    String name1 = androidVersionList.get(i).getCodeName();
                    for (int j = 0; j < i; j++) {
                        String name2 = androidVersionList.get(j).getCodeName();
                        if (name1.compareToIgnoreCase(name2) < 0) {
                            // Swap elements if they are out of order
                            AndroidVersion tempObject = androidVersionList.get(j);
                            androidVersionList.set(j, androidVersionList.get(i));
                            androidVersionList.set(i, tempObject);
                        }
                    }
                }
                //Notify adapter that data has changed
                androidVersionAdapter.notifyDataSetChanged();
            }
        });

    }

    //Method to create a list of Android versions with images, code names, and version numbers
    private ArrayList<AndroidVersion> createData (){

        ArrayList<AndroidVersion> androidVersionList =new ArrayList<>();

        AndroidVersion androidVersion3 = new AndroidVersion();
        androidVersion3.setImageResId(R.drawable.ic_froyo);
        androidVersion3.setCodeName(getString(R.string.froyo));
        androidVersion3.setVersion(getString(R.string.froyo_version));
        androidVersionList.add(androidVersion3);

        AndroidVersion androidVersion1 = new AndroidVersion(R.drawable.ic_donut, getString(R.string.donut), getString(R.string.donut_version));
        androidVersionList.add(androidVersion1);

        androidVersionList.add(new AndroidVersion(R.drawable.ic_eclair, getString(R.string.eclair), getString(R.string.eclair_version)));

        androidVersionList.add(new AndroidVersion(R.drawable.ic_cream_sandwich, getString(R.string.ice_cream_sandwich), getString(R.string.ice_cream_version)));

        androidVersionList.add(new AndroidVersion(R.drawable.ic_honeycomb, getString(R.string.honeycomb), getString(R.string.honeycomb_version)));
        androidVersionList.add(new AndroidVersion(R.drawable.ic_jellybean, getString(R.string.jelly_bean), getString(R.string.jelly_bean_version)));
        androidVersionList.add(new AndroidVersion(R.drawable.ic_kitkat, getString(R.string.kitkat), getString(R.string.kitkat_version)));
        androidVersionList.add(new AndroidVersion(R.drawable.ic_lollipop, getString(R.string.lollipop), getString(R.string.lollipop_version)));
        androidVersionList.add(new AndroidVersion(R.drawable.ic_gingerbread, getString(R.string.gingerbread), getString(R.string.gingerbread_version)));

        androidVersionList.add(new AndroidVersion(R.drawable.ic_nougat, getString(R.string.nougat), getString(R.string.nougat_version)));
        androidVersionList.add(new AndroidVersion(R.drawable.ic_oreo, getString(R.string.oreo), getString(R.string.oreo_version)));

        androidVersionList.add(new AndroidVersion(R.drawable.ic_marshmallow, getString(R.string.marshmallow), getString(R.string.marshmallow_version)));


        return androidVersionList;

    }


}