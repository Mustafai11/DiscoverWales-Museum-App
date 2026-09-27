package com.example.musem.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musem.ItineraryActivity;
import com.example.musem.database.DatabaseHelper;
import com.example.musem.databinding.ItemItineraryBinding;
import com.example.musem.model.Itinerary;
import com.example.musem.model.Museum;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;


public class ItineraryAdapter extends RecyclerView.Adapter<ItineraryAdapter.ItineraryViewHolder> {

    private final ArrayList<Itinerary> itineraries;

    // Constructor
    public ItineraryAdapter(ArrayList<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    // Create new views)
    @NonNull
    @Override
    public ItineraryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemItineraryBinding binding = ItemItineraryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItineraryViewHolder(binding);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ItineraryViewHolder holder, int position) {
        Itinerary itinerary = itineraries.get(position);
        holder.nameTextView.setText(itinerary.getName());
        holder.locationTextView.setText(itinerary.getLocation());
        holder.museumImage.setImageResource(itinerary.getImageResource());
        holder.itDate.setText(itinerary.getDate());
        holder.itTime.setText(itinerary.getTime());

        holder.deleteIcon.setOnClickListener(view -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {  // Correct the variable to pos
                DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
                int rowsDeleted = databaseHelper.deleteItinerary(itinerary.getId());

                if (rowsDeleted > 0) {
                    itineraries.remove(pos);
                    notifyItemRemoved(pos);

                    // Show a success Toast
                    Toast.makeText(view.getContext(), "Itinerary deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Show a failure Toast
                    Toast.makeText(view.getContext(), "Failed to delete itinerary", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return itineraries.size();
    }


    public static class ItineraryViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView, locationTextView, itDate,itTime;
        ImageView museumImage,deleteIcon,updateIcon;


        public ItineraryViewHolder(ItemItineraryBinding binding) {
            super(binding.getRoot());
            nameTextView = binding.itName;
            locationTextView = binding.itLocation;
            museumImage = binding.itImage;
            itDate = binding.itDate;
            itTime=binding.itTime;
            deleteIcon=binding.deleteIcon;
//            updateIcon=binding.updateIcon;
        }
    }
}
