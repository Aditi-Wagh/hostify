package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class add_issue extends AppCompatActivity {

    EditText issue;
    ImageView bars;
    RelativeLayout popupLayout;
    Button back;
    Button post;
    private DatabaseReference rootDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_issue);

        // Initialize the Firebase Database reference
        rootDB = FirebaseDatabase.getInstance().getReference();

        // Get user email and password from the previous activity
        String Email = getIntent().getStringExtra("Email");
        String Password = getIntent().getStringExtra("Password");

        // Initialize UI elements
        issue = findViewById(R.id.issue_text);
        popupLayout = findViewById(R.id.popupLayout);
        bars = findViewById(R.id.three_bars);
        back = findViewById(R.id.back);
        post = findViewById(R.id.postbutton);

        // Set an onClickListener for the "Bars" icon to show/hide the popupLayout
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

        // Set an onClickListener for the "Back" button to navigate back to the dashboard
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Student_dashboard.class);
                i.putExtra("Email", Email);
                i.putExtra("Password", Password);
                startActivity(i);
                finish();
            }
        });

        // Set an onClickListener for the "Post" button to add the issue to the Firebase Database
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Issue = issue.getText().toString();

                // Query the Firebase Database for user information
                rootDB.child("Student").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean isLoginValid = false;
                        String Name = "";
                        String Room = "";
                        String Hostel = "";

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
                            // Generate a unique key for the issue
                            String issueKey = rootDB.child("Issues").push().getKey();
                            DatabaseReference issueRef = rootDB.child("Issues").child(issueKey);

                            issueRef.child("sname").setValue(Name);
                            issueRef.child("sroom").setValue(Room);
                            issueRef.child("shostel").setValue(Hostel);
                            issueRef.child("sissue").setValue(Issue);

                            // Show a success message
                            Toast.makeText(add_issue.this, "Complaint Registered.", Toast.LENGTH_SHORT).show();

                            // Navigate back to the dashboard
                            Intent in = new Intent(getApplicationContext(), Student_dashboard.class);
                            in.putExtra("Email", Email);
                            in.putExtra("Password", Password);
                            startActivity(in);
                            finish();
                        } else {
                            Toast.makeText(add_issue.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Handle database error
                        Toast.makeText(add_issue.this, "Database error occurred", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
