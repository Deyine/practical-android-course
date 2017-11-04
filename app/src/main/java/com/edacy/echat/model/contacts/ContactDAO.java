package com.edacy.echat.model.contacts;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by djiddou on 11/4/17.
 */
@Dao
public interface ContactDAO {

    @Query("SELECT * FROM contact")
    List<Contact> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertAll(List<Contact> contacts);
}
