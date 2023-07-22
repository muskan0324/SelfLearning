package com.example.selflearning.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.selflearning.DateTypeConvertor
import com.example.selflearning.dao.ContactDAO
import com.example.selflearning.entity.Contact

@Database(entities = [Contact::class], version = 2)    //in entities u can mention which table are a part of this database  [Contact.class,Account.class,...]
@TypeConverters(DateTypeConvertor::class)
abstract class ContactDB:RoomDatabase() {
    abstract fun contactDAO(): ContactDAO
    companion object {
        private val migration_1_2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("Alter table contact add column isActive INTEGER NOT NULL DEFAULT(1)")
            }


        }
        @Volatile   //if the value of INSTANCE is set at any point ,it informs the other threads about it
        private var  INSTANCE:ContactDB?=null
        fun getInstance(context: Context):ContactDB {
                if (INSTANCE == null) {
                    Log.d("null","instance")
                    synchronized(this) {    // synchronizes ensures that multiple threads don't do the job of creating instance of "ContactDB"
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext, ContactDB::class.java,
                            "contactDB"
                        ).addMigrations(migration_1_2)
                            .build()
                    }
                }
                return INSTANCE!!
            }
        }
}