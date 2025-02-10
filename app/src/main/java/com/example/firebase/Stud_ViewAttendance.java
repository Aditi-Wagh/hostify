package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Stud_ViewAttendance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_view_attendance);

        Intent receivedIntent = getIntent();
        String Email = receivedIntent.getStringExtra("Email");
        String Password = receivedIntent.getStringExtra("Password");
    }
}