package com.jetpack.springtech.viewmodel

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.springtech.other.AppUtils
import com.jetpack.springtech.repositories.room.ContactsRepository
import com.jetpack.springtech.repositories.room.ContactsTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactsViewModel(context: Context, private val contactsRepository: ContactsRepository):ViewModel() {

    var contactsList = MutableLiveData<List<ContactsTable>>()

    init {
            getAllContactsData(context)
    }
    fun getAllContactsData(context :Context):MutableLiveData<List<ContactsTable>>{
        viewModelScope.launch {
            if (contactsRepository.getAllPersonsData().isNotEmpty())
                contactsList.postValue(contactsRepository.getAllPersonsData())
            else
                contactsList.postValue(
                    contactsRepository.getAllPersonsDataFromAssets(
                        context,
                        AppUtils.FILE_NAME
                    )
                )
        }
        return contactsList
    }
     fun editPersonsData(person:ContactsTable){
         viewModelScope.launch(Dispatchers.IO) {
             contactsRepository.editPersonsData(person)
         }
    }
    fun deletePersonsData(person:ContactsTable){
         viewModelScope.launch(Dispatchers.IO) {
             contactsRepository.deletePersonsData(person)
         }
    }

}