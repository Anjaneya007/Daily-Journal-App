package com.example.firestoreown;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    Button reg,log;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        reg=findViewById(R.id.registerBtnH);
        log=findViewById(R.id.loginBtnH);
        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x= new Intent(MainActivity.this,RegisterPage.class);
                startActivity(x);
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l=new Intent(MainActivity.this,LoginPage.class);
                startActivity(l);
            }
        });
    }
}