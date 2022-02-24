package com.example.itiproject.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import java.util.Observable;

import io.reactivex.Single;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTask(entityNote note);

//    @Insert(onConflict =OnConflictStrategy.IGNORE )
//  Comparable addNote(entityNote note);

    @Update
    void updateNote(entityNote note);

    @Delete
    void  delete(entityNote note);

    @Query("SELECT * FROM entityNote")
    LiveData<List<entityNote>> loadAllToDo() ;



}
