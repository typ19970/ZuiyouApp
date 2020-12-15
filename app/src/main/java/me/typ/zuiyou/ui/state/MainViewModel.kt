package me.typ.zuiyou.ui.state

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class MainViewModel : ViewModel() {

    val navController = ObservableField<NavController>()

    init {

    }

}