package me.typ.zuiyou.ui.binding_adapter

import android.text.TextUtils
import android.util.Log
import android.view.MenuItem
import androidx.databinding.BindingAdapter
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import me.typ.zuiyou.data.config.AppConfig
import me.typ.zuiyou.ui.view.AppBottomBar

/**
 * java中要求BindingAdapter需要是 public static 类型
 * kotlin中直接写成顶层函数即可
 */
@BindingAdapter(value = ["navController"], requireAll = true)
fun init(appBottomBar: AppBottomBar, navController: NavController) {
    appBottomBar.setOnNavigationItemSelectedListener(object :
        BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
            val destConfig = AppConfig.getDestConfig()
            destConfig.values.forEach {
                if (it.needLogin) {
                    Log.e("TAG", "${it.pageUrl} need login")
                    return false
                }
            }
            navController.navigate(menuItem.itemId)
            return !TextUtils.isEmpty(menuItem.title)
        }
    })
}
