package com.example.projetmobile2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {
    private Button button;
    private EditText editTextNom;
    private EditText editTextPrenom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNom = findViewById(R.id.editTextNom);
        editTextPrenom = findViewById(R.id.editTextPrenom);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openActivity2();
            }
        });

    }
    public void openActivity2(){
        String nom = editTextNom.getText().toString();
        String prenom = editTextPrenom.getText().toString();

        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("nom: ", nom);
        intent.putExtra("prenom: ", prenom);
        startActivity(intent);
    }
}