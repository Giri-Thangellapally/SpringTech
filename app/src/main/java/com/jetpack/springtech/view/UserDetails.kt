package com.jetpack.springtech.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.jetpack.springtech.R
import com.jetpack.springtech.databinding.ContactDetailsBinding
import com.jetpack.springtech.other.AppUtils.Companion.INTENT_CONTACTS
import com.jetpack.springtech.repositories.room.ContactsTable
import com.jetpack.springtech.view.adapters.PersonAdapter
import com.jetpack.springtech.viewmodel.ContactsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception

class UserDetails : AppCompatActivity() {
    private val TAG = "UserDetails"
    private  val userDetailsViewModel by viewModel<ContactsViewModel>()
    private lateinit var userDetailsBinding: ContactDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }
    private fun initViews() {
        //initialising the data binding
        userDetailsBinding = DataBindingUtil.setContentView(this, R.layout.contact_details)
        val contactDetails=intent.getSerializableExtra(INTENT_CONTACTS) as? ContactsTable
        userDetailsBinding.apply {
            contactDetails?.let {
                personImg.load(contactDetails.avatar)
                username.text=contactDetails.first_name
            }
        }
    }

}