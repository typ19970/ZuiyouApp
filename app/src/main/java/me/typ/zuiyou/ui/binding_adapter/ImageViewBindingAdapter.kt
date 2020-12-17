package me.typ.zuiyou.ui.binding_adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import me.typ.zuiyou.R

@BindingAdapter(value = ["imageUrl"], requireAll = false)
fun setImageUrl(imageView: ImageView, url: String?) {
    imageView.load(url) {
        crossfade(true)
        placeholder(R.drawable.ic_placeholder)
    }
}