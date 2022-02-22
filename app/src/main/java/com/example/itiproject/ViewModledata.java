package com.example.itiproject;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.itiproject.database.NoteDao;
import com.example.itiproject.database.NoteDatabase;
import com.example.itiproject.database.entityNote;

import java.util.List;

public class ViewModledata extends AndroidViewModel {

    NoteDatabase dp;
    public NoteDao noteDao;

    public ViewModledata(@NonNull Application application) {
        super(application);
        dp=NoteDatabase.getInstanc(application);
        this.noteDao= dp.noteD();
    }

    public void setNote(entityNote note){
        dp.noteD().insertTask(note);
    }


    public List<entityNote> getAllNote(){
        return dp.noteD().loadAllToDo();
    }
//    //public List<String> getTitle(){
//        return  dp.noteD().gitTitle();
//    }

    public void deletNote(entityNote note){
        Thread thread4=new Thread(){
            public void run(){
                dp.noteD().delete(note);
            }
        };
        thread4.start();
        thread4.destroy();

    }
    public void updataNote(entityNote note){
        Thread thread5 =new Thread(){
            public void run(){
                dp.noteD().updateNote(note);
            }
        };thread5.start();
        thread5.stop();

     }





}
