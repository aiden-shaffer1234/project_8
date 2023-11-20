package com.zybooks.myapplication.repo;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import com.zybooks.myapplication.model.FileEntry;
import java.util.List;

public class FileRepo {
    private static FileRepo mFileRepo;
    private final FileDao mFileDao;

    public static FileRepo getInstance(Context context) {
        if (mFileRepo == null) {
            mFileRepo = new FileRepo(context);
        }
        return mFileRepo;
    }

    private FileRepo(Context context) {
        DataBase database = Room.databaseBuilder(context, DataBase.class, "files.db")
                .allowMainThreadQueries()
                .build();

        mFileDao = database.FileDao();
    }

    public void addFileEntry(FileEntry fileEntry) {
        long fileEntryId = mFileDao.addFileEntry(fileEntry);
        fileEntry.setEntryID(fileEntryId);
    }

    public FileEntry getFileEntry(long fileEntryId) {
        return mFileDao.getFileEntry(fileEntryId);
    }

    public List<FileEntry> getFileEntrys() {
        return mFileDao.getFileEntrys();
    }

    public boolean contains(String microID) { // problem with updating instance
        List<FileEntry> list = getFileEntrys();

        for (FileEntry file: list) {
            if(file.getID().equals(microID))
            {
                return true;
            }
        }
        return false;
    }

}
