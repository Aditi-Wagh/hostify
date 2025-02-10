package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Mess_dashboard extends AppCompatActivity {
    Button add;
    DatabaseReference rootDB;
    EditText breakfast,lunch,dinner;
    Button back;
    ImageView bars;
    private RelativeLayout popupLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_dashboard);

        rootDB = FirebaseDatabase.getInstance().getReference();
        breakfast = findViewById(R.id.Breakfast);
        lunch = findViewById(R.id.lunch);
        dinner = findViewById(R.id.dinner);
        add = findViewById(R.id.addbutton);

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

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String BreakFast = String.valueOf(breakfast.getText());
                String Lunch = String.valueOf(lunch.getText());
                String Dinner = String.valueOf(dinner.getText());

                String empkey = rootDB.child("Menu").push().getKey();
                DatabaseReference empRef = rootDB.child("Menu").child(empkey);

                empRef.child("BreakFast").setValue(BreakFast);
                empRef.child("Lunch").setValue(Lunch);
                empRef.child("Dinner").setValue(Dinner);

                Toast.makeText(Mess_dashboard.this," Menu Added .",Toast.LENGTH_LONG).show();

            }
        });


    }
}