package com.example.musem;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musem.adapter.CustomInfoWindowAdapter;
import com.example.musem.model.CustomInfoWindowData;
import com.example.musem.model.Museum;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Objects;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ImageView back =findViewById(R.id.back);


        back.setOnClickListener(view -> {

        getOnBackPressedDispatcher().onBackPressed();

        });

        // Initialize the map fragment and request the map asynchronously
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        // Call the populateMuseumList method from Util to get the list of museums
        List<Museum> museumList = Util.populateMuseumList();



        for (Museum museum : museumList) {
            // Create a LatLng object for the museum's location
            LatLng location = new LatLng(museum.getLatitude(), museum.getLongitude());

            // Create a marker for each museum
            Objects.requireNonNull(googleMap.addMarker(new MarkerOptions()
                    .position(location)
                    .title(museum.getName())
                    .contentDescription(museum.getExhibits())
            )).setTag(new CustomInfoWindowData(museum.getName(), museum.getExhibits(), museum.getImage()));
        }

        // Set custom info window adapter for the map
        googleMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(this));

        // Move the camera to the first marker or set zoom levels
        if (!museumList.isEmpty()) {
            LatLng firstMuseumLocation = new LatLng(museumList.get(0).getLatitude(), museumList.get(0).getLongitude());
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstMuseumLocation, 6)); // Adjust zoom level as needed
        }
    }
}
