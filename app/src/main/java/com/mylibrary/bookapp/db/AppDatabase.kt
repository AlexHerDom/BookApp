package com.mylibrary.bookapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mylibrary.bookapp.db.tables.EventEntity

@Database(entities = [EventEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getDAO(): AppDao

    companion object {
        private var DBINSTANCE: AppDatabase? = null

        fun getAppDB(context: Context): AppDatabase {
            if (DBINSTANCE == null) {
                DBINSTANCE = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "BOOKDB"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return DBINSTANCE!!
        }
    }
}