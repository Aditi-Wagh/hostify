package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Warden_dashboard extends AppCompatActivity {

    ImageView leave,details,night,notice;
    Button back;
    ImageView bars;
    private RelativeLayout popupLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warden_dashboard);

        leave = findViewById(R.id.leave);
        details = findViewById(R.id.stud_details);
        night = findViewById(R.id.Nightout);
        notice = findViewById(R.id.Notice);
        bars = findViewById(R.id.three_bars);
        popupLayout = findViewById(R.id.popupLayout);
        back = findViewById(R.id.back);

        bars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupLayout.getVisibility() == View.VISIBLE) {
                    popupLayout.setVisibility(View.GONE); // Close the popup
                } else {
                    popupLayout.setVisibility(View.VISIBLE); // Open the popup
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),Employee_dashboard.class);
                startActivity(i);
                finish();

            }
        });

        String Email = getIntent().getStringExtra("Email");
        String Password = getIntent().getStringExtra("Password");

        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),Leave_Apply.class);
                i.putExtra("Email",Email);
                i.putExtra("Password",Password);
                startActivity(i);
                finish();

            }
        });

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),View_Student.class);
                i.putExtra("Choice","View_Student");
                startActivity(i);
                finish();

            }
        });

        night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), View_Student.class);
                i.putExtra("Choice","NightOut");
                startActivity(i);
                finish();

            }
        });

        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),NoticeBoard.class);
                startActivity(i);
                finish();

            }
        });

    }
}