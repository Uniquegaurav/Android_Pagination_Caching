package com.example.manpaginationroomrecycler.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.manpaginationroomrecycler.data.Converters
import com.example.manpaginationroomrecycler.domain.model.Item


@Database(
    entities = [Item::class], version = 1
)
@TypeConverters(Converters::class)
abstract class ResultDatabase : RoomDatabase() {

    abstract fun getResultDao(): ResultDao

    companion object {

        private var instance: ResultDatabase? = null

        operator fun invoke(context: Context) = instance ?: synchronized(Any()) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                ResultDatabase::class.java,
                "item_db.db"
            ).build()
        }
    }
}