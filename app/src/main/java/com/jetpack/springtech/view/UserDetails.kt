package com.jetpack.springtech.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jetpack.springtech.R
import com.jetpack.springtech.databinding.ContactDetailsBinding
import com.jetpack.springtech.other.AppUtils.Companion.INTENT_CONTACTS_AVATAR
import com.jetpack.springtech.other.AppUtils.Companion.INTENT_CONTACTS_EMAIL
import com.jetpack.springtech.other.AppUtils.Companion.INTENT_CONTACTS_NAME
import com.jetpack.springtech.repositories.room.ContactsTable
import com.jetpack.springtech.viewmodel.ContactsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
class UserDetails : AppCompatActivity() {
    private val TAG = "UserDetails"
    private  val userDetailsViewModel by viewModel<ContactsViewModel>()
    private lateinit var userDetailsBinding: ContactDetailsBinding
    lateinit var details: ContactsTable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }
    private fun initViews() {
        //initialising the data binding
        userDetailsBinding = DataBindingUtil.setContentView(this, R.layout.contact_details)
        val avatar=intent.extras!!.get(INTENT_CONTACTS_AVATAR) as String
        val firstName=intent.extras!!.get(INTENT_CONTACTS_NAME) as String
        val email=intent.extras!!.get(INTENT_CONTACTS_EMAIL) as String

        details= ContactsTable(avatar = avatar,first_name = firstName,last_name = "",email = email)
        userDetailsBinding.contactDetails=details
        userDetailsBinding.apply {
            editBtn.setOnClickListener {
                userDetailsViewModel.editPersonsData(ContactsTable(avatar = avatar,first_name = username.text.toString(),last_name = "",email = userEmail.text.toString()))
                finish()
            }
            deleteBtn.setOnClickListener {
                userDetailsViewModel.deletePersonsData(details)
                finish()
            }

        }
    }

}