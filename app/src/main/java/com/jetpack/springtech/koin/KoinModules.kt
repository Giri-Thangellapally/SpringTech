package com.jetpack.springtech.koin

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.jetpack.springtech.repositories.room.ContactsDAO
import com.jetpack.springtech.repositories.room.ContactsDatabase
import com.jetpack.springtech.repositories.room.ContactsRepository
import com.jetpack.springtech.view.adapters.PersonAdapter
import com.jetpack.springtech.viewmodel.ContactsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    fun provideContactsViewModel(
        context: Context,
        repository: ContactsRepository
    ): ContactsViewModel {
        return ContactsViewModel(context, repository)
    }
    viewModel {
        provideContactsViewModel(get(), get())
    }
}
val contactsRepositoryModule = module {
    fun provideRepository(personDAO: ContactsDAO): ContactsRepository {
        return ContactsRepository(personDAO)
    }
    single {
        provideRepository(get())
    }
    single {
        PersonAdapter()
    }
}
val databaseModule = module {
    fun provideDatabase(application: Application): ContactsDatabase {
        return Room.databaseBuilder(
            application,
            ContactsDatabase::class.java,
            "springlogic.database"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun personDao(database: ContactsDatabase): ContactsDAO {
        return database.contactsDao()
    }
    single { provideDatabase(androidApplication()) }
    single { personDao(get()) }
}



