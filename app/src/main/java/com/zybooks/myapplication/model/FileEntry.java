package com.zybooks.myapplication.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class FileEntry {
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "entryId")
    private long EntryID;
    @ColumnInfo(name = "id")
    private String ID;
    @ColumnInfo(name = "name")
    private String Name;
    @ColumnInfo(name = "gender")
    private String Gender;
    @ColumnInfo(name = "email")
    private String Email;
    @ColumnInfo(name = "password")
    private String Password;
    @ColumnInfo(name = "breed")
    private String Breed;
    @ColumnInfo(name = "neutered")
    private boolean Neutered;

    @Ignore
    public FileEntry(long EntryID, String ID, String Name,  String Gender,
                     String Email, String Password, String Breed,
                     boolean Neutered)
    {
        this.EntryID        = EntryID;
        this.ID             = ID;
        this.Name           = Name;
        this.Gender         = Gender;
        this.Email          = Email;
        this.Password       = Password;
        this.Breed          = Breed;
        this.Neutered       = Neutered;
    }
    public FileEntry(String ID, String Name,  String Gender,
                     String Email, String Password, String Breed,
                     boolean Neutered) {
        this.ID          = ID;
        this.Name        = Name;
        this.Gender      = Gender;
        this.Email       = Email;
        this.Password    = Password;
        this.Breed       = Breed;
        this.Neutered    = Neutered;
    }

    public long getEntryID() {
        return EntryID;
    }

    public void setEntryID(long EntryID) {
        this.EntryID  = EntryID;
    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getBreed() {
        return Breed;
    }

    public void setBreed(String Breed) {
        this.Breed = Breed;
    }

    public boolean getNeutered() {
        return Neutered;
    }

    public void setNeutered(boolean Neutered) {
        this.Neutered = Neutered;
    }
}
