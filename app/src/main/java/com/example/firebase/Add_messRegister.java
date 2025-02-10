package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Add_messRegister extends AppCompatActivity {
    //AutoCompleteTextView slot;
    Button regButton;
    ImageView bars;
    private RelativeLayout popupLayout;
    Button back;
    private DatabaseReference rootDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mess_register);

        //slot = findViewById(R.id.auto_text2);
        rootDB = FirebaseDatabase.getInstance().getReference();
        regButton = findViewById(R.id.regButton);
        bars = findViewById(R.id.three_bars);
        back = findViewById(R.id.back);
        popupLayout = findViewById(R.id.popupLayout);
        String Email = getIntent().getStringExtra("Email");
        String Password = getIntent().getStringExtra("Password");


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

                Intent i = new Intent(getApplicationContext(),Student_dashboard.class);
                startActivity(i);
                finish();

            }
        });


        String[] messnums={"Mess 1", "Mess 2", "Mess 3", "Mess 4"};

        AutoCompleteTextView autoCompleteTextView;
        ArrayAdapter<String> adapterItems;


            autoCompleteTextView=findViewById(R.id.auto_text2);
            adapterItems= new ArrayAdapter<String>(this,R.layout.list_mess, messnums);

            autoCompleteTextView.setAdapter(adapterItems);

            autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                    String item=adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(Add_messRegister.this, "Item: "+item, Toast.LENGTH_SHORT).show();

                }
            });


        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Mess = String.valueOf(autoCompleteTextView.getEditableText());


                rootDB.child("Student").addValueEventListener(new ValueEventListener() {
                    //     addListenerForSingleValueEvent
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean isLoginValid = false;
                        String Name =" ";
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

                                isLoginValid = true;
                                break;
                            }
                        }


                        if (isLoginValid) {

                            String mkey = rootDB.child("Mess").child(Mess).push().getKey();
                            DatabaseReference mRef = rootDB.child("Mess").child(Mess).child(mkey);

                            mRef.child("sname").setValue(Name);
                            mRef.child("sroom").setValue(Room);
                            mRef.child("shostel").setValue(Hostel);

                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Add_messRegister.this, "Slot Registered.",
                                    Toast.LENGTH_LONG).show();

                            Intent in = new Intent(getApplicationContext(), StudMess_dashboard.class);
                            in.putExtra("Email",Email);
                            in.putExtra("Password",Password);
                            startActivity(in);
                            finish();

                        } else {
                            Toast.makeText(Add_messRegister.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                        }

                    }

                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        //Log.w(TAG, "Failed to read value.", error.toException());

                        Toast.makeText(Add_messRegister.this, "Database error occurred", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }
}