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

public class Leave_Apply extends AppCompatActivity {

    Button leave_date, return_date,submit;
    EditText sname,hostel,reason,type;
    private DatabaseReference rootDB;
    Button back;
    ImageView bars;
    private RelativeLayout popupLayout;
    private TextView leavetext, returntext;
    String Name,Hostel,Type;
    Calendar mycal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_apply);

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

                Intent i = new Intent(getApplicationContext(),Warden_dashboard.class);
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
        Hostel = " ";
        Type = " ";


        sname = findViewById(R.id.nam);
        hostel = findViewById(R.id.hos);
        type = findViewById(R.id.emptype);
        reason = findViewById(R.id.reason);

        rootDB = FirebaseDatabase.getInstance().getReference();
        submit = findViewById(R.id.addbutton2);

        String Email = getIntent().getStringExtra("Email");
        String Password = getIntent().getStringExtra("Password");

        rootDB.child("Employees").addValueEventListener(new ValueEventListener() {
            //     addListenerForSingleValueEvent
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean isLoginValid = false;

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String savedEmail = userSnapshot.child("eemail").getValue(String.class);
                    String savedPassword = userSnapshot.child("epass").getValue(String.class);
                    if (savedEmail != null && savedPassword != null
                            && savedEmail.equals(Email) && savedPassword.equals(Password)) {

                        Name = userSnapshot.child("ename").getValue(String.class);
                        Hostel = userSnapshot.child("Hostel").getValue(String.class);
                        Type = userSnapshot.child("Type").getValue(String.class);

                        isLoginValid = true;
                        break;
                    }
                }

                if(isLoginValid){
                    sname.setText(Name);
                    hostel.setText(Hostel);
                    type.setText(Type);

                }
                else{
                    Toast.makeText(Leave_Apply.this, "Invalid Login", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            public void onCancelled(DatabaseError error) {
                Toast.makeText(Leave_Apply.this, "Database error occurred", Toast.LENGTH_SHORT).show();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Reason = String.valueOf(reason.getText());
                String leavetext1 = String.valueOf(leavetext.getText());
                String returntext1 = String.valueOf(returntext.getText());

                String nightkey = rootDB.child("Leave").push().getKey();
                DatabaseReference nightRef = rootDB.child("Leave").child(nightkey);

                nightRef.child("ename").setValue(Name);
                nightRef.child("Hostel").setValue(Hostel);
                nightRef.child("Type").setValue(Type);
                nightRef.child("Reason").setValue(Reason);
                nightRef.child("OUT").setValue(leavetext1);
                nightRef.child("IN").setValue(returntext1);

                ;

                // Sign in success, update UI with the signed-in user's information
                Toast.makeText(Leave_Apply.this, "Leave Form Submitted.",
                        Toast.LENGTH_SHORT).show();

                Intent in = new Intent(getApplicationContext(), Warden_dashboard.class);
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