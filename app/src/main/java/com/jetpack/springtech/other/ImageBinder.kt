package com.jetpack.mvvm_rooom.other

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.jetpack.springtech.repositories.room.ContactsTable
import java.io.File

@BindingAdapter("imgPath")
fun bindImage(imageView: ImageView,imgPath:String){
    imageView.load(imgPath){
        transformations(RoundedCornersTransformation(20f))
    }
}
