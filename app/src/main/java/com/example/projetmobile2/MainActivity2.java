package com.example.projetmobile2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;


public class MainActivity2 extends AppCompatActivity {
    DatabaseHelper myDB;
    private TextView nomTextView;
    private TextView prenomTextView;

    EditText editTextNom,editTextADRESS,editTextService,editTextPlat ;
    RatingBar ratingBar;
    SeekBar seekBar;
    Button button;
    Button buttonView;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        myDB= new DatabaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nomTextView = findViewById(R.id.nomTextView);
        prenomTextView = findViewById(R.id.prenomTextView);

        // Récupérer les données de l'Intent
        Intent intent = getIntent();
        if (intent != null) {
            String nom = intent.getStringExtra("nom: ");
            String prenom = intent.getStringExtra("prenom: ");

            // Mettre à jour les TextView avec les valeurs récupérées
            nomTextView.setText("Nom: " + nom);
            prenomTextView.setText("Prénom: " + prenom);
        }
        button=(Button)findViewById(R.id.buttonSubmit);
        buttonView=(Button)findViewById(R.id.viewAll);
        AddData();
        viewAll();
    }
    public void viewAll() {
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start DisplayDataActivity to show the data in a ListView
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });
    }
    public void AddData(){

        editTextNom =(EditText) findViewById(R.id.editTextNom);
        editTextADRESS =(EditText)findViewById(R.id.editTextADRESS);
        editTextService=(EditText) findViewById(R.id.editTextService);
        editTextPlat=(EditText) findViewById(R.id.editTextPlat);

        ratingBar =(RatingBar) findViewById(R.id.ratingBar);
        seekBar= (SeekBar)findViewById(R.id.seekBarPrix);

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String NomRes = editTextNom.getText().toString();
                        String Adresse = editTextADRESS.getText().toString();
                        String Service = editTextService.getText().toString();
                        String Plat = editTextPlat.getText().toString();
                        float seekBarValue = (float) seekBar.getProgress() / seekBar.getMax();
                        int rating = (int) ratingBar.getRating();

                        boolean isInserted= myDB.insertData(NomRes,Adresse,Service,Plat,seekBarValue,rating);
                        if (isInserted==true)
                            Toast.makeText(MainActivity2.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity2.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );

    }

}