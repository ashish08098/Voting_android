package com.example.praticeandroidsgn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaCodec;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class activity_front extends AppCompatActivity {
    EditText emailEditText,passwordEditText,ConfirmPasswordEditText;
    Button createAccountBtn;
    ProgressBar progresssBar;
    TextView loginBtnTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);

        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        ConfirmPasswordEditText = findViewById(R.id.confirm_password_edit_text);
        progresssBar = findViewById(R.id.progreass_bar);
        loginBtnTextView = findViewById(R.id.login_text_veiw_btn);

        createAccountBtn.setOnClickListener(view-> createAccount());
        loginBtnTextView.setOnClickListener(v-> finish());
    }
    void createAccount(){
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmpassword = ConfirmPasswordEditText.getText().toString();

        boolean isvalidated = validateData(email,password,confirmpassword);
        if(!isvalidated){
            return;
        }
        createAccountInFirebase(email,password);
    }
    void createAccountInFirebase(String email, String password){
        changeInProgress(true);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity_front.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        changeInProgress(false);
                        if(task.isSuccessful()){
                            utility.showToast(activity_front.this, "Succesfully create account, Check email to verify");
                            firebaseAuth.getCurrentUser().sendEmailVerification();
                            firebaseAuth.signOut();
                            finish();
                        }else {
                            utility.showToast(activity_front.this, task.getException().getLocalizedMessage());
                        }
                    }
                }
        );
    }
    void changeInProgress(boolean inProgress){
        if(inProgress){
            progresssBar.setVisibility(View.VISIBLE);
            createAccountBtn.setVisibility(View.GONE);
        }else{
            progresssBar.setVisibility(View.GONE);
            createAccountBtn.setVisibility(View.VISIBLE);
        }
    }
    boolean validateData(String email, String password, String confirmpassword){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Email is invalid");
            return false;
        }
        if (password.length()<6){
            passwordEditText.setError("Password length is invalid");
            return false;
        }
        if(!password.equals(confirmpassword)){
            ConfirmPasswordEditText.setError("Password not matched");
            return false;
        }
        return true;
    }
}

