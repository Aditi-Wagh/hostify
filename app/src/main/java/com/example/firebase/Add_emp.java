package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.provider.Telephony;
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

public class Add_emp extends AppCompatActivity {

    EditText ename,eph,eemail,epass,addr,adhar,type,hostel;
    Button add;
    FirebaseAuth mAuth;
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    private RelativeLayout popupLayout;
    ImageView bars;
    Button back;
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
        setContentView(R.layout.activity_add_emp);

        bars = findViewById(R.id.three_bars);
        back = findViewById(R.id.back);
        mAuth = FirebaseAuth.getInstance();
        ename = findViewById(R.id.ename);
        eph   = findViewById(R.id.ephone);
        eemail  = findViewById(R.id.eemail);
        epass   = findViewById(R.id.pass);
        addr   = findViewById(R.id.addr);
        adhar   = findViewById(R.id.adhar);
        type  = findViewById(R.id.type);
        hostel   = findViewById(R.id.hostelno);popupLayout = findViewById(R.id.popupLayout);
        add =   findViewById(R.id.addbutton);
        rootDB = FirebaseDatabase.getInstance().getReference();

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
                String Ename,Eph,Eemail,Epass,Address,Aadhaar,HostelNo,Type;
                Ename = String.valueOf(ename.getText());
                Eph = String.valueOf(eph.getText());
                Eemail = String.valueOf(eemail.getText());
                Epass = String.valueOf(epass.getText());
                Address = String.valueOf(addr.getText());
                Aadhaar = String.valueOf(adhar.getText());
                Type= String.valueOf(type.getText());
                HostelNo = String.valueOf(hostel.getText());


                if(TextUtils.isEmpty(Ename) || TextUtils.isEmpty(Eph)|| TextUtils.isEmpty(Eemail) || TextUtils.isEmpty(Epass)|| TextUtils.isEmpty(Address) || TextUtils.isEmpty(Aadhaar)|| TextUtils.isEmpty(Type) || TextUtils.isEmpty(HostelNo)){
                    Toast.makeText(Add_emp.this,"Alert : Fill All Details !! ",Toast.LENGTH_LONG).show();
                    return;
                }


                String empkey = rootDB.child("Employees").push().getKey();
                DatabaseReference empRef = rootDB.child("Employees").child(empkey);

                empRef.child("ename").setValue(Ename);
                empRef.child("ephone").setValue(Eph);
                empRef.child("eemail").setValue(Eemail);
                empRef.child("epass").setValue(Epass);
                empRef.child("address").setValue(Address);
                empRef.child("aadhar").setValue(Aadhaar);
                empRef.child("Hostel").setValue(HostelNo);
                empRef.child("Type").setValue(Type);

                Toast.makeText(Add_emp.this,"Employee Added .",Toast.LENGTH_LONG).show();

                if(Type.equals("Warden")){
                    Toast.makeText(Add_emp.this,"Entered if  ",Toast.LENGTH_LONG).show();

                    String emp = "Login_Warden";
                    String login =rootDB.child(emp).push().getKey();
                    DatabaseReference log_w = rootDB.child(emp).child(login);

                    log_w.child("eemail").setValue(Eemail);
                    log_w.child("epass").setValue(Epass);
                    Toast.makeText(Add_emp.this,"Employee Account Created in "+emp+" .",Toast.LENGTH_LONG).show();
                    return;
                }

                if(Type.equals("Mess")){
                    String emp = "Login_Mess";
                    String login =rootDB.child(emp).push().getKey();
                    DatabaseReference log_w = rootDB.child(emp).child(login);

                    log_w.child("eemail").setValue(Eemail);
                    log_w.child("epass").setValue(Epass);
                    Toast.makeText(Add_emp.this,"Employee Account Created in "+emp+" .",Toast.LENGTH_LONG).show();
                    return;
                }

                if(Type.equals("Security")){
                    String emp = "Login_Security";
                    String login =rootDB.child(emp).push().getKey();
                    DatabaseReference log_w = rootDB.child(emp).child(login);

                    log_w.child("eemail").setValue(Eemail);
                    log_w.child("epass").setValue(Epass);
                    Toast.makeText(Add_emp.this,"Employee Account Created in "+emp+" .",Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(Add_emp.this,"Employee Account Created .",Toast.LENGTH_LONG).show();

            }
        });


    }
}