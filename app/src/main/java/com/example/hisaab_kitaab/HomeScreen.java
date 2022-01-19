package com.example.hisaab_kitaab;

import static androidx.appcompat.app.AlertDialog.*;
import static com.google.android.material.internal.ContextUtils.getActivity;
import static java.security.AccessController.getContext;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Calendar;

public class HomeScreen extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private ArrayList<KhataModel> khataModelArrayList;
    private DBHandler dbHandler;
    private RVAdapter rvAdapter;
    private RecyclerView recyclerView;
    Button add_btn;
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
                builder.setView(inflater.inflate(R.layout.dialog_signin, null));
                builder.setNeutralButton("pick", new OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText et_recipient = findViewById(R.id.et_recipient);
                        EditText et_date=findViewById(R.id.et_date);
                        EditText et_amount=findViewById(R.id.et_amount);
                        Button pick = findViewById(R.id.pickBtn);
                        ChipGroup type = findViewById(R.id.type_group);
                        final Calendar newCalendar = Calendar.getInstance();
                        final DatePickerDialog  StartTime = new DatePickerDialog(HomeScreen.this, new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Calendar newDate = Calendar.getInstance();
                                newDate.set(year, monthOfYear, dayOfMonth);
                                et_date.setText((CharSequence) newDate.getTime());
                            }
                        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                        StartTime.show();
                    }
                })
                .setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        ;// Add action button
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }


        });
        khataModelArrayList = new ArrayList<>();
        dbHandler = new DBHandler(HomeScreen.this);
        khataModelArrayList = dbHandler.readRecords();

        rvAdapter=new RVAdapter(khataModelArrayList,HomeScreen.this);
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeScreen.this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(rvAdapter);

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }
}