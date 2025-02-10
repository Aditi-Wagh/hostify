package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import android.app.Application;
import com.google.firebase.FirebaseApp;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Student_Profile extends AppCompatActivity {

    Button back;
    ImageView bars;
    private RelativeLayout popupLayout;
    private static final int PICK_IMAGE_REQUEST = 1;
    EditText sname,sph,semail,pname,pemail,pph,sAdhar,sAddr,roomno,hostelno,gname,gph,gaddr;
    ImageView iv;
    Button upload,add;
    private Uri imageUri; // Stores the selected image URI
    private DatabaseReference rootDB;
    private  String studkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        FirebaseApp.initializeApp(this);

        String Email = getIntent().getStringExtra("Email");
        String Password = getIntent().getStringExtra("Password");
        Toast.makeText(Student_Profile.this,"Email ,"+Email+" and Pass , "+Password, Toast.LENGTH_LONG).show();

        gname = findViewById(R.id.Guard_name);
        gph = findViewById(R.id.Guard_Phone);
        gaddr = findViewById(R.id.Guard_Address);
        sname=findViewById(R.id.sname);
        sph=findViewById(R.id.sph);
        semail=findViewById(R.id.semail);
        pname=findViewById(R.id.pname);
        pph=findViewById(R.id.pph);
        sAdhar=findViewById(R.id.studAdhar);
        sAddr=findViewById(R.id.studAddr);
        roomno=findViewById(R.id.roomno);
        hostelno=findViewById(R.id.hostelno);
        pemail=findViewById(R.id.p_email);
        upload=findViewById(R.id.uploadbutton);
        add=findViewById(R.id.addbutton);
        iv=findViewById(R.id.iv1);
        rootDB = FirebaseDatabase.getInstance().getReference();


        bars = findViewById(R.id.three_bars);
        popupLayout = findViewById(R.id.popupLayout);
        back = findViewById(R.id.back);
        String sName,Room,Hostel,Pname,Pph,Sph,sEmail;

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

        rootDB.child("Student").addValueEventListener(new ValueEventListener() {
            //     addListenerForSingleValueEvent
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean isLoginValid = false;
                String SName="",Room="",Hostel="",Pname="",Pph="",Sph="",sEmail="";


                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String savedEmail = userSnapshot.child("semail").getValue(String.class);
                    String savedPassword = userSnapshot.child("spass").getValue(String.class);
                    if (savedEmail != null && savedPassword != null
                            && savedEmail.equals(Email) && savedPassword.equals(Password)) {

                        studkey=userSnapshot.getKey();
                        SName = userSnapshot.child("sname").getValue(String.class);
                        Room = userSnapshot.child("room").getValue(String.class);
                        Hostel = userSnapshot.child("hostel").getValue(String.class);
                        Pname = userSnapshot.child("pname").getValue(String.class);
                        Pph = userSnapshot.child("pph").getValue(String.class);
                        Sph = userSnapshot.child("sph").getValue(String.class);
                        sEmail=savedEmail;


                        isLoginValid = true;
                        break;
                    }
                }

                if(isLoginValid){
                    sname.setText(SName);
                    roomno.setText(Room);
                    hostelno.setText(Hostel);
                    pname.setText(Pname);
                    sph.setText(Sph);
                    pph.setText(Pph);
                    semail.setText(sEmail);



                }
                else{
                    Toast.makeText(Student_Profile.this, "Invalid Login", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            public void onCancelled(DatabaseError error) {
                Toast.makeText(Student_Profile.this, "Database error occurred", Toast.LENGTH_SHORT).show();
            }
        });


        iv.setOnClickListener(v -> openImageChooser());

        // Set a click listener for the "Upload Image" button
        upload.setOnClickListener(v -> uploadImage(sname.getText().toString(),rootDB));




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String Address = String.valueOf(sAddr.getText());
                String Adhaar = String.valueOf(sAdhar.getText());
                String Pemail = String.valueOf(pemail.getText());
                String Gname = String.valueOf(gname.getText());
                String Gph = String.valueOf(gph.getText());
                String Gaddr = String.valueOf(gaddr.getText());

                Map<String, Object> updatedData = new HashMap<>();
                updatedData.put("sAddress", Address);
                updatedData.put("sAdahaar", Adhaar);
                updatedData.put("pEmail", Pemail);
                updatedData.put("Gname", Gname);
                updatedData.put("Gph", Gph);
                updatedData.put("Gaddr", Gaddr);


                rootDB.child("Student").child(studkey).updateChildren(updatedData, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if (error == null) {
                            Toast.makeText(Student_Profile.this, "Student information updated.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Student_Profile.this, "Failed to update student information.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                // Sign in success, update UI with the signed-in user's information
                Toast.makeText(Student_Profile.this, "Night Out Form Submitted.",
                        Toast.LENGTH_SHORT).show();

                Intent in = new Intent(getApplicationContext(), Student_dashboard.class);
                in.putExtra("Email",Email);
                in.putExtra("Password",Password);
                startActivity(in);
                finish();
            }
        });

    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            iv.setImageURI(imageUri);
        }
    }
    private void uploadImage(String Name, DatabaseReference rootDB) {
        if (imageUri != null) {
            // Create a storage reference
            StorageReference storageRef = FirebaseStorage.getInstance().getReference();

// Create a reference to your image file
            StorageReference imageRef = storageRef.child(Name+"_profile_.jpg");

// Upload the image to Firebase Storage
            UploadTask uploadTask = imageRef.putFile(imageUri);

// Set up a listener to monitor the upload task

            uploadTask.addOnSuccessListener(taskSnapshot -> {
                // Image uploaded successfully
                // You can retrieve the download URL of the uploaded image
                imageRef.getDownloadUrl().addOnSuccessListener(downloadUri -> {
                    // Save this download URL to Firebase Realtime Database or use it as needed
                    String imageURL = downloadUri.toString();

                    Map<String, Object> updatedData = new HashMap<>();
                    updatedData.put("profile_img",imageURL);

                    rootDB.child("Student").child(studkey).updateChildren(updatedData, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            if (error == null) {
                                Toast.makeText(Student_Profile.this, "Student information updated.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Student_Profile.this, "Failed to update student information.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



                    // Now you can save this URL to your database or do other operations

                });
            }).addOnFailureListener(e -> {
                // Handle errors during the upload
            });



        } else {
            // Handle the case where no image is selected

        }
    }
}