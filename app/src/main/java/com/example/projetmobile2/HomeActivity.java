package com.example.projetmobile2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private DatabaseHelper myDB;
    private EditText editTextNom, editTextAdresse, editTextService, editTextPlat;
    private RatingBar ratingBar;
    private SeekBar seekBar;
    private Button buttonAdd, buttonViewAll;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        myDB = new DatabaseHelper(this); // Initialize DatabaseHelper

        // Reference UI elements
        editTextNom = findViewById(R.id.editTextNom);
        editTextAdresse = findViewById(R.id.editTextADRESS);
        editTextService = findViewById(R.id.editTextService);
        editTextPlat = findViewById(R.id.editTextPlat);

        ratingBar = findViewById(R.id.ratingBar);
        seekBar = findViewById(R.id.seekBarPrix);

        buttonAdd = findViewById(R.id.buttonSubmit);
        buttonViewAll = findViewById(R.id.viewAll);
        buttonAdd.setText("Submit");


        // Add data on button click
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomRes = editTextNom.getText().toString();
                String adresse = editTextAdresse.getText().toString();
                String service = editTextService.getText().toString();
                String plat = editTextPlat.getText().toString();
                float prixMoyen = (float) seekBar.getProgress() / seekBar.getMax();
                int nbEtoiles = (int) ratingBar.getRating();

                // Create a Restaurant object
                Restaurant restaurant = new Restaurant(0, nomRes, adresse, service, plat, prixMoyen, nbEtoiles);

                // Insert the restaurant using DatabaseHelper
                boolean isInserted = myDB.insertData(restaurant);

                if (isInserted) {
                    Toast.makeText(HomeActivity.this, "Restaurant Inserted", Toast.LENGTH_LONG).show();
                    // Clear edit texts after successful insertion
                    clearEditTexts();
                } else {
                    Toast.makeText(HomeActivity.this, "Restaurant Not Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });


        // View all restaurants on button click (implementation missing, likely starts another activity)
        buttonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start DisplayDataActivity to show the data in a ListView (implementation needed)
                Intent intent = new Intent(HomeActivity.this, RestaurantActivity.class); // Modify class name if needed
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        if (intent.getStringExtra("restaurant_id") != null) {
            buttonAdd.setText("Edit");
            String restaurantId= intent.getStringExtra("restaurant_id");
            String restaurantName = intent.getStringExtra("restaurant_name");
            editTextNom.setText(restaurantName);
            String restaurantAddress = intent.getStringExtra("restaurant_address");
            editTextAdresse.setText(restaurantAddress);
            String restaurantQualitePlats = intent.getStringExtra("restaurant_qualite_plats");
            editTextPlat.setText(restaurantQualitePlats);
            String restaurantQualiteService = intent.getStringExtra("restaurant_qualite_service");
            editTextService.setText(restaurantQualiteService);
            double restaurantPrixMoyen = intent.getDoubleExtra("restaurant_prix_moyen", 0.0);
                seekBar.setProgress((int) restaurantPrixMoyen);
            int restaurantNbEtoiles = intent.getIntExtra("restaurant_nb_etoiles", 0);
                ratingBar.setRating(restaurantNbEtoiles);
            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDB.updateRestaurantById(Integer.parseInt(restaurantId),new Restaurant(Integer.parseInt(restaurantId),editTextNom.getText().toString(),editTextAdresse.getText().toString(),editTextPlat.getText().toString(),editTextService.getText().toString(),0,0));
                    Toast.makeText(HomeActivity.this, "Restaurant Edited! click View to see chan,ges", Toast.LENGTH_LONG).show();

                }
            });

        }
    }

    private void clearEditTexts() {
        editTextNom.setText("");
        editTextAdresse.setText("");
        editTextService.setText("");
        editTextPlat.setText("");
    }
}
