package com.example.hisaab_kitaab;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class HomeScreen extends AppCompatActivity {

    private ArrayList<Khata> khataArrayList;
    private RVAdapter rvAdapter;
    private RecyclerView recyclerView;
    FirebaseUser user;
    FirebaseAuth auth;
    int counter;
    DatabaseReference databaseReference;
    Button add_btn, logout_btn;
    EditText et_amount, et_date, et_recipient;
    RadioGroup radioGroup;
    TextView tv_user;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        tv_user = findViewById(R.id.tv_user);
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            userId = user.getUid();
        }
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).child("name");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String uName = (String) snapshot.getValue(Boolean.parseBoolean(Users.class.getName()));
                tv_user.setText(uName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        logout_btn = findViewById(R.id.logout_btn);
        logout_btn.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
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
                RadioButton rb = rootview.findViewById(radioGroup.getCheckedRadioButtonId());
                String type = (String) rb.getText();
                if (recipient.isEmpty() || date.isEmpty() || amount.isEmpty() || type.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all fields!", Toast.LENGTH_SHORT).show();
                } else {
                    Khata khata = new Khata(
                            recipient,
                            date,
                            amount,
                            (String) type
                    );
                    FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("Khata").push().setValue(khata);
                    counter++;
                    fetchData();

                }
            });
            builder.setNegativeButton("cancel", (dialog, id) -> dialog.dismiss());

            builder.show();
            et_recipient = rootview.findViewById(R.id.et_recipient);
            et_date = rootview.findViewById(R.id.et_date);
            et_amount = rootview.findViewById(R.id.et_amount);
            radioGroup = rootview.findViewById(R.id.radioGroup);
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
        fetchData();
    }

    public void fetchData() {
        khataArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        rvAdapter = new RVAdapter(khataArrayList, HomeScreen.this);
        recyclerView.setAdapter(rvAdapter);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).child("Khata");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Khata ks = ds.getValue(Khata.class);
                    khataArrayList.add(ks);
                }
                rvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}