package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Add_Nightout extends AppCompatActivity {
    Button leave_date, return_date,submit;
    EditText sname,roomno,reason,address;
    private DatabaseReference rootDB;
    private RelativeLayout popupLayout;
    ImageView bars;
    Button back;
    private TextView leavetext, returntext;
    String Name,Room,Hostel,Pname,Pph,Sph;
    Calendar mycal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nightout);
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

//------------------------------------------------------------------------------------------
        leave_date = findViewById(R.id.leave_date);
        return_date = findViewById(R.id.return_date);
        leavetext=findViewById(R.id.leave_text);
        returntext=findViewById(R.id.return_text);

        leave_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        return_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog2();
            }
        });
//------------------------------------------------------------------------------------------------

         Name = " ";
         Room = " ";
         Hostel = " ";
         Pname = " ";
         Pph = " ";
         Sph = " ";
        sname = findViewById(R.id.sname);
        roomno = findViewById(R.id.room);
        reason = findViewById(R.id.reason);
        address = findViewById(R.id.address);
        rootDB = FirebaseDatabase.getInstance().getReference();
        submit = findViewById(R.id.addbutton);


        rootDB.child("Student").addValueEventListener(new ValueEventListener() {
                                                          //     addListenerForSingleValueEvent
                                                          @Override
                                                          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                              boolean isLoginValid = false;

                                                              for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                                                  String savedEmail = userSnapshot.child("semail").getValue(String.class);
                                                                  String savedPassword = userSnapshot.child("spass").getValue(String.class);
                                                                  if (savedEmail != null && savedPassword != null
                                                                          && savedEmail.equals(Email) && savedPassword.equals(Password)) {

                                                                      Name = userSnapshot.child("sname").getValue(String.class);
                                                                      Room = userSnapshot.child("room").getValue(String.class);
                                                                      Hostel = userSnapshot.child("hostel").getValue(String.class);
                                                                      Pname = userSnapshot.child("pname").getValue(String.class);
                                                                      Pph = userSnapshot.child("pph").getValue(String.class);
                                                                      Sph = userSnapshot.child("sph").getValue(String.class);

                                                                      isLoginValid = true;
                                                                      break;
                                                                  }
                                                              }

                                                              if(isLoginValid){
                                                                  sname.setText(Name);
                                                                  roomno.setText(Room);

                                                              }
                                                              else{
                                                                  Toast.makeText(Add_Nightout.this, "Invalid Login", Toast.LENGTH_SHORT).show();
                                                                    return;
                                                              }
                                                          }
                                                          public void onCancelled(DatabaseError error) {
                                                              Toast.makeText(Add_Nightout.this, "Database error occurred", Toast.LENGTH_SHORT).show();
            }
                                                      });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Reason = String.valueOf(reason.getText());
                String Address = String.valueOf(address.getText());
                String leavetext1 = String.valueOf(leavetext.getText());
                String returntext1 = String.valueOf(returntext.getText());

                String nightkey = rootDB.child("NightOut").push().getKey();
                DatabaseReference nightRef = rootDB.child("NightOut").child(nightkey);

                nightRef.child("sname").setValue(Name);
                nightRef.child("sphone").setValue(Sph);
                nightRef.child("Pname").setValue(Pname);
                nightRef.child("Pph").setValue(Pph);
                nightRef.child("sroom").setValue(Room);
                nightRef.child("shostel").setValue(Hostel);
                nightRef.child("reason").setValue(Reason);
                nightRef.child("address").setValue(Address);
                nightRef.child("LeaveDate").setValue(leavetext1);
                nightRef.child("ReturnDate").setValue(returntext1);
                nightRef.child("Status").setValue("Pending");
                ;

                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Add_Nightout.this, "Night Out Form Submitted.",
                                    Toast.LENGTH_SHORT).show();

                            Intent in = new Intent(getApplicationContext(), Student_dashboard.class);
                            in.putExtra("Email",Email);
                            in.putExtra("Password",Password);
                            startActivity(in);
                            finish();
                    }
                });
    }


    private void openDialog2() {

        DatePickerDialog dialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                returntext.setText(String.valueOf(year)+"."+String.valueOf(month +1)+"."+String.valueOf(dayOfMonth));
            }
        }, 2023, 0, 15);
        dialog.show();

    }

    private void openDialog() {

        DatePickerDialog dialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                leavetext.setText(String.valueOf(year)+"."+String.valueOf(month +1)+"."+String.valueOf(dayOfMonth));
            }
        }, 2023, 0, 15);
        dialog.show();

    }
}