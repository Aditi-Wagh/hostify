package com.example.firebase;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Add_New_Note extends AppCompatActivity {

    EditText titleEditText,contentEditText;
    ImageButton saveNoteBtn;
    ImageView bars;
    Button back;
    TextView pageTitleTextView;
    private RelativeLayout popupLayout;
    String title,content,docId;
    boolean isEditMode = false;
    TextView deleteNoteTextViewBtn;
    private DatabaseReference rootDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);

        titleEditText = findViewById(R.id.notes_title_text);
        contentEditText = findViewById(R.id.notes_content_text);
        saveNoteBtn = findViewById(R.id.save_note_btn);
        bars = findViewById(R.id.three_bars);
        back = findViewById(R.id.back);
        popupLayout = findViewById(R.id.popupLayout);
        pageTitleTextView = findViewById(R.id.page_title);
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


        saveNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = String.valueOf(titleEditText.getText());
                String content = String.valueOf(contentEditText.getText());

                long currentTimeMillis = System.currentTimeMillis();

                // Create a Date object from the current timestamp
                Date currentDate = new Date(currentTimeMillis);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


                String timestamp = dateFormat.format(currentDate);
                String noticekey = rootDB.child("Notices").push().getKey();
                DatabaseReference noticeRef = rootDB.child("Notices").child(noticekey);

                noticeRef.child("Title").setValue(title);
                noticeRef.child("Content").setValue(content);
                noticeRef.child("Timestamp").setValue(timestamp);


                // Sign in success, update UI with the signed-in user's information
                Toast.makeText(Add_New_Note.this, "New Notice Added",
                        Toast.LENGTH_SHORT).show();

                Intent in = new Intent(getApplicationContext(), NoticeBoard.class);

                startActivity(in);
                finish();

            }
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());

            }
        });

    }
}