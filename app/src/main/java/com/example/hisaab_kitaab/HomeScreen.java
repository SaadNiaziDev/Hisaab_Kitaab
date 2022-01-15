package com.example.hisaab_kitaab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {

    private ArrayList<KhataModel> khataModelArrayList;
    private DBHandler dbHandler;
    private RVAdapter rvAdapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        khataModelArrayList = new ArrayList<>();
        dbHandler = new DBHandler(HomeScreen.this);
        khataModelArrayList = dbHandler.readRecords();

        rvAdapter=new RVAdapter(khataModelArrayList,HomeScreen.this);
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeScreen.this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(rvAdapter);

    }
}