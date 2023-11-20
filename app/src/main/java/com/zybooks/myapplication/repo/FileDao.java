package com.zybooks.myapplication.repo;

import java.util.List;
import androidx.room.*;
import com.zybooks.myapplication.model.FileEntry;
import androidx.lifecycle.LiveData;

@Dao
public interface FileDao {
    @Query("SELECT * FROM FileEntry WHERE id = :id")
    FileEntry getFileEntry(long id);

    @Query("SELECT * FROM FileEntry")
    List<FileEntry> getFileEntrys();

    @Insert(onConflict = OnConflictStrategy.ABORT) // watch for future problems about conflict
    long addFileEntry(FileEntry fileEntry);

    @Update
    void updateFileEntry(FileEntry fileEntry);
}
