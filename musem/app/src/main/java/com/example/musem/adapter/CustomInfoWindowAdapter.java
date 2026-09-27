package com.example.musem.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musem.R;
import com.example.musem.model.CustomInfoWindowData;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final Context context;

    public CustomInfoWindowAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.custom_window, null);

        TextView title = view.findViewById(R.id.infoWindowTitle);
        TextView desc = view.findViewById(R.id.infoWindowDesc);
        ImageView image = view.findViewById(R.id.infoWindowIv);

        CustomInfoWindowData data = (CustomInfoWindowData) marker.getTag();
        if (data != null) {
            title.setText(data.getTitle());
            desc.setText(data.getDesc());
            if (data.getImage() != 0) {
                image.setImageResource(data.getImage());
            }
        }

        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
