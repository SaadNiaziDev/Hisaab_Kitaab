package com.example.hisaab_kitaab;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hisaab_kitaab.models.Khata;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Calendar;

public class HomeScreen extends AppCompatActivity {

    private ArrayList<Khata> khataArrayList;
    private DBHandler dbHandler;
    private RVAdapter rvAdapter;
    private RecyclerView recyclerView;
    Button add_btn;
    EditText et_amount,et_date,et_recipient;
    ChipGroup chipGroup;
    TextView tv_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        add_btn=findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeScreen.this);
                LayoutInflater inflater = getLayoutInflater();
                View rootview = inflater.inflate(R.layout.dialog_signin, null);
                builder.setView(rootview);
                builder.setPositiveButton("add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // sign in the user ...
                            }
                        });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });

                builder.show();
                et_recipient = rootview.findViewById(R.id.et_recipient);
                et_date=(EditText) rootview.findViewById(R.id.et_date);
                et_amount=rootview.findViewById(R.id.et_amount);
                chipGroup = rootview.findViewById(R.id.type_group);
                // perform click event on edit text
                et_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // calender class's instance and get current date , month and year from calender
                        final Calendar c = Calendar.getInstance();
                        int mYear = c.get(Calendar.YEAR); // current year
                        int mMonth = c.get(Calendar.MONTH); // current month
                        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                        // date picker dialog
                      DatePickerDialog datePickerDialog = new DatePickerDialog(HomeScreen.this,
                                (view1, year, monthOfYear, dayOfMonth) -> {
                                    // set day of month , month and year value in the edit text
                                    et_date.setText(dayOfMonth + "/"
                                            + (monthOfYear + 1) + "/" + year);

                                }, mYear, mMonth, mDay);
                        datePickerDialog.show();
                    }
                });

            }


        });
        khataArrayList = new ArrayList<>();
        dbHandler = new DBHandler(HomeScreen.this);
        khataArrayList = dbHandler.readRecords();

        rvAdapter=new RVAdapter(khataArrayList,HomeScreen.this);
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeScreen.this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(rvAdapter);

    }
}