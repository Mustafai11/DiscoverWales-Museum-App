package com.example.musem;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musem.adapter.ItineraryAdapter;
import com.example.musem.database.DatabaseHelper;
import com.example.musem.databinding.ActivityItineraryDetailsBinding;
import com.example.musem.model.Itinerary;

import java.util.ArrayList;

public class ItineraryDetailsActivity extends AppCompatActivity {

    ActivityItineraryDetailsBinding detailsBinding;
    private ItineraryAdapter itineraryAdapter;
    private ArrayList<Itinerary> itineraries;
    private DatabaseHelper databaseHelper; // Database helper to fetch itineraries

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailsBinding = ActivityItineraryDetailsBinding.inflate(getLayoutInflater());
        setContentView(detailsBinding.getRoot());

        // Initialize the DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        detailsBinding.back.setOnClickListener(view -> {
            getOnBackPressedDispatcher().onBackPressed();
        });
        // Setup RecyclerView
        RecyclerView recyclerView = detailsBinding.itRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the list and the adapter
        itineraries = new ArrayList<>();
        itineraryAdapter = new ItineraryAdapter(itineraries);
        recyclerView.setAdapter(itineraryAdapter);

        // Fetch and load itineraries from the database
        loadItineraries();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadItineraries() {
        // Query all itineraries from the database
        Cursor cursor = databaseHelper.getAllItineraries();

        // Check if cursor has data
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Retrieve the data from the cursor
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)); // Use the column name
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)); // Use the column name
                @SuppressLint("Range") String location = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LOCATION)); // Use the column name
                @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TIME)); // Use the column name
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DATE)); // Use the column name
                @SuppressLint("Range") int imageResource = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_IMAGE)); // Use the column name

                // Create a new Itinerary object

                Itinerary itinerary = new Itinerary(id, name, location, time, date, imageResource);

                // Add the itinerary to the list
                itineraries.add(itinerary);
            } while (cursor.moveToNext());

            // Notify the adapter that data has changed
            itineraryAdapter.notifyDataSetChanged();
        }

        // Close the cursor after processing
        if (cursor != null) {
            cursor.close();
        }
    }
}
