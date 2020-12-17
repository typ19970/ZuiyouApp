package me.typ.zuiyou.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import me.typ.zuiyou.data.config.AppConfig
import me.typ.zuiyou.ui.page.MeFragment

class HomeTabFragmentAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int {
        return AppConfig.getHomeEnableTabs().size
    }

    override fun createFragment(position: Int): Fragment {
        return MeFragment()
    }

}