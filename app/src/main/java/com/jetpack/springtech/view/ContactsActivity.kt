package com.jetpack.springtech.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetpack.springtech.R
import com.jetpack.springtech.databinding.ContactsActivityBinding
import com.jetpack.springtech.view.adapters.PersonAdapter
import com.jetpack.springtech.viewmodel.ContactsViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsActivity : AppCompatActivity() {

    //Initialized the ViewModel from koin(lazy) dependency injection.
    private val viewModel by viewModel<ContactsViewModel>()

    //data binding to avoid the FindViewById.
    private lateinit var dataBinding: ContactsActivityBinding

    //Persons Adapter
    private val personAdapter: PersonAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         initViews()
        initViewModelObserver()
    }

    private fun initViews() {
        //initialising the data binding
        dataBinding = DataBindingUtil.setContentView(this, R.layout.contacts_activity)
        dataBinding.apply {
            //initialise the recyclerview
            contactsRecyclerView.layoutManager = LinearLayoutManager(this@ContactsActivity)
            contactsRecyclerView.setHasFixedSize(true)
            contactsRecyclerView.adapter = personAdapter
        }
    }
    private fun initViewModelObserver() {
        viewModel.contactsList.observe(this, {
            it?.let { it ->
                personAdapter.setPersonsData(it)
            }
        })
    }

}