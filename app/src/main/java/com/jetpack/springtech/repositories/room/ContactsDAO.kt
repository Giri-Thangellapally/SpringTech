package com.jetpack.springtech.repositories.room

import androidx.room.*

@Dao
interface ContactsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(person: ContactsTable): Long

    @Query("SELECT * FROM contact_table ORDER BY id")
    fun getAllPersonsData(): List<ContactsTable>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateContact(contact: ContactsTable)

    @Delete
    fun delete(contact: ContactsTable)

}