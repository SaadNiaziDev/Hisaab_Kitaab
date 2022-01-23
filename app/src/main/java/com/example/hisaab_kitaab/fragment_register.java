package com.example.hisaab_kitaab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class fragment_register extends Fragment {

    FirebaseAuth auth;
    FirebaseDatabase database;
    Button register;
    EditText et_email,et_password,et_repassword,et_name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
        register = rootView.findViewById(R.id.btn_register);
        et_email= rootView.findViewById(R.id.et_email);
        et_password=rootView.findViewById(R.id.et_password);
        et_name=rootView.findViewById(R.id.et_name);
        et_repassword=rootView.findViewById(R.id.et_repassword);

        register.setOnClickListener(view -> {
            String password = et_password.getText().toString();
            String repassword = et_repassword.getText().toString();
            String username = et_name.getText().toString();
            String email = et_email.getText().toString();
            if (email.isEmpty() || password.isEmpty() || username.isEmpty() || repassword.isEmpty()){
                Toast.makeText(getActivity(), "ALL INPUTS ARE REQUIRED! ", Toast.LENGTH_LONG).show();
            }else{
                if(email.contains(".com") && email.contains("@") && email.length()>12 && username.length()>4 ){
                    if(password.equals(repassword)) {
                        Toast.makeText(getActivity(), "User Has Been Registered! ", Toast.LENGTH_SHORT).show();
                        et_name.setText("");
                        et_email.setText("");
                        et_password.setText("");
                        et_repassword.setText("");
                        auth.createUserWithEmailAndPassword(
                                email,
                                password
                        ).addOnCompleteListener(task -> {
                            if(task.isSuccessful()){
                                Users user = new Users(
                                        username,
                                        email,
                                        password
                                );
                                String id = Objects.requireNonNull(task.getResult().getUser()).getUid();
                                database.getReference().child("Users").child(id).setValue(user);
                            }
                    });
                    }else{
                        Toast.makeText(getActivity(), "Password Doesn't Matched!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(), "Invalid email or username!", Toast.LENGTH_SHORT).show();
                }
            }



        });
        // Inflate the layout for this fragment
        return rootView;
    }
}