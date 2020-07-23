package com.adi_random.tracky.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adi_random.tracky.models.GoodreadsBook

/**
 * Created by Adrian Pascu on 23-Jul-20.
 */


class Database {

    @androidx.room.Database(entities = arrayOf(GoodreadsBook::class), version = 1)
    abstract class _Database : RoomDatabase() {
        abstract fun goodreadsBookDao(): GoodreadsBookDao
    }

    companion object {

        private lateinit var db: _Database;
        fun getInstance(ctx: Context): _Database {
            if (!this::db.isInitialized)
                db = Room.databaseBuilder(ctx, _Database::class.java, "app-db").build()
            return db
        }
    }

}