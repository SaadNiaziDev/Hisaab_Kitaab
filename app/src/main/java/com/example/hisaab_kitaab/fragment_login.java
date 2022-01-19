package com.example.hisaab_kitaab;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class fragment_login extends Fragment {
    Button loginBtn;
    EditText email,password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        email=rootView.findViewById(R.id.et_amount);
        password=rootView.findViewById(R.id.et_date);
        loginBtn= rootView.findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAttract(view);
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    public void goToAttract(View v)
    {
        Intent intent = new Intent(getContext(), HomeScreen.class);
        startActivity(intent);
    }
}