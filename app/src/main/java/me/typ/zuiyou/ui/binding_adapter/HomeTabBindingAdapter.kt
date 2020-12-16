package me.typ.zuiyou.ui.binding_adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import me.typ.zuiyou.R
import me.typ.zuiyou.data.config.AppConfig

@BindingAdapter(value = ["initTabLayout"], requireAll = false)
fun initTabLayout(tabLayout: TabLayout) {
    val rootView = tabLayout.rootView

    val config = AppConfig.getHomeTabConfig()
    tabLayout.tabGravity = config.tabGravity
    val viewPager2 = rootView.findViewById<ViewPager2>(R.id.view_pager2)



}

/*private fun makeTabView(context: Context, position: Int): View {
    val tabView = TextView(context)
    val states = arrayOfNulls<IntArray>(2)
    states[0] = intArrayOf(android.R.attr.state_selected)
    states[1] = intArrayOf()

    val colors =
        intArrayOf(Color.parseColor(tabConfig.activeColor), Color.parseColor(tabConfig.normalColor))
    val stateList = ColorStateList(states, colors)
    tabView.setTextColor(stateList)
    tabView.setText(tabs.get(position).title)
    tabView.textSize = tabConfig.normalSize
    return tabView
}*/
