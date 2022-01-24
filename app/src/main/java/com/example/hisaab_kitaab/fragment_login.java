package com.example.hisaab_kitaab;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

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
        View rootView = inflater.inflate(R.layout.fragment_login, container, false); // fragment inflates
        email=rootView.findViewById(R.id.et_email);
        password=rootView.findViewById(R.id.et_password);
        loginBtn= rootView.findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(view -> auth.signInWithEmailAndPassword( // email and psswd verification
                email.getText().toString(),
                password.getText().toString())
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Intent intent = new Intent(getContext(),HomeScreen.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "Invalid Credentials!",Toast.LENGTH_SHORT).show();
                    }
                }));
        return rootView;
    }

}