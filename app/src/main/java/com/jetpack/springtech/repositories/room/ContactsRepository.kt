package com.jetpack.springtech.repositories.room

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.jetpack.springtech.other.AppUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactsRepository (private val personDAO: ContactsDAO) {

    var allPersonsData = listOf<ContactsTable>()

    /*get the all persons data from the database*/
    suspend fun getAllPersonsData(): List<ContactsTable> {
        withContext(Dispatchers.IO) {
            allPersonsData = personDAO.getAllPersonsData()
        }
        return allPersonsData
    }

     suspend fun getAllPersonsDataFromAssets(context: Context, fileName:String):List<ContactsTable> ?{
         val contacts= AppUtils.getJsonDataFromAsset(context,fileName)
        val person=contacts?.iterator()
         person?.forEach {
             insertPersonData(it)
         }
        return contacts
    }

    /*insert the person data into the database*/
    suspend fun insertPersonData(person: ContactsTable) {
        withContext(Dispatchers.IO) {
            personDAO.insertData(person)
        }
    }

    suspend fun editPersonsData(person: ContactsTable) {
        withContext(Dispatchers.IO) {
            personDAO.updateContact(person)
        }
    }
    suspend fun deletePersonsData(person: ContactsTable) {
        withContext(Dispatchers.IO) {
            personDAO.updateContact(person)
        }
    }

}