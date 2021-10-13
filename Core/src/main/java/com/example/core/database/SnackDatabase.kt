package com.example.navigationcomponent.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coffee.model.CoffeeDao
import com.example.navigationcomponent.model.Donut

@Database(entities = arrayOf(Donut::class), version = 1)
abstract class SnackDatabase:RoomDatabase() {
    abstract fun donutDao(): DonutDao

    abstract fun coffeeDao(): CoffeeDao
    companion object {
        @Volatile private var INSTANCE: SnackDatabase? = null

        fun getDatabase(context: Context): SnackDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context,
                    SnackDatabase::class.java,
                    "snack_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}