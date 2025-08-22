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

import adapters.AndoidVersionAdapter;
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

        ArrayList<AndroidVersion> androidVersionList = createData();

        final RecyclerView rvAndroidVersions = mainBinding.rvAndroidVersions;

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
   //   RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        rvAndroidVersions.setLayoutManager(layoutManager);

        AndoidVersionAdapter andoidVersionAdapter = new AndoidVersionAdapter(this, androidVersionList);
        rvAndroidVersions.setAdapter(andoidVersionAdapter);

        mainBinding.btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < androidVersionList.size(); i++) {
                    String name1 = androidVersionList.get(i).getCodeName();
                    for (int j = 0; j < i; j++) {
                        String name2 = androidVersionList.get(j).getCodeName();
                        if (name1.compareToIgnoreCase(name2) < 0) {
                            // swap the locations
                            AndroidVersion tempObject = androidVersionList.get(j);
                            androidVersionList.set(j, androidVersionList.get(i));
                            androidVersionList.set(i, tempObject);
                        }
                    }
                }
                andoidVersionAdapter.notifyDataSetChanged();
            }
        });

    }

    private ArrayList<AndroidVersion> createData (){

        ArrayList<AndroidVersion> androidVersionList =new ArrayList<>();

        AndroidVersion androidVersion3 = new AndroidVersion();
        androidVersion3.setImageResId(R.drawable.ic_froyo);
        androidVersion3.setCodeName("Froyo");
        androidVersion3.setVersion("2.2 – 2.2.3");
        androidVersionList.add(androidVersion3);

        AndroidVersion androidVersion1 = new AndroidVersion(R.drawable.ic_donut, "Donut", "1.6");
        androidVersionList.add(androidVersion1);

        androidVersionList.add(new AndroidVersion(R.drawable.ic_eclair, "Eclair", "2.0 – 2.1"));

        androidVersionList.add(new AndroidVersion(R.drawable.ic_cream_sandwich, "Ice Cream Sandwich", "4.0 – 4.0.4"));

        androidVersionList.add(new AndroidVersion(R.drawable.ic_honeycomb, "Honeycomb", "3.0 – 3.2.6"));
        androidVersionList.add(new AndroidVersion(R.drawable.ic_jellybean, "Jelly Bean", "4.1 – 4.3.1"));
        androidVersionList.add(new AndroidVersion(R.drawable.ic_kitkat, "KitKat", "4.4 – 4.4.4"));
        androidVersionList.add(new AndroidVersion(R.drawable.ic_lollipop, "Lollipop", "5.0 – 5.1.1"));
        androidVersionList.add(new AndroidVersion(R.drawable.ic_gingerbread, "Gingerbread", "2.3 – 2.3.7"));

        androidVersionList.add(new AndroidVersion(R.drawable.ic_nougat, "Nougat", "7.0 – 7.1.2"));
        androidVersionList.add(new AndroidVersion(R.drawable.ic_oreo, "Oreo", "8.0 – 8.1"));

        androidVersionList.add(new AndroidVersion(R.drawable.ic_marshmallow, "Marshmallow", "6.0 – 6.0.1"));


        return androidVersionList;

    }


}