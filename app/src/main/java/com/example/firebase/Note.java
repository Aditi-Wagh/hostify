package com.example.firebase;

public class Note {
    String title;
    String content;
    // Get the current timestamp in milliseconds
    String timestamp;

    public Note(String title,String content,String timestamp) {
        this.title=title;
        this.content=content;
        this.timestamp=timestamp;

    }

    public String getTitle() {
        return title;
    }



    public String getContent() {
        return content;
    }

    public String getTimestamp() {
        return timestamp;
    }

}
