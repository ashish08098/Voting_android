package com.example.praticeandroidsgn;


import android.widget.EditText;

public class DataClass {
    private String name;
    private String password;
    private String contact;
    private String email;
    private String gender;
    private  String age;

    public DataClass(EditText first, EditText pass, EditText userContact, EditText email, EditText age) {
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }

    public DataClass(String name, String password, String contact, String email, String gender, String age) {
        this.name = name;
        this.password = password;
        this.contact = contact;
        this.email = email;
        this.gender = gender;
        this.age = age;
    }



}
