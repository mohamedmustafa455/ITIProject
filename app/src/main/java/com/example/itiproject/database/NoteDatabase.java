package com.example.itiproject.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = entityNote.class,version = 1,exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instanc;

    public  abstract NoteDao noteD();
    public static final ExecutorService executer= Executors.newFixedThreadPool(4);

    public static NoteDatabase getInstanc(Context cont){
        if(instanc==null){
            instanc= Room.databaseBuilder(cont.getApplicationContext()
                    ,NoteDatabase.class,"Note")
                    .fallbackToDestructiveMigration()//.addCallback(callback)
                    .build();
            //instanc.noteD().insertTask(new entityNote("ITI Project"," APP","22/2/2022","04:00"));
        }
        return instanc;
    }
//    private static final RoomDatabase.Callback callback=new RoomDatabase.Callback(){
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//        }
//    };

}
