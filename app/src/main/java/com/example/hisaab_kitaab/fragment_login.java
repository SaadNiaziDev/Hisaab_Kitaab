package com.example.hisaab_kitaab;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class fragment_login extends Fragment {
    FirebaseAuth auth;
    Button loginBtn;
    String name;
    EditText email,password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth=FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        email=rootView.findViewById(R.id.et_email);
        password=rootView.findViewById(R.id.et_password);
        loginBtn= rootView.findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(view -> auth.signInWithEmailAndPassword(
                email.getText().toString(),
                password.getText().toString())
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("username");
                        ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(getActivity(), "Problem Occurred While logging In!",Toast.LENGTH_SHORT).show();
                            }
                        });
                        Intent intent = new Intent(getContext(),HomeScreen.class);
                        startActivity(intent);
                    }
                }));
        return rootView;
    }

}