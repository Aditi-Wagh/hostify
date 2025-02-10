package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class View_Student extends AppCompatActivity {

    TextView sname,hostel,room;
    Button view_stud_profile;
    DatabaseReference rootDB;
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private MessAdapter mess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_stud_for_admin);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rootDB = FirebaseDatabase.getInstance().getReference();

        String choice = getIntent().getStringExtra("Choice");


        if(choice.equals("NightOut")) {
           ArrayList<Student> studentList = new ArrayList<>(); // Populate this list with student data

            studentAdapter = new StudentAdapter(this,studentList,"NightOut");
            recyclerView.setAdapter(studentAdapter);

            rootDB.child("NightOut").addValueEventListener(new ValueEventListener() {
                //     addListenerForSingleValueEvent
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    studentList.clear(); // Clear the list before adding new data
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {


                        String name = userSnapshot.child("sname").getValue(String.class);
                        String room = userSnapshot.child("sroom").getValue(String.class);
                        String hostel = userSnapshot.child("shostel").getValue(String.class);
                        studentList.add(new Student(name, room, hostel, null, null, null));
                    }
                    studentAdapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle errors
                }

            });

        }

        if(choice.equals("View_Mess")) {
            ArrayList<Mess> messList = new ArrayList<>(); // Populate this list with student data

            mess= new MessAdapter(this,messList,"Mess");
            recyclerView.setAdapter(mess);

            rootDB.child("Menu").addValueEventListener(new ValueEventListener() {
                //     addListenerForSingleValueEvent
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    messList.clear(); // Clear the list before adding new data
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {

                        String name = userSnapshot.child("BreakFast").getValue(String.class);
                        String room = userSnapshot.child("Lunch").getValue(String.class);
                        String hostel = userSnapshot.child("Dinner").getValue(String.class);
                        messList.add(new Mess(name, room, hostel));
                    }
                    mess.notifyDataSetChanged(); // Notify the adapter that the data has changed
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle errors
                }

            });

        }


        if(choice.equals("View_Student")) {
            ArrayList<Student> studentList = new ArrayList<>(); // Populate this list with student data

            studentAdapter = new StudentAdapter(this,studentList,"Student");
            recyclerView.setAdapter(studentAdapter);

            rootDB.child("Student").addValueEventListener(new ValueEventListener() {
                //     addListenerForSingleValueEvent
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    studentList.clear(); // Clear the list before adding new data
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {


                        String name = userSnapshot.child("sname").getValue(String.class);
                        String room = userSnapshot.child("room").getValue(String.class);
                        String hostel = userSnapshot.child("hostel").getValue(String.class);
                        studentList.add(new Student(name, room, hostel, null, null, null));
                    }
                    studentAdapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle errors
                }

            });

        }

        if(choice.equals("View_Employees")){

            ArrayList<Student> studentList = new ArrayList<>(); // Populate this list with student data

            studentAdapter = new StudentAdapter(this,studentList,"Employee");
            recyclerView.setAdapter(studentAdapter);

            rootDB.child("Employees").addValueEventListener(new ValueEventListener() {
                //     addListenerForSingleValueEvent
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    studentList.clear(); // Clear the list before adding new data
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {


                        String name = userSnapshot.child("ename").getValue(String.class);
                        String hostel = userSnapshot.child("Hostel").getValue(String.class);
                        String type = userSnapshot.child("Type").getValue(String.class);
                        studentList.add(new Student(null, null, null, name, hostel, type));
                    }
                    studentAdapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle errors
                }

            });

        }

        if(choice.equals("Leave")) {
            ArrayList<Student> studentList = new ArrayList<>(); // Populate this list with student data

            studentAdapter = new StudentAdapter(this,studentList,"Leave");
            recyclerView.setAdapter(studentAdapter);

            rootDB.child("Leave").addValueEventListener(new ValueEventListener() {
                //     addListenerForSingleValueEvent
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    studentList.clear(); // Clear the list before adding new data
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {


                        String name = userSnapshot.child("ename").getValue(String.class);
                        String hostel = userSnapshot.child("Hostel").getValue(String.class);
                        String type = userSnapshot.child("Type").getValue(String.class);
                        studentList.add(new Student(null, null, null, name, hostel, type));
                    }
                    studentAdapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle errors
                }

            });

        }

        if(choice.equals("Issue")) {
            ArrayList<Student> studentList = new ArrayList<>(); // Populate this list with student data

            studentAdapter = new StudentAdapter(this,studentList,"Issues");
            recyclerView.setAdapter(studentAdapter);

            rootDB.child("Issues").addValueEventListener(new ValueEventListener() {
                //     addListenerForSingleValueEvent
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    studentList.clear(); // Clear the list before adding new data
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {

                        String name = userSnapshot.child("sname").getValue(String.class);
                        String room = userSnapshot.child("sroom").getValue(String.class);
                        String hostel = userSnapshot.child("shostel").getValue(String.class);

                        studentList.add(new Student(name, room, hostel, null, null, null));
                    }
                    studentAdapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle errors
                }

            });

        }

    }
}