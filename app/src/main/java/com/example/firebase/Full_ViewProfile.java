package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Full_ViewProfile extends AppCompatActivity {

    String Name,Hostel,Room;
    Button back;
    DatabaseReference rootDB;
    EditText sname,sph,pname,pph,semail,spass,hos,room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_emp_profile);

        rootDB = FirebaseDatabase.getInstance().getReference();

        Name = getIntent().getStringExtra("name");
        Hostel = getIntent().getStringExtra("Hostel");
        Room = getIntent().getStringExtra("Room");
        back = findViewById(R.id.button);

        sname = findViewById(R.id.Stud_Name);
        sph = findViewById(R.id.Stud_Phone);
        pname = findViewById(R.id.Parent_Name);
        pph = findViewById(R.id.Parent_Phone);
        semail = findViewById(R.id.Stud_Email);
        spass = findViewById(R.id.Stud_Pass);
        hos = findViewById(R.id.Hostel);
        room = findViewById(R.id.Room);

        sname.setText(Name);
        hos.setText(Hostel);
        room.setText(Room);

        rootDB.child("Student").addValueEventListener(new ValueEventListener() {
            //     addListenerForSingleValueEvent
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean isLoginValid = false;
                String sPh =" ";
                String pName = " ";
                String pPh = " ";
                String sEmail = " ";
                String sPass = " ";

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String savedName = userSnapshot.child("sname").getValue(String.class);
                    String savedHostel = userSnapshot.child("hostel").getValue(String.class);
                    String savedRoom = userSnapshot.child("room").getValue(String.class);
                    if (savedName != null && savedHostel != null && savedRoom != null
                            && savedName.equals(Name) && savedHostel.equals(Hostel) && savedRoom.equals(Room)) {

                        sPh = userSnapshot.child("sph").getValue(String.class);
                        pName = userSnapshot.child("pname").getValue(String.class);
                        pPh = userSnapshot.child("pph").getValue(String.class);
                        sEmail = userSnapshot.child("semail").getValue(String.class);
                        sPass = userSnapshot.child("spass").getValue(String.class);


                        sph.setText(sPh);
                        pname.setText(pName);
                        pph.setText(pPh);
                        semail.setText(sEmail);
                        spass.setText(sPass);
                        isLoginValid = true;
                        break;
                    }
                }

                if (!isLoginValid) {

                    Toast.makeText(Full_ViewProfile.this, " Content Not Present ", Toast.LENGTH_SHORT).show();
                }
            }

            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
                Toast.makeText(Full_ViewProfile.this, "Database error occurred", Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), View_Student.class);
                in.putExtra("Choice","View_Student");
                startActivity(in);
                finish();

            }
        });

    }
}