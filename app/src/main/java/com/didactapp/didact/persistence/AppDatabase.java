package com.didactapp.didact.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.didactapp.didact.entities.Book;


/**
 * Created by roman on 06/03/2018.
 */

@Database(entities = {Book.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BookDao bookDao();
}