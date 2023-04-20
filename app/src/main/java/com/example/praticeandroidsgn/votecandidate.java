package com.example.praticeandroidsgn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class votecandidate extends AppCompatActivity {
    private Button btn1,btn2;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=findViewById(R.id.btnSubmit);
        btn2=findViewById(R.id.btnSubmit);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialo();
            }

            public void opendialo() {
                opendialog dialog= new opendialog();
                dialog.show(getSupportFragmentManager() ,"Opendialog");
            }
        });

    }
    public void opendialog(){
        opendialog Opendialog= new opendialog();
        Opendialog.show(getSupportFragmentManager() ,"Opendialog");




    }
}