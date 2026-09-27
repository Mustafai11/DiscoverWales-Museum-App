package com.example.musem;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musem.adapter.MuseumAdapter;
import com.example.musem.databinding.ActivityExploreBinding;
import com.example.musem.model.Museum;

import java.util.ArrayList;

public class ExploreActivity extends AppCompatActivity {

    ActivityExploreBinding exploreBinding;
    public RecyclerView recyclerView;
    public MuseumAdapter museumAdapter;
    public ArrayList<Museum> museumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        exploreBinding = ActivityExploreBinding.inflate(getLayoutInflater());
        setContentView(exploreBinding.getRoot());

        exploreBinding.back.setOnClickListener(view -> {
            getOnBackPressedDispatcher().onBackPressed();

        });

        recyclerView = exploreBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create and populate the list of museums
        museumList = new ArrayList<>();
        museumList=Util.populateMuseumList();

        museumAdapter = new MuseumAdapter(museumList);
        recyclerView.setAdapter(museumAdapter);
    }


}
