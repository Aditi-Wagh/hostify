package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Student_dashboard extends AppCompatActivity {

    ImageView attend,mess,laund,night,issue;
    ImageView bars;
    private DatabaseReference rootDB;
    private RelativeLayout popupLayout;
    Button admin,notice,logout,back,nightout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        bars = findViewById(R.id.three_bars);
        popupLayout = findViewById(R.id.popupLayout);
        back = findViewById(R.id.back);
        admin = findViewById(R.id.navigateToPage1Button);
        nightout = findViewById(R.id.Page2Button);
        notice = findViewById(R.id.navigateToPage2Button);
        logout = findViewById(R.id.logout);



        attend = findViewById(R.id.attendance);
        mess   = findViewById(R.id.mess);
        laund  = findViewById(R.id.Laundry);
        night  = findViewById(R.id.night);
        issue  = findViewById(R.id.issue);
        rootDB = FirebaseDatabase.getInstance().getReference();
        String Email = getIntent().getStringExtra("Email");
        String Password = getIntent().getStringExtra("Password");
        Toast.makeText(Student_dashboard.this, "Email ,"+Email+" and Pass , "+Password, Toast.LENGTH_LONG).show();


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

                popupLayout.setVisibility(View.GONE);

            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Student_Profile.class);
                i.putExtra("Email",Email);
                i.putExtra("Password",Password);
                startActivity(i);
                finish();

            }
        });

        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), Stud_noticeboard.class);
                startActivity(i);
                finish();

            }
        });

        nightout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                rootDB.child("Student").addValueEventListener(new ValueEventListener() {
                    //     addListenerForSingleValueEvent
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean isLoginValid = false;
                        String Name = " ";
                        String Room = " ";
                        String Hostel = " ";

                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            String savedEmail = userSnapshot.child("semail").getValue(String.class);
                            String savedPassword = userSnapshot.child("spass").getValue(String.class);
                            if (savedEmail != null && savedPassword != null
                                    && savedEmail.equals(Email) && savedPassword.equals(Password)) {

                                Name = userSnapshot.child("sname").getValue(String.class);
                                Room = userSnapshot.child("room").getValue(String.class);
                                Hostel = userSnapshot.child("hostel").getValue(String.class);

                                Intent i = new Intent(getApplicationContext(), View_WardenNight.class);
                                i.putExtra("choice","NightOut2");
                                i.putExtra("name",Name);
                                i.putExtra("Hostel",Hostel);
                                i.putExtra("Room",Room);
                                startActivity(i);
                                finish();
                                break;
                            }
                        }
                        }


                    public void onCancelled(DatabaseError error) {
                        Toast.makeText(Student_dashboard.this, "Database error occurred", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Start.class);
                startActivity(i);
                finish();
            }
        });

        //---------------------------------------------------------------------------------------------------


        attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Stud_ViewAttendance.class);
                i.putExtra("Email",Email);
                i.putExtra("Password",Password);
                startActivity(i);
                finish();
            }
        });

        mess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), StudMess_dashboard.class);
                i.putExtra("Email",Email);
                i.putExtra("Password",Password);
                startActivity(i);
                finish();
            }
        });

        laund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Add_Laundry.class);
                i.putExtra("Email",Email);
                i.putExtra("Password",Password);
                startActivity(i);
                finish();
            }
        });

        night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Add_Nightout.class);
                i.putExtra("Email",Email);
                i.putExtra("Password",Password);
                startActivity(i);
                finish();
            }
        });

        issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),add_issue.class);
                i.putExtra("Email",Email);
                i.putExtra("Password",Password);
                startActivity(i);
                finish();
            }
        });



    }
}