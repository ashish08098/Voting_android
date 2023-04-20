package com.example.praticeandroidsgn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class administratorLogin extends AppCompatActivity {

    EditText emailEditText,passwordEditText;
    Button loginbtn;
    ProgressBar progresssBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_login);

        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        progresssBar = findViewById(R.id.progreass_bar);
        loginbtn = findViewById(R.id.login_button);

        loginbtn.setOnClickListener((v)-> loginUser());
    }
    void loginUser(){
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        boolean isvalidated = validateData(email,password);
        if(!isvalidated){
            return;
        }
        loginAccountInFirebase(email,password);
    }
    void loginAccountInFirebase(String email, String password){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        changeInProgress(true);
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                changeInProgress(false);
                if(task.isSuccessful()){
                    //login is success
                    if(firebaseAuth.getCurrentUser().isEmailVerified()){
                        //go to mainactivity
                        startActivity((new Intent(administratorLogin.this,MainActivity.class)));
                        finish();
                    }else {
                        utility.showToast(administratorLogin.this,"Email not verified, Please verify your email!");
                    }
                }else {
                    //login failed
                    utility.showToast(administratorLogin.this,task.getException().getLocalizedMessage());
                }
            }
        });

    }
    void changeInProgress(boolean inProgress){
        if(inProgress){
            progresssBar.setVisibility(View.VISIBLE);
            loginbtn.setVisibility(View.GONE);
        }else{
            progresssBar.setVisibility(View.GONE);
            loginbtn.setVisibility(View.VISIBLE);
        }
    }
    boolean validateData(String email, String password){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Email is invalid");
            return false;
        }
        if (password.length()<6){
            passwordEditText.setError("Password length is invalid");
            return false;
        }

        return true;
    }
}