package com.example.lenovo.movielist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class check extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("login_email",MODE_PRIVATE);
        email = sharedPreferences.getString("email","");
        if (email == ""){
            startActivity(new Intent(getApplicationContext(),login.class));
        }else{
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    }
}
