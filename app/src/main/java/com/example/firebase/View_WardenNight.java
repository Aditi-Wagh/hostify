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

public class View_WardenNight extends AppCompatActivity {
    String Name,Hostel,Room,Choice;
    Button back,accept,reject;
    DatabaseReference rootDB;
    EditText sname,sph,pname,pph,reason,addr,status;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_warden_night);

        back = findViewById(R.id.button);
        accept = findViewById(R.id.accept);
        reject = findViewById(R.id.reject);
        rootDB = FirebaseDatabase.getInstance().getReference();

        Name = getIntent().getStringExtra("name");
        Hostel = getIntent().getStringExtra("Hostel");
        Room = getIntent().getStringExtra("Room");
        Choice = getIntent().getStringExtra("choice");


        if(Choice.equals("NightOut")){
            accept.setText("Accept");
            reject.setText("Reject");
        }

        if(Choice.equals("NightOut2")){
            accept.setVisibility(View.INVISIBLE);
            reject.setVisibility(View.INVISIBLE);

            rootDB.child("NightOut").addValueEventListener(new ValueEventListener() {
                //     addListenerForSingleValueEvent
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    boolean isLoginValid = false;
                    String Reason = " ";
                    String Address = " ";
                    String Return = " ";
                    String Leave = " ";
                    String Pname = " ";
                    String Pph = " ";
                    String Status = " ";

                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        String savedName = userSnapshot.child("sname").getValue(String.class);
                        String savedHostel = userSnapshot.child("shostel").getValue(String.class);
                        String savedRoom = userSnapshot.child("sroom").getValue(String.class);
                        if (savedName != null && savedHostel != null && savedRoom != null
                                && savedName.equals(Name) && savedHostel.equals(Hostel) && savedRoom.equals(Room)) {

                            Reason = userSnapshot.child("reason").getValue(String.class);
                            Address = userSnapshot.child("address").getValue(String.class);
                            Return = userSnapshot.child("ReturnDate").getValue(String.class);
                            Leave = userSnapshot.child("LeaveDate").getValue(String.class);
                            Pname = userSnapshot.child("Pname").getValue(String.class);
                            Pph = userSnapshot.child("Pph").getValue(String.class);
                            Status = userSnapshot.child("Status").getValue(String.class);

                            sname.setText(Reason);
                            sph.setText(Address);
                            pname.setText(Return);
                            pph.setText(Leave);
                            reason.setText(Pname);
                            addr.setText(Pph);
                            status.setText(Status);

                            isLoginValid = true;

                            break;
                        }
                    }

                    if (!isLoginValid) {
                        Toast.makeText(View_WardenNight.this, " Content Not Present ", Toast.LENGTH_SHORT).show();
                    }
                }

                public void onCancelled(DatabaseError error) {
                    Toast.makeText(View_WardenNight.this, "Database error occurred", Toast.LENGTH_SHORT).show();
                }
            });
        }

        sname = findViewById(R.id.Stud_Name1);
        sph = findViewById(R.id.Stud_Phone1);
        pname = findViewById(R.id.Parent_Name1);
        pph = findViewById(R.id.Parent_Phone1);
        reason = findViewById(R.id.Reason);
        addr = findViewById(R.id.Address);
        status = findViewById(R.id.Status);

        sname.setText(Name);
        sph.setText(Hostel);
        pname.setText(Room);

        rootDB.child("NightOut").addValueEventListener(new ValueEventListener() {
            //     addListenerForSingleValueEvent
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean isLoginValid = false;
                String Reason = " ";
                String Address = " ";
                String Return = " ";
                String Leave = " ";
                String Pname = " ";
                String Pph = " ";
                String Status = " ";

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String savedName = userSnapshot.child("sname").getValue(String.class);
                    String savedHostel = userSnapshot.child("shostel").getValue(String.class);
                    String savedRoom = userSnapshot.child("sroom").getValue(String.class);
                    if (savedName != null && savedHostel != null && savedRoom != null
                            && savedName.equals(Name) && savedHostel.equals(Hostel) && savedRoom.equals(Room)) {

                        Reason = userSnapshot.child("reason").getValue(String.class);
                        Address = userSnapshot.child("address").getValue(String.class);
                        Return = userSnapshot.child("ReturnDate").getValue(String.class);
                        Leave = userSnapshot.child("LeaveDate").getValue(String.class);
                        Pname = userSnapshot.child("Pname").getValue(String.class);
                        Pph = userSnapshot.child("Pph").getValue(String.class);
                        Status = userSnapshot.child("Status").getValue(String.class);

                        sname.setText(Reason);
                        sph.setText(Address);
                        pname.setText(Return);
                        pph.setText(Leave);
                        reason.setText(Pname);
                        addr.setText(Pph);
                        status.setText(Status);

                        isLoginValid = true;

                        break;
                    }
                }

                if (!isLoginValid) {
                    Toast.makeText(View_WardenNight.this, " Content Not Present ", Toast.LENGTH_SHORT).show();
                }
            }

            public void onCancelled(DatabaseError error) {
                Toast.makeText(View_WardenNight.this, "Database error occurred", Toast.LENGTH_SHORT).show();
            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rootDB.child("NightOut").addValueEventListener(new ValueEventListener() {
                    //     addListenerForSingleValueEvent
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean isLoginValid = false;
                        String Reason = " ";
                        String Address = " ";
                        String Return = " ";
                        String Leave = " ";
                        String Pname = " ";
                        String Pph = " ";
                        String Status = " ";

                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            String savedName = userSnapshot.child("sname").getValue(String.class);
                            String savedHostel = userSnapshot.child("shostel").getValue(String.class);
                            String savedRoom = userSnapshot.child("sroom").getValue(String.class);
                            if (savedName != null && savedHostel != null && savedRoom != null
                                    && savedName.equals(Name) && savedHostel.equals(Hostel) && savedRoom.equals(Room)) {
                                rootDB.child("NightOut").child(userSnapshot.getKey()).child("Status").setValue("Accept");


                                isLoginValid = true;

                                break;
                            }
                        }

                        if (!isLoginValid) {
                            Toast.makeText(View_WardenNight.this, " Content Not Present ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    public void onCancelled(DatabaseError error) {
                        Toast.makeText(View_WardenNight.this, "Database error occurred", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rootDB.child("NightOut").addValueEventListener(new ValueEventListener() {
                    //     addListenerForSingleValueEvent
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean isLoginValid = false;
                        String Reason = " ";
                        String Address = " ";
                        String Return = " ";
                        String Leave = " ";
                        String Pname = " ";
                        String Pph = " ";
                        String Status = " ";

                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            String savedName = userSnapshot.child("sname").getValue(String.class);
                            String savedHostel = userSnapshot.child("shostel").getValue(String.class);
                            String savedRoom = userSnapshot.child("sroom").getValue(String.class);
                            if (savedName != null && savedHostel != null && savedRoom != null
                                    && savedName.equals(Name) && savedHostel.equals(Hostel) && savedRoom.equals(Room)) {
                                rootDB.child("NightOut").child(userSnapshot.getKey()).child("Status").setValue("Reject");


                                isLoginValid = true;

                                break;
                            }
                        }

                        if (!isLoginValid) {
                            Toast.makeText(View_WardenNight.this, " Content Not Present ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    public void onCancelled(DatabaseError error) {
                        Toast.makeText(View_WardenNight.this, "Database error occurred", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), View_Student.class);
                in.putExtra("Choice","NightOut");
                startActivity(in);
                finish();

            }
        });




    }



}