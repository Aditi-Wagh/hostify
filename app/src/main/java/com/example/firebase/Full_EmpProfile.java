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

public class Full_EmpProfile extends AppCompatActivity {

    String Name,Hostel,Type;
    Button back;
    DatabaseReference rootDB;
    EditText addr,aadhar,ename,eph,eemail,epass,hos,type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_emp_profile);

        rootDB = FirebaseDatabase.getInstance().getReference();

        Name = getIntent().getStringExtra("name");
        Hostel = getIntent().getStringExtra("Hostel");
        Type = getIntent().getStringExtra("Type");


        ename = findViewById(R.id.Stud_Name);
        eph = findViewById(R.id.Stud_Phone);
        addr = findViewById(R.id.Parent_Name);
        aadhar = findViewById(R.id.Parent_Phone);
        eemail = findViewById(R.id.Stud_Email);
        epass = findViewById(R.id.Stud_Pass);
        hos = findViewById(R.id.Hostel);
        type = findViewById(R.id.Room);

        ename.setText(Name);
        hos.setText(Hostel);
        type.setText(Type);



        rootDB.child("Employees").addValueEventListener(new ValueEventListener() {
            //     addListenerForSingleValueEvent
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean isLoginValid = false;
                String ePh =" ";
                String Addr = " ";
                String Aadhar = " ";
                String eEmail = " ";
                String ePass = " ";


                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String savedName = userSnapshot.child("ename").getValue(String.class);
                    String savedHostel = userSnapshot.child("Hostel").getValue(String.class);
                    String savedType = userSnapshot.child("Type").getValue(String.class);
                    if (savedName != null && savedHostel != null && savedType != null
                            && savedName.equals(Name) && savedHostel.equals(Hostel) && savedType.equals(Type)) {

                        ePh = userSnapshot.child("ephone").getValue(String.class);
                        Addr = userSnapshot.child("address").getValue(String.class);
                        Aadhar = userSnapshot.child("aadhar").getValue(String.class);
                        eEmail = userSnapshot.child("eemail").getValue(String.class);
                        ePass = userSnapshot.child("epass").getValue(String.class);

                        addr.setText(Addr);
                        aadhar.setText(Aadhar);
                        eph.setText(ePh);
                        eemail.setText(eEmail);
                        epass.setText(ePass);

                        isLoginValid = true;
                        break;
                    }
                }


                if (!isLoginValid) {
                    Toast.makeText(Full_EmpProfile.this, " Content Not Present ", Toast.LENGTH_SHORT).show();
                }

            }

            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());

                Toast.makeText(Full_EmpProfile.this, "Database error occurred", Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), View_Student.class);
                in.putExtra("Choice","View_Employese");
                startActivity(in);
                finish();

            }
        });



    }
}