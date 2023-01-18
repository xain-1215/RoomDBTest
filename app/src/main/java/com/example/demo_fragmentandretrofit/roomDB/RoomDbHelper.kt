package com.example.demo_fragmentandretrofit.roomDB

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.demo_fragmentandretrofit.roomDB.RoomEntity.Companion.TABLE_NAME


@Database(entities = [(RoomEntity::class)], version = 2)
abstract class RoomDbHelper : RoomDatabase() {

    companion object {
        @Volatile private var instance: RoomDbHelper? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            RoomDbHelper::class.java, "item-list.db")
            .addMigrations(MIGRATION_1_TO_2).build()

        val MIGRATION_1_TO_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

                val tableName = TABLE_NAME
                database.execSQL(
                    "ALTER TABLE $tableName ADD COLUMN 'tako' TEXT NOT NULL DEFAULT ''")
            }
        }
    }


    abstract fun roomDao(): RoomDao?
}