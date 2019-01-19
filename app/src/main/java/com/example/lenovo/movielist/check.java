package com.example.lenovo.movielist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class check extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private String email,status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("login_email",MODE_PRIVATE);
        email = sharedPreferences.getString("email","");
        status = sharedPreferences.getString("status","");
        if (email == ""){
            startActivity(new Intent(getApplicationContext(),login.class));
        }else if (email == email && status == "admin"){
            startActivity(new Intent(getApplicationContext(),home_admin.class));
        }else if (email == email && status == "user"){
            startActivity(new Intent(getApplicationContext(),home_user.class));
        }
    }
}
