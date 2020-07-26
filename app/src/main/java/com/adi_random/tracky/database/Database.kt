package com.adi_random.tracky.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.adi_random.tracky.models.GoodreadsBook
import com.adi_random.tracky.ui.main.readingList.ReadingListTypeConverter

/**
 * Created by Adrian Pascu on 23-Jul-20.
 */


class Database {

    @androidx.room.Database(
        entities = arrayOf(GoodreadsBook::class),
        version = 4,
        exportSchema = false
    )
    @TypeConverters(ReadingListTypeConverter::class)
    abstract class _Database : RoomDatabase() {
        abstract fun goodreadsBookDao(): GoodreadsBookDao
    }

    companion object {

        private lateinit var db: _Database;

        @JvmStatic
        fun getInstance(ctx: Context): _Database {
            if (!this::db.isInitialized)
                db = Room.databaseBuilder(ctx, _Database::class.java, "app-db")
                    .fallbackToDestructiveMigration().build()
            return db
        }
    }

}


