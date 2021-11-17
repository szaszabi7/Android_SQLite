package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RogzitesActivity extends AppCompatActivity {
    private EditText edit_vnev, edit_knev, edit_jegy;
    private Button btn_rogzit, btn_vissza;
    private DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rogzites);
        init();

        btn_vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vissza = new Intent(RogzitesActivity.this, MainActivity.class);
                startActivity(vissza);
                finish();
            }
        });

        btn_rogzit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vezeteknev = edit_vnev.getText().toString().trim();
                String keresztnev = edit_knev.getText().toString().trim();
                String jegyString = edit_jegy.getText().toString().trim();
                if (vezeteknev.isEmpty() || keresztnev.isEmpty() || jegyString.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Minden mező kitöltése kötelező",
                            Toast.LENGTH_SHORT).show();
                } else{
                    try {
                        int jegy = Integer.parseInt(jegyString);
                        if (jegy < 1 || jegy > 5){
                            Toast.makeText(getApplicationContext(),
                                    "A jegynek 1 és 5 közötti számnak kell lennie",
                                    Toast.LENGTH_SHORT).show();
                        } else{
                            if (adatbazis.rogzites(vezeteknev, keresztnev, jegy)){
                                Toast.makeText(getApplicationContext(), "Sikeres rögzítés",
                                        Toast.LENGTH_SHORT).show();
                            } else{
                                Toast.makeText(getApplicationContext(), "Sikeretelen rögzítés",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (NumberFormatException ex) {
                        Toast.makeText(getApplicationContext(), "A jegynek számnak kell lennie",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void init() {
        edit_vnev = findViewById(R.id.edit_vnev);
        edit_knev = findViewById(R.id.edit_knev);
        edit_jegy = findViewById(R.id.edit_jegy);
        btn_rogzit = findViewById(R.id.btn_rogzit);
        btn_vissza = findViewById(R.id.btn_vissza);
        adatbazis = new DBHelper(this);
    }
}