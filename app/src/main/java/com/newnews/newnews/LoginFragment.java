package com.newnews.newnews;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private EditText email_login, password_login;
    private Button button_login;
    private ProgressDialog progressDialog;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        email_login = rootView.findViewById(R.id.email_login);
        password_login = rootView.findViewById(R.id.password_login);
        button_login = rootView.findViewById(R.id.button_login);
        progressDialog = new ProgressDialog(getContext());

        button_login.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v == button_login) {
            userLogin();
        }
    }

    private void userLogin() {

        String email = email_login.getText().toString().trim();
        String password = password_login.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getContext(), "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getContext(), "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage("Logging In...");
        progressDialog.show();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Login successfully", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                    Intent i = new Intent(getActivity(), DetailActivity.class);
                    i.putExtra("source", "create");
                    startActivity(i);
                } else {
                    Toast.makeText(getActivity(), "Login failed", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            }
        });

    }
}
