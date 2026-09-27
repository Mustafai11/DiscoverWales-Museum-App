package com.example.musem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.musem.databinding.ActivityHomeBinding;
import com.example.musem.databinding.ActivityMainBinding;

public class HomeActivity extends AppCompatActivity {


    public ActivityHomeBinding homeBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        homeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = homeBinding.getRoot();
        setContentView(view);



        homeBinding.map.setOnClickListener(view1 -> {
            Intent intent = new Intent(getApplicationContext(), MapActivity.class);
            startActivity(intent);

        });


        homeBinding.explore.setOnClickListener(view1 -> {
            Intent intent = new Intent(getApplicationContext(), ExploreActivity.class);
            startActivity(intent);

        });

        homeBinding.itinerary.setOnClickListener(view1 -> {
            Intent intent = new Intent(getApplicationContext(), ItineraryDetailsActivity.class);
            startActivity(intent);

        });



    }
}