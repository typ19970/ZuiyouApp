package me.typ.zuiyou.ui.state

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    val text = ObservableField<String>()

    init {
        text.set("Home")
    }

}