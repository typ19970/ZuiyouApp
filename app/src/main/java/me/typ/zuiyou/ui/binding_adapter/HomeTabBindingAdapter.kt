package me.typ.zuiyou.ui.binding_adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import me.typ.zuiyou.R
import me.typ.zuiyou.data.config.AppConfig
import me.typ.zuiyou.data.model.TabMo

@BindingAdapter(value = ["initWithViewPager2"], requireAll = true)
fun initTabLayout(
    tabLayout: TabLayout,
    adapter: FragmentStateAdapter
) {
    val rootView = tabLayout.rootView

    val tabs = AppConfig.getHomeEnableTabs()
    val viewPager2 = rootView.findViewById<ViewPager2>(R.id.view_pager2)

    viewPager2.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
    viewPager2.adapter = adapter
    viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            val tabCount = tabLayout.tabCount
            for (i in 0 until tabCount) {
                val tab = tabLayout.getTabAt(i)
                val customView = tab!!.customView as TextView?
                if (tab.position == position) {
                    customView!!.textSize = tabs[position].activeSize.toFloat()
                    customView.typeface = Typeface.DEFAULT_BOLD
                } else {
                    customView!!.textSize = tabs[position].normalSize.toFloat()
                    customView.typeface = Typeface.DEFAULT
                }
            }
        }
    })
    //切换到【推荐】tab
    viewPager2.post { viewPager2.setCurrentItem(2, false) }

    //tabLayout.setupWithViewPager(viewPager2,true)
    //viewPager2 就不能和再用TabLayout.setUpWithViewPager()了
    //取而代之的是TabLayoutMediator。我们可以在onConfigureTab()方法的回调里面 做tab标签的配置
    val mediator = TabLayoutMediator(tabLayout, viewPager2, true) { tab, position ->
        tab.customView = makeTabView(rootView.context, tabs[position])
    }
    /**
     * 本来想vp2与tablayout的初始化分开 但是会报错：
     * abLayoutMediator attached before ViewPager2 has an adapter
     */
    mediator.attach()
}

private fun makeTabView(context: Context, tab: TabMo): View {
    val tabView = TextView(context)
    val states = arrayOfNulls<IntArray>(2)
    states[0] = intArrayOf(android.R.attr.state_selected)
    states[1] = intArrayOf()
    val colors =
        intArrayOf(Color.parseColor(tab.activeColor), Color.parseColor(tab.normalColor))
    val stateList = ColorStateList(states, colors)
    tabView.setTextColor(stateList)
    tabView.text = tab.title
    tabView.textSize = tab.normalSize.toFloat()
    return tabView
}

