package com.didactapp.didact.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.didactapp.didact.entities.Book;
import com.didactapp.didact.entities.Chapter;
import com.didactapp.didact.entities.Section;


/**
 * database ORM declaration class - define tables here
 */
@Database(entities = {Book.class, Chapter.class, Section.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    public abstract BookDao bookDao();

    public abstract ChapterDao chapterDao();

    public abstract SectionDao sectionDao();

}