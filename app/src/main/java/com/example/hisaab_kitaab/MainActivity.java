package com.example.hisaab_kitaab;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button signInBtn,signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout,new fragment_login()).commit();
        signInBtn = findViewById(R.id.signInBtn);
        signUpBtn = findViewById(R.id.signUpBtn);
        signInBtn.setOnClickListener(view -> getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout,new fragment_login()).commit());
        signUpBtn.setOnClickListener(view -> getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout,new fragment_register()).commit());
    }
}