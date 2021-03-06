package com.didactapp.didact.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.didactapp.didact.entities.Chapter;

import java.util.List;


/**
 * data access object
 */
@Dao
public interface ChapterDao {
    @Query("SELECT * FROM Chapter")
    List<Chapter> getAll();

    @Insert
    void insert(Chapter chapter);

    @Delete
    void delete(Chapter chapter);

}