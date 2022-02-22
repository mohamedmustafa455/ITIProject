package com.example.itiproject.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = entityNote.class,version = 19,exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instanc;

    public  abstract NoteDao noteD();

    public static NoteDatabase getInstanc(Context cont){
        if(instanc==null){
            instanc= Room.databaseBuilder(cont.getApplicationContext()
                    ,NoteDatabase.class,"Note")
                    .fallbackToDestructiveMigration()
                    .build();
            //instanc.noteD().insertTask(new entityNote("ITI Project"," APP","22/2/2022","04:00"));
        }
        return instanc;
    }

}
