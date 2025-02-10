package com.example.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class View_Issue extends AppCompatActivity {

    String Name,Hostel,Room,Choice;
    Button back,accept,reject;
    DatabaseReference rootDB;
    EditText sname,sph,pname,pph;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_view_profile);

        back = findViewById(R.id.button);
        accept = findViewById(R.id.accept);
        reject = findViewById(R.id.reject);
        rootDB = FirebaseDatabase.getInstance().getReference();

        Name = getIntent().getStringExtra("name");
        Hostel = getIntent().getStringExtra("Hostel");
        Room = getIntent().getStringExtra("Room");
        Choice = getIntent().getStringExtra("choice");

        if(Choice.equals("Issue")){
            accept.setText("Done");
            reject.setText("Pending");
        }

        sname = findViewById(R.id.Stud_Name1);
        sph = findViewById(R.id.Stud_Phone1);
        pname = findViewById(R.id.Parent_Name1);
        pph = findViewById(R.id.Parent_Phone1);

        sname.setText(Name);
        sph.setText(Hostel);
        pname.setText(Room);

        rootDB.child("Issues").addValueEventListener(new ValueEventListener() {
            //     addListenerForSingleValueEvent
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean isLoginValid = false;
                String issue = " ";

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String savedName = userSnapshot.child("sname").getValue(String.class);
                    String savedHostel = userSnapshot.child("shostel").getValue(String.class);
                    String savedRoom = userSnapshot.child("sroom").getValue(String.class);
                    if (savedName != null && savedHostel != null && savedRoom != null
                            && savedName.equals(Name) && savedHostel.equals(Hostel) && savedRoom.equals(Room)) {

                        issue = userSnapshot.child("sissue").getValue(String.class);

                        pph.setText(issue);
                        isLoginValid = true;
                        break;
                    }
                }

                if (!isLoginValid) {
                    Toast.makeText(View_Issue.this, " Content Not Present ", Toast.LENGTH_SHORT).show();
                }
            }

            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
                Toast.makeText(View_Issue.this, "Database error occurred", Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), View_Student.class);
                in.putExtra("Choice","Issue");
                startActivity(in);
                finish();

            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ikey = rootDB.child("Issues").push().getKey();
                DatabaseReference empRef = rootDB.child("Issues").child(ikey);

                empRef.child("Status").setValue("Done");
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ikey = rootDB.child("Issues").push().getKey();
                DatabaseReference empRef = rootDB.child("Issues").child(ikey);

                empRef.child("Status").setValue("Pending");
            }
        });


    }
}
