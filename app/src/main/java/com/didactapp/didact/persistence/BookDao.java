package com.didactapp.didact.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.didactapp.didact.entities.Book;

import java.util.List;


/**
 * data access object
 */
@Dao
public interface BookDao {
    @Query("SELECT * FROM Book")
    List<Book> getAll();

    @Insert
    void insert(Book book);

}