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

public class View_Leave extends AppCompatActivity {

    String Name,Hostel,Type,Choice;
    Button back,accept,reject;
    DatabaseReference rootDB;
    EditText sname,sph,pname,pph;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_view_profile);

        back = findViewById(R.id.button);
        rootDB = FirebaseDatabase.getInstance().getReference();
        Name = getIntent().getStringExtra("name");
        Hostel = getIntent().getStringExtra("Hostel");
        Type = getIntent().getStringExtra("Type");
        Choice = getIntent().getStringExtra("choice");

        if(Choice.equals("Leave")){
            accept.setText("Accept");
            reject.setText("Reject");
        }

        sname = findViewById(R.id.Stud_Name1);
        sph = findViewById(R.id.Stud_Phone1);
        pname = findViewById(R.id.Parent_Name1);
        pph = findViewById(R.id.Parent_Phone1);

        sname.setText(Name);


        rootDB.child("Leave").addValueEventListener(new ValueEventListener() {
            //     addListenerForSingleValueEvent
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean isLoginValid = false;
                String reason = " ";
                String out =" ";
                String in = " ";

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String savedName = userSnapshot.child("ename").getValue(String.class);
                    String savedHostel = userSnapshot.child("Hostel").getValue(String.class);
                    String savedType = userSnapshot.child("Type").getValue(String.class);
                    if (savedName != null && savedHostel != null && savedType != null
                            && savedName.equals(Name) && savedHostel.equals(Hostel) && savedType.equals(Type)) {

                        reason = userSnapshot.child("Reason").getValue(String.class);
                        out = userSnapshot.child("OUT").getValue(String.class);
                        in = userSnapshot.child("IN").getValue(String.class);

                        pph.setText(reason);
                        sph.setText(out);
                        pname.setText(in);
                        isLoginValid = true;
                        break;
                    }
                }

                if (!isLoginValid) {
                    Toast.makeText(View_Leave.this, " Content Not Present ", Toast.LENGTH_SHORT).show();
                }
            }

            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
                Toast.makeText(View_Leave.this, "Database error occurred", Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), View_Student.class);
                in.putExtra("Choice","Leave");
                startActivity(in);
                finish();

            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String leavekey = rootDB.child("Leave").push().getKey();
                DatabaseReference empRef = rootDB.child("Leave").child(leavekey);

                empRef.child("Status").setValue("Accept");
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String leavekey = rootDB.child("Leave").push().getKey();
                DatabaseReference empRef = rootDB.child("Leave").child(leavekey);

                empRef.child("Status").setValue("Reject");
            }
        });



    }
}
