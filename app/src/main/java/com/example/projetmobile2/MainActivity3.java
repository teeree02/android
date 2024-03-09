package com.example.projetmobile2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // Retrieve data from the database
        DatabaseHelper myDB = new DatabaseHelper(this);
        Cursor res = myDB.getAllData();

        // Extract data from the Cursor and add it to a list
        List<String> dataList = new ArrayList<>();
        while (res.moveToNext()) {
            dataList.add("ID res: " + res.getString(0) + "\n"
                    + "Nom RES: " + res.getString(1) + "\n"
                    + "ADRS: " + res.getString(2) + "\n"
                    + "Plat: " + res.getString(3) + "\n"
                    + "Service: " + res.getString(4) + "\n"
                    + "Prix: " + res.getString(5) + "\n"
                    + "Rate: " + res.getString(6) + "\n\n");
        }

        // Display the data in a ListView
        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
    }
}
