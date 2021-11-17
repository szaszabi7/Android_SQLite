package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.interfaces.RSAMultiPrimePrivateCrtKey;

public class TorlesActivity extends AppCompatActivity {

    private EditText editTorles;
    private Button buttonVissza, buttonTorles;
    private DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torles);
        init();
        buttonVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vissza = new Intent(TorlesActivity.this, MainActivity.class);
                startActivity(vissza);
                finish();
            }
        });

        buttonTorles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String torlendo = editTorles.getText().toString();
                if (torlendo.isEmpty()) {
                    Toast.makeText(TorlesActivity.this, "Nem lehet üres az id mező", Toast.LENGTH_SHORT).show();
                } else {
                    if (adatbazis.torles(torlendo) == 0) {
                        Toast.makeText(TorlesActivity.this, "Nincs ilyen id", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(TorlesActivity.this, "Sikeres törlés", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void init() {
        editTorles = findViewById(R.id.edit_id_torles);
        buttonTorles = findViewById(R.id.buttonTorles);
        buttonVissza = findViewById(R.id.button_id_torles);
        adatbazis = new DBHelper(this);
    }
}