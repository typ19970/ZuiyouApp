package me.typ.zuiyou.ui.state

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class ChannelViewModel:ViewModel() {
    val text = ObservableField<String>()

    init {
        text.set("Channel")
    }
}