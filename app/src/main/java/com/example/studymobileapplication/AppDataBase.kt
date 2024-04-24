package com.example.studymobileapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.studymobileapplication.AccountsDao
import com.example.studymobileapplication.AccountsData
import com.example.studymobileapplication.OperationsDao
import com.example.studymobileapplication.OperationsData

@Database(entities = [OperationsData::class, AccountsData::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase(){
    abstract fun operationDao() : OperationsDao
    abstract fun accountDao() : AccountsDao


    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}