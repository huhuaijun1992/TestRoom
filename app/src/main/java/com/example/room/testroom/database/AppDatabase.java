package com.example.room.testroom.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {UserEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
        private static AppDatabase INSTANCE;
    private static final Object sLock = new Object();

    public abstract UserDao userDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (sLock) {
                INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "user.db")
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return INSTANCE;
    }
}
