package me.typ.zuiyou.ui.binding_adapter

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

//这样可以不用再xml中引入View、TextUtil等辅助判断的类
@BindingAdapter(value = ["isVisible"], requireAll = false)
fun setVisibility(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}