package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;

public class Add_Laundry extends AppCompatActivity {
    private TextView text;
    AutoCompleteTextView timing;
    private RelativeLayout popupLayout;
    ImageView bars;
    Button back;
    private Button date_button,reg;

    String[] timeIntervals = {
            "6:00-6:30", "6:30-7:00",
            "7:00-7:30", "7:30-8:00",
            "21:00-21:30", "21:30-22:00",
            "22:00-22:30", "22:30-23:00",
    };

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    private DatabaseReference rootDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_laundry);

        rootDB = FirebaseDatabase.getInstance().getReference();
        String Email = getIntent().getStringExtra("Email");
        String Password = getIntent().getStringExtra("Password");



//-----------------------------------------------------------------------------------------------------------------
        text = findViewById(R.id.date);
        date_button = findViewById(R.id.date_button);
        reg = findViewById(R.id.Register);
        timing = findViewById(R.id.auto_text);
        bars = findViewById(R.id.three_bars);
        back = findViewById(R.id.back);
        popupLayout = findViewById(R.id.popupLayout);

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
                i.putExtra("Email",Email);
                i.putExtra("Password",Password);
                startActivity(i);
                finish();

            }
        });

        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        autoCompleteTextView=findViewById(R.id.auto_text);
        adapterItems= new ArrayAdapter<String>(this,R.layout.list_mess, timeIntervals);

        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                String item=adapterView.getItemAtPosition(i).toString();
                Toast.makeText(Add_Laundry.this, "Item: "+item, Toast.LENGTH_SHORT).show();
            }
        });
//------------------------------------------------------------------------------------------------------------------

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Date = String.valueOf(text.getText());
                String Time = String.valueOf(timing.getText());

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

                            String lkey = rootDB.child("Laundry").push().getKey();
                            DatabaseReference lRef = rootDB.child("Laundry").child(lkey);

                            lRef.child("sname").setValue(Name);
                            lRef.child("sroom").setValue(Room);
                            lRef.child("shostel").setValue(Hostel);
                            lRef.child("Date").setValue(Date);
                            lRef.child("Time Slot").setValue(Time);



                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Add_Laundry.this, "Slot Registered.",
                                    Toast.LENGTH_LONG).show();

                            Intent in = new Intent(getApplicationContext(), Student_dashboard.class);
                            in.putExtra("Email",Email);
                            in.putExtra("Password",Password);
                            startActivity(in);
                            finish();

                        } else {
                            Toast.makeText(Add_Laundry.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                        }

                    }

                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        //Log.w(TAG, "Failed to read value.", error.toException());

                        Toast.makeText(Add_Laundry.this, "Database error occurred", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
    private void openDialog(){
        DatePickerDialog dialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                text.setText(String.valueOf(year)+"."+String.valueOf(month +1)+"."+String.valueOf(dayOfMonth));
            }
        }, 2023, 0, 15);
        dialog.show();
    }
}