package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class Employee_dashboard extends AppCompatActivity {
    ImageView warden,security,mess;
    Button back;
    ImageView bars;
    private RelativeLayout popupLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_dashboard);

        warden   = findViewById(R.id.Warden);
        security = findViewById(R.id.Security);
        mess     = findViewById(R.id.Mess);
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

                Intent i = new Intent(getApplicationContext(),Start.class);
                startActivity(i);
                finish();

            }
        });

        warden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Login_warden.class);
                startActivity(i);
                finish();

            }
        });

        security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Login_security.class);
                startActivity(i);
                finish();

            }
        });

        mess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Login_mess.class);
                startActivity(i);
                finish();

            }
        });

    }
}