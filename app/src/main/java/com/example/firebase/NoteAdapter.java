package com.example.firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{

    private List<Note> noticeList;
    String Choice;
    Context context;

    public NoteAdapter(List<Note> noticeList) {

        this.noticeList = noticeList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = noticeList.get(position);


        holder.snameTextView.setText(note.getTitle());
        holder.hostelTextView.setText(note.getContent());
        holder.roomTextView.setText(note.getTimestamp());

    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView snameTextView, hostelTextView, roomTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            snameTextView = itemView.findViewById(R.id.title_tv);
            hostelTextView = itemView.findViewById(R.id.content_tv);
            roomTextView = itemView.findViewById(R.id.timestamp_tv);
        }
    }
}

