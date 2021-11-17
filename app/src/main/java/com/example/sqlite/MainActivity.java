package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnListaz, btnRogzitesre, btnModositasra, btnTorlesre;
    private TextView textLista;
    private DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnRogzitesre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rogzitesre = new Intent(MainActivity.this, RogzitesActivity.class);
                startActivity(rogzitesre);
                finish();
            }
        });

        btnListaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor adatok = adatbazis.listaz();
                if (adatok.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "Nincs benne az adatbázisban", Toast.LENGTH_SHORT).show();
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    while (adatok.moveToNext()) {
                        stringBuilder.append("ID: ").append(adatok.getString(0));
                        stringBuilder.append(System.lineSeparator());
                        stringBuilder.append("Keresztnév: ").append(adatok.getString(1));
                        stringBuilder.append(System.lineSeparator());
                        stringBuilder.append("Vezetéknév: ").append(adatok.getString(2));
                        stringBuilder.append(System.lineSeparator());
                        stringBuilder.append("Jegy: ").append(adatok.getString(3));
                        stringBuilder.append(System.lineSeparator());
                        stringBuilder.append(System.lineSeparator());
                    }
                    textLista.setText(stringBuilder.toString());
                }
            }
        });

        btnTorlesre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent torlesre = new Intent(MainActivity.this, TorlesActivity.class);
                startActivity(torlesre);
                finish();
            }
        });
    }

    private void init() {
        btnListaz = findViewById(R.id.btn_olvasas);
        btnRogzitesre = findViewById(R.id.btn_rogzitesre);
        btnModositasra = findViewById(R.id.btn_modositara);
        btnTorlesre = findViewById(R.id.btn_torlesre);
        textLista = findViewById(R.id.text_lista);
        adatbazis = new DBHelper(this);
    }
}