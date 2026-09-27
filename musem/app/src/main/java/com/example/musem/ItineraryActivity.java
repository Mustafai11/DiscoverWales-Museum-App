package com.example.musem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.musem.database.DatabaseHelper;
import com.example.musem.databinding.ActivityItineraryBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class ItineraryActivity extends AppCompatActivity {

    public ActivityItineraryBinding itineraryBinding;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        itineraryBinding = ActivityItineraryBinding.inflate(getLayoutInflater());
        View view = itineraryBinding.getRoot();
        setContentView(view);
        dbHelper = new DatabaseHelper(this);

        // Handle window insets for padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        itineraryBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        int image = intent.getIntExtra("image", 0);
        String location = intent.getStringExtra("loc");

        itineraryBinding.nameValueTextView.setText(title);
        itineraryBinding.imageView.setImageResource(image);
        itineraryBinding.locationValueTextView.setText(location);


        itineraryBinding.addNewButton.setOnClickListener(view1 -> {

            Intent intent1 = new Intent(getApplicationContext(), ExploreActivity.class);
            startActivity(intent1);

        });


        itineraryBinding.saveButton.setOnClickListener(view2 -> {
            String name = itineraryBinding.nameValueTextView.getText().toString();
            String loc = itineraryBinding.locationValueTextView.getText().toString();
            String time = Objects.requireNonNull(itineraryBinding.timeTextView.getText()).toString();
            String date = Objects.requireNonNull(itineraryBinding.dateTextView.getText()).toString();

            if (!name.isEmpty() && !loc.isEmpty() && !time.isEmpty() && !date.isEmpty()) {
                // Insert the data into the SQLite database
                long result = dbHelper.addItinerary(name, loc, time, date, image);

                if (result != -1) {
                    Toast.makeText(ItineraryActivity.this, "Itinerary saved!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ItineraryActivity.this, "Failed to save itinerary.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ItineraryActivity.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            }
        });


    }


    // Method to open the Date Picker
    public void openDatePicker(View view) {

        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
                .build();
        datePicker.addOnPositiveButtonClickListener(selection -> {

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
            itineraryBinding.dateTextView.setText(sdf.format(new Date(selection)));
        });
        datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
    }

    // Method to open the Time Picker
    public void openTimePicker(View view) {

        MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)  // Use 12-hour format for AM/PM
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Time")
                .build();

        timePicker.addOnPositiveButtonClickListener(v -> {
            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();

            String time = String.format(Locale.getDefault(), "%02d:%02d %s",
                    (hour == 0 || hour == 12) ? 12 : hour % 12, // 12-hour format handling
                    minute,
                    (hour < 12) ? "AM" : "PM");  // AM/PM suffix

            itineraryBinding.timeTextView.setText(time);
        });

        timePicker.show(getSupportFragmentManager(), "TIME_PICKER");
    }

}
