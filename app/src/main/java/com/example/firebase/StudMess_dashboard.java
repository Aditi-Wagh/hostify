package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StudMess_dashboard extends AppCompatActivity {

    TextView reg;
    ImageView view;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_mess_dashboard);


        String Email = getIntent().getStringExtra("Email");
        String Password = getIntent().getStringExtra("Password");

        reg = findViewById(R.id.register);
        view = findViewById(R.id.view_menu);
        back = findViewById(R.id.back);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Add_messRegister.class);
                i.putExtra("Email",Email);
                i.putExtra("Password",Password);
                startActivity(i);
                finish();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), View_Student.class);
                i.putExtra("Choice","View_Mess");
                startActivity(i);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Student_dashboard.class);
                i.putExtra("Email",Email);
                i.putExtra("Password",Password);
                startActivity(i);
                finish();
            }
        });


    }
}