package com.jetpack.springtech.repositories.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jetpack.mvvm_rooom.other.ImageConverter

@Database(entities = [ContactsTable::class], version = 1, exportSchema = false)
@TypeConverters(ImageConverter::class)
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun contactsDao(): ContactsDAO

}
