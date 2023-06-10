package com.example.testportfolio.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CoinInfoDbModel::class], version = 10, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private var db: AppDatabase? = null
        private const val DB_NAME = "database"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {

            db?.let {
                return it
            }
            synchronized(LOCK) {
                db?.let {
                    return it
                }
                val instance =
                    Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun coinInfoDao(): CoinInfoDao
}