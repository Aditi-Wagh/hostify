package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Add_student extends AppCompatActivity {

    EditText sname,sph,semail,spass,pname,pph,hostel,room;
    Button add;
    Button back;
    ImageView bars;
    private RelativeLayout popupLayout;
    FirebaseAuth mAuth;
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    private DatabaseReference rootDB;


    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent in = new Intent(getApplicationContext(),Admin_dashboard.class);
            startActivity(in);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        //String[] messnums={"Hostel 1", "Hostel 2", "Hostel 3", "Hostel 4"};

        mAuth = FirebaseAuth.getInstance();
        sname = findViewById(R.id.nam);
        sph   = findViewById(R.id.hos);
        semail  = findViewById(R.id.semail);
        spass   = findViewById(R.id.pass);
        pname   = findViewById(R.id.emptype);
        pph   = findViewById(R.id.reason);
        hostel  = findViewById(R.id.hostelno);
        room   = findViewById(R.id.roomno);
        add =   findViewById(R.id.addbutton);
        rootDB = FirebaseDatabase.getInstance().getReference();
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

                Intent i = new Intent(getApplicationContext(),Admin_dashboard.class);
                startActivity(i);
                finish();

            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Sname,Sph,Semail,Spass,Pname,Pph,HostelNo,RoomNo;
                Sname = String.valueOf(sname.getText());
                Sph = String.valueOf(sph.getText());
                Semail = String.valueOf(semail.getText());
                Spass = String.valueOf(spass.getText());
                Pname = String.valueOf(pname.getText());
                Pph = String.valueOf(pph.getText());
                HostelNo= String.valueOf(hostel.getText());
                RoomNo = String.valueOf(room.getText());


                if(TextUtils.isEmpty(Sname) || TextUtils.isEmpty(Sph)|| TextUtils.isEmpty(Semail) || TextUtils.isEmpty(Spass)|| TextUtils.isEmpty(Pname) || TextUtils.isEmpty(Pph)|| TextUtils.isEmpty(HostelNo) || TextUtils.isEmpty(RoomNo)){
                    Toast.makeText(Add_student.this,"Alert : Fill All Details !! ",Toast.LENGTH_LONG).show();
                    return;
                }

                DatabaseReference statusRef = rootDB.child("Hostels").child(HostelNo).child(RoomNo).child("Status");
                statusRef.addValueEventListener(new ValueEventListener() {
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean status =false;
                        if (dataSnapshot.exists()) {
                            String sta = dataSnapshot.getValue(String.class);
                            if (sta != null && sta.equals("No")) {

                                //------------------------------------------------------------------------------------------------------------
                                String hosKey = rootDB.child("Hostels").child(HostelNo).child(RoomNo).push().getKey();
                                DatabaseReference hosRef = rootDB.child("Hostels").child(HostelNo).child(RoomNo).child(hosKey);
                                DatabaseReference hos1Ref = rootDB.child("Hostels").child(HostelNo).child(RoomNo);

                                // DatabaseReference userRef = rootDB.child("Login")//.child(userID);
                                hosRef.child("sname").setValue(Sname);
                                hos1Ref.child("Status").setValue("Yes");

                                Toast.makeText(Add_student.this, "Room Added.",
                                        Toast.LENGTH_SHORT).show();
                                //------------------------------------------------------------------------------------------------------------
                                String studKey = rootDB.child("Student").push().getKey();
                                DatabaseReference studRef = rootDB.child("Student").child(studKey);

                                // DatabaseReference userRef = rootDB.child("Login")//.child(userID);
                                studRef.child("sname").setValue(Sname);
                                studRef.child("sph").setValue(Sph);
                                studRef.child("semail").setValue(Semail);
                                studRef.child("spass").setValue(Spass);
                                studRef.child("pname").setValue(Pname);
                                studRef.child("pph").setValue(Pph);
                                studRef.child("hostel").setValue(HostelNo);
                                studRef.child("room").setValue(RoomNo);



                                Toast.makeText(Add_student.this, "Student Added.",
                                        Toast.LENGTH_LONG).show();

                                //-------------------------------------------------------------------------------------------------------------------
                                String userKey = rootDB.child("Login").push().getKey();
                                DatabaseReference userRef = rootDB.child("Login").child(userKey);

                                // DatabaseReference userRef = rootDB.child("Login")//.child(userID);
                                userRef.child("email").setValue(Semail);
                                userRef.child("password").setValue(Spass);


                                Toast.makeText(Add_student.this, "Account created.",
                                        Toast.LENGTH_SHORT).show();

                                //-------------------------------------------------------------------------------------------------------------------

                            } else {
                                Toast.makeText(Add_student.this, "Room Occupied.",
                                        Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(Add_student.this, "Data not Found.",
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                    public void onCancelled(DatabaseError error) {
                        Toast.makeText(Add_student.this, "Database error occurred", Toast.LENGTH_SHORT).show();
                    }
                });






            }
        });





          /*  autoCompleteTextView=findViewById(R.id.auto_complete_text);
            adapterItems= new ArrayAdapter<String>(this,R.layout.hostel_list, messnums);


            autoCompleteTextView.setAdapter(adapterItems);


            autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                    String item=adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(Add_student.this, "Item: "+item, Toast.LENGTH_SHORT).show();
                }
            });*/

        }
}