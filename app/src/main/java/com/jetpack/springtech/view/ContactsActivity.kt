package com.jetpack.springtech.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetpack.springtech.R
import com.jetpack.springtech.databinding.ContactsActivityBinding
import com.jetpack.springtech.other.AppUtils.Companion.INTENT_CONTACTS_AVATAR
import com.jetpack.springtech.other.AppUtils.Companion.INTENT_CONTACTS_EMAIL
import com.jetpack.springtech.other.AppUtils.Companion.INTENT_CONTACTS_ID
import com.jetpack.springtech.other.AppUtils.Companion.INTENT_CONTACTS_NAME
import com.jetpack.springtech.repositories.room.ContactsTable
import com.jetpack.springtech.view.adapters.PersonAdapter
import com.jetpack.springtech.viewmodel.ContactsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsActivity : AppCompatActivity() {

    //Initialized the ViewModel from koin(lazy) dependency injection.
    private val viewModel by viewModel<ContactsViewModel>()

    //data binding to avoid the FindViewById.
    private lateinit var dataBinding: ContactsActivityBinding
     var list: List<ContactsTable>?=null

    //Persons Adapter
    lateinit var  personAdapter: PersonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         initViews()
    }

    override fun onResume() {
        super.onResume()
        initViewModelObserver()
    }

    private fun initViews() {
        //initialising the data binding
        dataBinding = DataBindingUtil.setContentView(this, R.layout.contacts_activity)
        dataBinding.apply {
            //initialise the recyclerview
            personAdapter= PersonAdapter(this@ContactsActivity,ArrayList()){

            }
            contactsRecyclerView.layoutManager = LinearLayoutManager(this@ContactsActivity)
            contactsRecyclerView.setHasFixedSize(true)
        }
    }
    private fun initViewModelObserver() {
        viewModel.getAllContactsData(this).observe(this, {
            personAdapter.setPersonsData(it)
            personAdapter= PersonAdapter(this,it,onItemClicked = {
                val intent = Intent(this, UserDetails::class.java).apply {
                    putExtra(INTENT_CONTACTS_ID, it.id)
                    putExtra(INTENT_CONTACTS_AVATAR, it.avatar)
                    putExtra(INTENT_CONTACTS_NAME, it.first_name)
                    putExtra(INTENT_CONTACTS_EMAIL, it.email)
                }
                startActivity(intent)
            }
            )
            dataBinding.contactsRecyclerView.adapter=personAdapter
        })
    }
}