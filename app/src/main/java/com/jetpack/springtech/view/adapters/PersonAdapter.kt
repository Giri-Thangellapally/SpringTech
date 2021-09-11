package com.jetpack.springtech.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jetpack.springtech.R
import com.jetpack.springtech.databinding.PersonRowBinding
import com.jetpack.springtech.repositories.room.ContactsTable



class PersonAdapter : RecyclerView.Adapter<PersonAdapter.MyViewHolder>() {

    lateinit var personRowBinding: PersonRowBinding

    private var personsList = mutableListOf<ContactsTable>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_row, parent, false)
        personRowBinding = DataBindingUtil.bind(view)!!
        return MyViewHolder(personRowBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(personsList.get(position))
    }

    override fun getItemCount(): Int {

        return personsList.size
    }

    fun setPersonsData(list: List<ContactsTable>) {
        personsList = list.toMutableList()
        notifyDataSetChanged()
    }


    inner class MyViewHolder(private val personRowBinding: PersonRowBinding) :
        RecyclerView.ViewHolder(personRowBinding.root) {
        fun bind(person: ContactsTable) {
            personRowBinding.person = person
            personRowBinding.executePendingBindings()
            personRowBinding.edit.setOnClickListener {


            }
        }

    }

}

