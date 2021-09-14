package com.jetpack.springtech.other

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.widget.ImageView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jetpack.springtech.repositories.room.ContactsTable
import java.io.IOException

class AppUtils {
    companion object {
        const val FILE_NAME="contacts.json"
        const val INTENT_CONTACTS_AVATAR="INTENT_CONTACTS_AVATAR"
        const val INTENT_CONTACTS_ID="INTENT_CONTACTS_ID"
        const val INTENT_CONTACTS_NAME="INTENT_CONTACTS_NAME"
        const val INTENT_CONTACTS_EMAIL="INTENT_CONTACTS_EMAIL"

        fun loadPic(imageView: ImageView, bitmap: Bitmap) {
            imageView.load(bitmap)
            {
                transformations(RoundedCornersTransformation(20f))
            }
        }
        fun getJsonDataFromAsset(context: Context, fileName: String): List<ContactsTable>? {
            var contactsList = arrayListOf<ContactsTable>()
            val jsonString: String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
                val listPersonType = object : TypeToken<ArrayList<ContactsTable>>() {}.type
                val gson = Gson()
                try {
                    contactsList = gson.fromJson(jsonString, listPersonType)
                }catch (e:Exception){
                    Log.e("ERROR",e.toString())
                }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return contactsList
        }
    }
}