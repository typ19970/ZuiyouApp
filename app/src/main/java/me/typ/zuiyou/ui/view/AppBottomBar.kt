package me.typ.zuiyou.ui.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.MenuItem
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import me.typ.zuiyou.R
import me.typ.zuiyou.data.config.AppConfig

class AppBottomBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationView(context, attrs, defStyleAttr) {

    private val inactiveIcons = intArrayOf(
        R.drawable.tab_home_selector,
        R.drawable.tab_channel_selector,
        R.drawable.ic_bot_tab_publish,
        R.drawable.tab_msg_selector,
        R.drawable.tab_me_selector
    )

    init {
        val config = AppConfig.getBottomBarConfig()
        val state = arrayOfNulls<IntArray>(2)
        state[0] = intArrayOf(android.R.attr.state_selected)
        state[1] = intArrayOf()
        val colors =
            intArrayOf(Color.parseColor(config.activeColor), Color.parseColor(config.inActiveColor))
        val stateList = ColorStateList(state, colors)
        itemTextColor = stateList
        itemIconTintList = stateList
        //选中监听

        //LABEL_VISIBILITY_LABELED:设置按钮的文本为一直显示模式
        //LABEL_VISIBILITY_AUTO:当按钮个数小于三个时一直显示，或者当按钮个数大于3个且小于5个时，被选中的那个按钮文本才会显示
        //LABEL_VISIBILITY_SELECTED：只有被选中的那个按钮的文本才会显示
        //LABEL_VISIBILITY_UNLABELED:所有的按钮文本都不显示
        labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED

        //设置图标
        for (tab in config.tabs) {
            if (!tab.enable) {
                continue
            }
            val itemId: Int = getItemId(tab.pageUrl)
            if (itemId < 0) {
                continue
            }
            val menuItem = menu.add(0, itemId, tab.index, tab.title)
            menuItem.setIcon(inactiveIcons[tab.index])
        }

        //设置大小
        var index = 0
        for (tab in config.tabs) {
            if (!tab.enable) {
                continue
            }
            val itemId: Int = getItemId(tab.pageUrl)
            if (itemId < 0) {
                continue
            }

            val iconSize = dp2Px(tab.size)
            val menuView = getChildAt(0) as BottomNavigationMenuView
            val itemView = menuView.getChildAt(index) as BottomNavigationItemView
            itemView.setIconSize(iconSize)
            itemView.setIconTintList(null)
            //此item（一般是中间那个 这里是发帖item）要突出显示
            if (tab.title.isEmpty()) {
                itemView.setShifting(false)//禁止掉点按时 上下浮动的效果
                /**
                 * 如果想要禁止掉所有按钮的点击浮动效果。
                 * 那么还需要给选中和未选中的按钮配置一样大小的字号。
                 *
                 *  在MainActivity布局的AppBottomBar标签增加如下配置，
                 *  @style/active，@style/inActive 在style.xml中
                 *  app:itemTextAppearanceActive="@style/active"
                 *  app:itemTextAppearanceInactive="@style/inActive"
                 */
            }
            index++
        }

        if (config.selectTab != 0) {
            val selectTab = config.tabs[config.selectTab]
            if (selectTab.enable) {
                //这里需要延迟一下 再定位到默认选中的tab
                //因为 咱们需要等待内容区域,也就NavGraphBuilder解析数据并初始化完成，
                //否则会出现 底部按钮切换过去了，但内容区域还没切换过去
                post { selectedItemId = getItemId(selectTab.pageUrl) }
            }
        }
    }

    private fun getItemId(pageUrl: String): Int {
        val destination = AppConfig.getDestConfig()[pageUrl] ?: return -1
        return destination.id
    }

    private fun dp2Px(dpValue: Int): Int {
        val metrics = context.resources.displayMetrics
        return (metrics.density * dpValue + 0.5f).toInt()
    }

}