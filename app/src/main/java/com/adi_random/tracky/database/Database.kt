package com.adi_random.tracky.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.adi_random.tracky.models.GoodreadsBook
import com.adi_random.tracky.models.GoodreadsBookTypeConverter

/**
 * Created by Adrian Pascu on 23-Jul-20.
 */


class Database {

    @androidx.room.Database(
        entities = arrayOf(GoodreadsBook::class),
        version = 5,
        exportSchema = false
    )
    @TypeConverters(GoodreadsBookTypeConverter::class)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun goodreadsBookDao(): GoodreadsBookDao
    }

    companion object {

        private lateinit var db: AppDatabase

        @JvmStatic
        fun getInstance(ctx: Context): AppDatabase {
            if (!this::db.isInitialized)
                db = Room.databaseBuilder(ctx, AppDatabase::class.java, "app-db")
                    .fallbackToDestructiveMigration().build()
            return db
        }
    }

}


