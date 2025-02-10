package com.example.firebase;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import java.util.ArrayList;


public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private ArrayList<Student> studentList;
    String Choice;
    Context context;

    public StudentAdapter(Context context,ArrayList<Student> studentList, String Choice) {
        this.context = context;
        this.studentList = studentList;
        this.Choice = Choice;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view_admin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = studentList.get(position);

        if(Choice.equals("NightOut")){
            holder.snameTextView.setText(student.getName());
            holder.hostelTextView.setText(student.getHostel());
            holder.roomTextView.setText(student.getRoom());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // on below line we are calling an intent.
                    Intent i = new Intent(context, View_WardenNight.class);

                    // below we are passing all our values.
                    i.putExtra("choice","NightOut");
                    i.putExtra("name", student.getName());
                    i.putExtra("Hostel", student.getHostel());
                    i.putExtra("Room", student.getRoom());

                    // starting our activity.
                    context.startActivity(i);
                }
            });
            return;
        }

        else if(Choice.equals("Student")){
            holder.snameTextView.setText(student.getName());
            holder.hostelTextView.setText(student.getHostel());
            holder.roomTextView.setText(student.getRoom());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // on below line we are calling an intent.
                    Intent i = new Intent(context, Full_ViewProfile.class);

                    // below we are passing all our values.
                    i.putExtra("name", student.getName());
                    i.putExtra("Hostel", student.getHostel());
                    i.putExtra("Room", student.getRoom());

                    // starting our activity.
                    context.startActivity(i);
                }
            });

            return;
        }

        else if(Choice.equals("Issue")){
            holder.snameTextView.setText(student.getName());
            holder.hostelTextView.setText(student.getHostel());
            holder.roomTextView.setText(student.getRoom());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // on below line we are calling an intent.
                    Intent i = new Intent(context, View_Issue.class);

                    // below we are passing all our values.
                    i.putExtra("choice","Issue");
                    i.putExtra("name", student.getName());
                    i.putExtra("Hostel", student.getHostel());
                    i.putExtra("Room", student.getRoom());

                    // starting our activity.
                    context.startActivity(i);
                }
            });
            return;
        }

        else if(Choice.equals("Leave")){
            holder.snameTextView.setText(student.getEName());
            holder.hostelTextView.setText(student.geteHostel());
            holder.roomTextView.setText(student.geteType());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // on below line we are calling an intent.
                    Intent i = new Intent(context, View_Leave.class);

                    // below we are passing all our values.
                    i.putExtra("choice","Leave");
                    i.putExtra("name", student.getEName());
                    i.putExtra("Hostel", student.geteHostel());
                    i.putExtra("Type", student.geteType());

                    // starting our activity.
                    context.startActivity(i);
                }
            });
            return;
        }

        else if(Choice.equals("Employee")){
            holder.snameTextView.setText(student.getEName());
            holder.hostelTextView.setText(student.geteHostel());
            holder.roomTextView.setText(student.geteType());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // on below line we are calling an intent.
                    Intent i = new Intent(context, Full_EmpProfile.class);

                    // below we are passing all our values.
                    i.putExtra("name", student.getEName());
                    i.putExtra("Hostel", student.geteHostel());
                    i.putExtra("Type", student.geteType());

                    // starting our activity.
                    context.startActivity(i);
                }
            });
            return;
        }

        else{

            return;
        }
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView snameTextView, hostelTextView, roomTextView;
        Button view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            snameTextView = itemView.findViewById(R.id.studname);
            hostelTextView = itemView.findViewById(R.id.hostel);
            roomTextView = itemView.findViewById(R.id.Roomno);

        }
    }
}
