package com.example.projetmobile2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.util.ArrayList;

public class RestaurantActivity extends AppCompatActivity implements CustomViewAdapter.OnItemClickListener {
    RecyclerView recyclerView;
    Button goHome;
    ArrayList<Restaurant> restaurantNames;
    CustomViewAdapter customViewAdapter;

    DatabaseHelper myDB = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        displayItems();
    }

    private void displayItems() {
        goHome = findViewById(R.id.gohome);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RestaurantActivity.this, HomeActivity.class));

            }
        });

        // Get data from DatabaseHelper

        Cursor res = myDB.getAllData(); // Call new method

        restaurantNames = new ArrayList<>();
        while (res.moveToNext()) {
            int id = res.getInt(0); // Assuming id is in COL_1 (index 0)
            String restaurantName = res.getString(1); // Get restaurant name from COL_2 (index 1)
            String adresseRestaurant = res.getString(2); // Get address from COL_3 (index 2)
            String qualitePlats = res.getString(3); // Get quality of plates from COL_4 (index 3)
            String qualiteService = res.getString(4); // Get quality of service from COL_5 (index 4)
            float prixMoyen = res.getFloat(5); // Get average price from COL_6 (index 5)
            int nbEtoiles = res.getInt(6); // Get number of stars from COL_7 (index 6)

            Restaurant restaurant = new Restaurant(id, restaurantName, adresseRestaurant, qualitePlats, qualiteService, prixMoyen, nbEtoiles);
            restaurantNames.add(restaurant);
        }

        res.close();


        customViewAdapter = new CustomViewAdapter(this,restaurantNames,this);
        recyclerView.setAdapter(customViewAdapter);
    }

    public void showAlertDialog(Restaurant restaurant , int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(restaurant.getNomRestaurant());
        builder.setMessage(restaurant.toString());

        builder.setPositiveButton("EDIT", (dialog, which) -> {
            Intent intent = new Intent(this, HomeActivity.class);

            // Put restaurant data as extras in the intent
            intent.putExtra("restaurant_id", String.valueOf(restaurant.getId()));
            intent.putExtra("restaurant_name", restaurant.getNomRestaurant());
            intent.putExtra("restaurant_address", restaurant.getAdresseRestaurant());
            intent.putExtra("restaurant_qualite_plats", restaurant.getQualitePlats());
            intent.putExtra("restaurant_qualite_service", restaurant.getQualiteService());
            intent.putExtra("restaurant_prix_moyen", restaurant.getPrixMoyen());
            intent.putExtra("restaurant_nb_etoiles", restaurant.getNbEtoiles());
            startActivity(intent);
            dialog.dismiss();
        });

        builder.setNegativeButton("DELETE", (dialog, which) -> {

            customViewAdapter.remove(position);
            myDB.deleteRestaurantById(restaurant.id);
            dialog.dismiss();
            Toast.makeText(this, "DELETED!", Toast.LENGTH_SHORT).show();

        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onItemClick(int position, Restaurant restaurant) {
        showAlertDialog(restaurant,position);
    }
}
