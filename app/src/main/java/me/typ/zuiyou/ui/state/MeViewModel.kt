package me.typ.zuiyou.ui.state

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class MeViewModel:ViewModel() {
    val text = ObservableField<String>()

    init {
        text.set("Me")
    }
}