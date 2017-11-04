package com.edacy.echat;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.edacy.echat.db.AppDatabase;

/**
 * Created by djiddou on 11/4/17.
 */

public class EdacyApplication extends Application {


    private static EdacyApplication instance;

    private AppDatabase db;


    /**
     * Constructor.
     */
    public EdacyApplication() {
        instance = this;
    }

    /**
     * Get application singleton instance.
     * @return
     */
    public static EdacyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        configureRoom();
    }

    /**
     * Configure database
     */
    private void configureRoom() {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, Config.DB_NAME)
                .build();
    }

    /**
     * Get the current db instance
     * @return
     */
    public AppDatabase getDb() {
        return db;
    }
}
