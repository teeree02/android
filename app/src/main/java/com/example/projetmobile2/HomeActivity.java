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
import android.widget.TextView;

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

        myDB = new DatabaseHelper(this);

        editTextNom = findViewById(R.id.editTextNom);
        editTextAdresse = findViewById(R.id.editTextADRESS);
        editTextService = findViewById(R.id.editTextService);
        editTextPlat = findViewById(R.id.editTextPlat);

        ratingBar = findViewById(R.id.ratingBar);
        seekBar = findViewById(R.id.seekBarPrix);

        TextView textViewNom = findViewById(R.id.nomTextView);
        TextView textViewPrenom = findViewById(R.id.prenomTextView);

        buttonAdd = findViewById(R.id.buttonSubmit);
        buttonViewAll = findViewById(R.id.viewAll);
        buttonAdd.setText("Submit");

        Intent intent = getIntent();

        String nom = intent.getStringExtra("nom: ");
        String prenom = intent.getStringExtra("prenom: ");

        textViewNom.setText(nom);
        textViewPrenom.setText(prenom);





        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomRes = editTextNom.getText().toString();
                String adresse = editTextAdresse.getText().toString();
                String service = editTextService.getText().toString();
                String plat = editTextPlat.getText().toString();
                float prixMoyen = (float) seekBar.getProgress() / seekBar.getMax();
                int nbEtoiles = (int) ratingBar.getRating();


                Restaurant restaurant = new Restaurant(0, nomRes, adresse, service, plat, prixMoyen, nbEtoiles);


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



        buttonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(HomeActivity.this, RestaurantActivity.class);
                startActivity(intent1);
            }
        });

        Intent receivedIntent = getIntent();

        if (receivedIntent.getStringExtra("restaurant_id") != null) {
            buttonAdd.setText("Edit");
            String restaurantId= receivedIntent.getStringExtra("restaurant_id");
            String restaurantName = receivedIntent.getStringExtra("restaurant_name");
            editTextNom.setText(restaurantName);
            String restaurantAddress = receivedIntent.getStringExtra("restaurant_address");
            editTextAdresse.setText(restaurantAddress);
            String restaurantQualitePlats = receivedIntent.getStringExtra("restaurant_qualite_plats");
            editTextPlat.setText(restaurantQualitePlats);
            String restaurantQualiteService = receivedIntent.getStringExtra("restaurant_qualite_service");
            editTextService.setText(restaurantQualiteService);
            buttonAdd.setOnClickListener(v -> {
                double restaurantPrixMoyen  = (float) seekBar.getProgress() / seekBar.getMax();
                int restaurantNbEtoiles = (int) ratingBar.getRating();
                myDB.updateRestaurantById(Integer.parseInt(restaurantId),new Restaurant(Integer.parseInt(restaurantId),editTextNom.getText().toString(),editTextAdresse.getText().toString(),editTextPlat.getText().toString(),editTextService.getText().toString(), (float) restaurantPrixMoyen,restaurantNbEtoiles));
                Toast.makeText(HomeActivity.this, "Restaurant Edited! click View to see changes", Toast.LENGTH_LONG).show();
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
