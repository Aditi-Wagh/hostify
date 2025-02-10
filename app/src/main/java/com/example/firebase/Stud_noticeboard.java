package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Stud_noticeboard extends AppCompatActivity {

    DatabaseReference rootDB;
    RecyclerView rv;
    private NoteAdapter noteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_noticeboard);

        rv = findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rootDB = FirebaseDatabase.getInstance().getReference();
        List<Note> noticeList = new ArrayList<>();

        noteAdapter = new NoteAdapter(noticeList);
        rv.setAdapter(noteAdapter);

        rootDB.child("Notices").addValueEventListener(new ValueEventListener() {
            //     addListenerForSingleValueEvent
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                noticeList.clear(); // Clear the list before adding new data
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {


                    String title = userSnapshot.child("Title").getValue(String.class);

                    String content = userSnapshot.child("Content").getValue(String.class);
                    String timestamp = userSnapshot.child("Timestamp").getValue(String.class);
                    noticeList.add(new Note(title, content, timestamp));
                }
                noteAdapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }

        });

    }
}