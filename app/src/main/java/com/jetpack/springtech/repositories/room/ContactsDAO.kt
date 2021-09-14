package com.jetpack.springtech.repositories.room

import androidx.room.*

@Dao
interface ContactsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(person: ContactsTable): Long

    @Query("SELECT * FROM contact_table ORDER BY id")
    suspend fun getAllPersonsData(): List<ContactsTable>

    @Update
   suspend fun updateContact(contact: ContactsTable)

    @Delete
   suspend fun delete(contact: ContactsTable)

}