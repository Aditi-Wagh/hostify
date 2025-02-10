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
public class MessAdapter extends RecyclerView.Adapter<MessAdapter.ViewHolder> {
    private ArrayList<Mess> studentList;
    String Choice;
    Context context;

    public MessAdapter(Context context,ArrayList<Mess> studentList, String Choice) {
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
    public void onBindViewHolder(@NonNull MessAdapter.ViewHolder holder, int position) {
        Mess student = studentList.get(position);

        if(Choice.equals("Mess")){
            holder.snameTextView.setText(student.getBreakFast());
            holder.hostelTextView.setText(student.getLunch());
            holder.roomTextView.setText(student.getDinner());

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
