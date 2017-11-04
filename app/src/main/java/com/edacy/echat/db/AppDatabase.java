package com.edacy.echat.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.edacy.echat.model.contacts.Contact;
import com.edacy.echat.model.contacts.ContactDAO;

@Database(entities = {Contact.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ContactDAO contactDao();
}