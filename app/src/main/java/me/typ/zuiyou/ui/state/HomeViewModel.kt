package me.typ.zuiyou.ui.state

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeViewModel : ViewModel() {

    val viewPagerAdapter = ObservableField<FragmentStateAdapter>()


    init {

    }

}