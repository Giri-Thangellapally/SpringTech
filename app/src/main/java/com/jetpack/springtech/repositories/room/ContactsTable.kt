package com.jetpack.springtech.repositories.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "contact_table")
data class ContactsTable(
    @PrimaryKey
    var id: Int,
    @ColumnInfo(name = "avatar")
    var avatar: String,
    @ColumnInfo(name = "first_name")
    var first_name: String,
    @ColumnInfo(name = "last_name")
    var last_name: String,
    @ColumnInfo(name = "email")
    val email: String = ""

)
