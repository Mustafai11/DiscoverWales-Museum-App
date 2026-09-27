package com.example.musem.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musem.ItineraryActivity;
import com.example.musem.databinding.ItemMuseumBinding;
import com.example.musem.model.Museum;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;


public class MuseumAdapter extends RecyclerView.Adapter<MuseumAdapter.MuseumViewHolder> {

    private final ArrayList<Museum> museumList;

    // Constructor
    public MuseumAdapter(ArrayList<Museum> museumList) {
        this.museumList = museumList;
    }

    // Create new views)
    @NonNull
    @Override
    public MuseumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMuseumBinding binding = ItemMuseumBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MuseumViewHolder(binding);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MuseumViewHolder holder, int position) {
        Museum currentMuseum = museumList.get(position);
        holder.nameTextView.setText(currentMuseum.getName());
        holder.locationTextView.setText(currentMuseum.getLocation());
        holder.foundedTextView.setText("Founded in: " + currentMuseum.getFounded());
        holder.museumExhibits.setText("Exhibits: " + currentMuseum.getExhibits());
        holder.museumImage.setImageResource(currentMuseum.getImage());

        holder.saveBtn.setOnClickListener(view -> {

            Context context = view.getContext();
            Intent intent = new Intent(context, ItineraryActivity.class);
            intent.putExtra("title",currentMuseum.getName());
            intent.putExtra("image",currentMuseum.getImage());
            intent.putExtra("loc",currentMuseum.getLocation());
            context.startActivity(intent);

        });

    }


    @Override
    public int getItemCount() {
        return museumList.size();
    }


    public static class MuseumViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView, locationTextView, foundedTextView,museumExhibits;
        ImageView museumImage;
        MaterialButton saveBtn;

        public MuseumViewHolder(ItemMuseumBinding binding) {
            super(binding.getRoot());
            nameTextView = binding.museumName;
            locationTextView = binding.museumLocation;
            foundedTextView = binding.museumFounded;
            museumImage = binding.museumImage;
            museumExhibits = binding.museumExhibits;
            saveBtn=binding.saveBtn;
        }
    }
}
