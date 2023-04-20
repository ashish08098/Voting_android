package com.example.praticeandroidsgn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextView txtalertName;
    EditText first,pass,UserContact,email,age;
    Button savebtn;
    FirebaseDatabase db;
    DatabaseReference reference;
    RadioButton Malebtn,Femalbtn,otherbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        first=findViewById(R.id.firstName);
        pass=findViewById(R.id.reg);
        UserContact=findViewById(R.id.userContact);

        email=findViewById(R.id.email);
        age=findViewById(R.id.age);
        Malebtn=findViewById(R.id.Male);
        Femalbtn=findViewById(R.id.Female);
        otherbtn=findViewById(R.id.others);
        savebtn =findViewById(R.id.btnSubmit);
        savebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DataClass dataclass=new DataClass(first,pass,UserContact,email,age);
                db= FirebaseDatabase.getInstance();
                reference=db.getReference("user");
                reference.child(String.valueOf(first)).setValue(dataclass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pass.setText("");
                        first.setText("");
                        UserContact.setText("");
                        email.setText("");
                        age.setText("");

                    }
                });

            }
        });
        }
    }
