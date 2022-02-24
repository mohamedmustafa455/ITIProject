package com.example.itiproject.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class entityNote {
    @PrimaryKey(autoGenerate = true)
    int id;
    public String title;
    public String body;
    public String date;


    public entityNote(int id, String title, String body, String date) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.date = date;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
