package com.xfg.simple_build.utils

import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide


fun ImageView.setImage(activity: FragmentActivity, image: String, placeholder: Int = 0) {
    Glide.with(activity).asBitmap().dontAnimate().placeholder(placeholder).load(image).into(this)
}