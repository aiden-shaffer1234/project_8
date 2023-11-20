package com.zybooks.myapplication.repo;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.zybooks.myapplication.model.FileEntry;


@Database(entities = FileEntry.class, version = 1)
public abstract class DataBase extends RoomDatabase {
    public abstract FileDao FileDao();
}
