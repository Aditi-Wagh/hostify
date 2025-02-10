package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_security extends AppCompatActivity {

    EditText email,pass;
    Button log;
    FirebaseAuth mAuth;
    private DatabaseReference rootDB;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent in = new Intent(getApplicationContext(),Security_dashboard.class);
            startActivity(in);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_security);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        log = findViewById(R.id.loginbutton);
        rootDB = FirebaseDatabase.getInstance().getReference();

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Email, Password;
                Email = String.valueOf(email.getText());
                Password = String.valueOf(pass.getText());

                if (TextUtils.isEmpty(Email) || TextUtils.isEmpty(Password)) {
                    Toast.makeText(Login_security.this, "Enter Email and Password ", Toast.LENGTH_LONG).show();
                    return;
                }


                rootDB.child("Login_Security").addValueEventListener(new ValueEventListener() {
                    //     addListenerForSingleValueEvent
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean isLoginValid = false;
                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            String savedEmail = userSnapshot.child("email").getValue(String.class);
                            String savedPassword = userSnapshot.child("password").getValue(String.class);
                            if (savedEmail != null && savedPassword != null
                                    && savedEmail.equals(Email) && savedPassword.equals(Password)) {
                                isLoginValid = true;
                                break;
                            }
                        }


                        if (isLoginValid) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Login_security.this, "Authentication Completed.",
                                    Toast.LENGTH_SHORT).show();

                            Intent in = new Intent(getApplicationContext(), Security_dashboard.class);
                            startActivity(in);
                            finish();




                        } else {
                            Toast.makeText(Login_security.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                        }

                    }

                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        //Log.w(TAG, "Failed to read value.", error.toException());

                        Toast.makeText(Login_security.this, "Database error occurred", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}