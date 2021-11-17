package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
            public void onClick(View view) {
                String vezeteknev = edit_vnev.getText().toString().trim();
                String keresztnev = edit_knev.getText().toString().trim();
                String jegyString = edit_jegy.getText().toString().trim();
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