package me.typ.zuiyou.ui.state

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class MessageViewModel:ViewModel() {
    val text = ObservableField<String>()

    init {
        text.set("Message")
    }
}