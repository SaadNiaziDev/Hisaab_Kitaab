package com.example.hisaab_kitaab;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hisaab_kitaab.models.Khata;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class HomeScreen extends AppCompatActivity {

    private ArrayList<Khata> khataArrayList;
    private RVAdapter rvAdapter;
    private RecyclerView recyclerView;
    FirebaseUser user;
    FirebaseAuth auth;
    FirebaseDatabase database;
    Button add_btn,logout_btn;
    EditText et_amount,et_date,et_recipient;
    ChipGroup chipGroup;
    TextView tv_user;
    String userId,username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            userId = user.getUid();
        }
        ;
        tv_user = findViewById(R.id.tv_user);
        logout_btn = findViewById(R.id.logout_btn);
        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeScreen.this);
            LayoutInflater inflater = getLayoutInflater();
            View rootview = inflater.inflate(R.layout.dialog_signin, null);
            builder.setView(rootview);
            builder.setPositiveButton("add", (dialog, id) -> {
                String recipient = et_recipient.getText().toString();
                String date = et_date.getText().toString();
                String amount = et_amount.getText().toString();
                //String selectedChipText = chipGroup.findViewById(chipGroup.getCheckedChipId()).toString();
                if (recipient.isEmpty() || date.isEmpty() || amount.isEmpty() ) {
                    Toast.makeText(getApplicationContext(), "Please fill all fields!", Toast.LENGTH_SHORT).show();
                } else {
                    Khata khata = new Khata(
                            recipient,
                            date,
                            amount
                            //selectedChipText
                    );
                    FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("Khata").setValue(khata);
                }
            });
            builder.setNegativeButton("cancel", (dialog, id) -> dialog.dismiss());

            builder.show();
            et_recipient = rootview.findViewById(R.id.et_recipient);
            et_date =  rootview.findViewById(R.id.et_date);
            et_amount = rootview.findViewById(R.id.et_amount);
            chipGroup = rootview.findViewById(R.id.type_group);
            // perform click event on edit text
            et_date.setOnClickListener(v -> {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                @SuppressLint("SetTextI18n") DatePickerDialog datePickerDialog = new DatePickerDialog(HomeScreen.this,
                        (view1, year, monthOfYear, dayOfMonth) -> {
                            // set day of month , month and year value in the edit text
                            et_date.setText(dayOfMonth + "/"
                                    + (monthOfYear + 1) + "/" + year);

                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            });

        });
        khataArrayList = new ArrayList<>();
        //khataArrayList = dbHandler.readRecords();

        rvAdapter = new RVAdapter(khataArrayList, HomeScreen.this);
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeScreen.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(rvAdapter);

    }
}