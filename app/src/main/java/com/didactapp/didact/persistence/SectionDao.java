package com.didactapp.didact.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.didactapp.didact.entities.Section;

import java.util.List;


/**
 * data access object
 */
@Dao
public interface SectionDao {
    @Query("SELECT * FROM Section")
    List<Section> getAll();

    @Insert
    void insert(Section section);

    @Delete
    void delete(Section section);

}