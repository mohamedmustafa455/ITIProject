package com.example.itiproject;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.itiproject.database.NoteDao;
import com.example.itiproject.database.NoteDatabase;
import com.example.itiproject.database.entityNote;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.subjects.ReplaySubject;

public class ViewModledata extends AndroidViewModel {

    NoteDatabase dp;
    public NoteDao noteDao;


    public ViewModledata(@NonNull Application application) {
        super(application);
        dp=NoteDatabase.getInstanc(application);
        this.noteDao= dp.noteD();
    }

    public void setNote(entityNote note){
        dp.executer.execute(()->{
            noteDao.insertTask(note);
        });
       // dp.noteD().insertTask(note);
    }

    public LiveData<List<entityNote>> getAllNote(){
        return dp.noteD().loadAllToDo();
    }


    public void deletNote(entityNote note){
        dp.executer.execute(()->{
            noteDao.delete(note);
        });
    }





//    public void deletNote(entityNote note){
//        Thread thread2=new Thread(){
//            public void run(){
//                dp.noteD().delete(note);
//            }
//        };
//        thread2.start();
//        thread2.destroy();
//
// }
//    public void  updataNote(entityNote note){
//
//
//        /**
//         * step1: navigate to create note page
//         * step2: pass current note to new note page ()
//         */
//
//        dp.executer.execute(()->{
//            noteDao.updateNote(note);
//        });
//    }
    public void updataNote(entityNote note){
        Thread thread3 =new Thread(){
            public void run(){
                dp.noteD().updateNote(note);
            }
        };thread3.start();


     }

}
