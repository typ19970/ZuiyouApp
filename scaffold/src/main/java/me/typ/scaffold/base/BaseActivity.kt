package me.typ.scaffold.base

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kunminx.architecture.BaseApplication
import com.kunminx.architecture.ui.page.DataBindingActivity
import com.zackratos.ultimatebarx.library.UltimateBarX
import com.zackratos.ultimatebarx.library.bean.BarConfig

/**
 * TODO tip 1: DataBinding 严格模式
 * 将 DataBinding 实例限制于 base 页面中，默认不向子类暴露，
 * 通过这样的方式，来彻底解决 视图调用的一致性问题，
 * 如此，视图调用的安全性将和基于函数式编程思想的 Jetpack Compose 持平。
 * 详解：https://xiaozhuanlan.com/topic/9816742350 和 https://xiaozhuanlan.com/topic/2356748910
 */
abstract class BaseActivity : DataBindingActivity() {

    private val mActivityProvider by lazy { ViewModelProvider(this) }
    private val mApplicationProvider by lazy {
        ViewModelProvider(
            this.applicationContext as BaseApplication, getAppFactory(
                this
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        configAppBar();
        super.onCreate(savedInstanceState)

    }

    override fun initViewModel() {}

    protected open fun configAppBar() {
        val config = BarConfig.newInstance()
            .color(Color.WHITE)
            .light(true)

        UltimateBarX.with(this).config(config).applyStatusBar()
        UltimateBarX.with(this).config(config).applyNavigationBar()
    }

    /**
     * TODO tip 2: Jetpack 通过 "工厂模式" 来实现 ViewModel 的作用域可控
     * 目前我们在项目中提供了 Application、Activity、Fragment 三个级别的作用域
     * 值得注意的是，通过不同作用域的 Provider 获得的 ViewModel 实例不是同一个
     * 所以如果 ViewModel 对状态信息的保留不符合预期，可以从这个角度出发去排查 是否眼前的 ViewModel 实例不是目标实例所致
     * 详解：https://xiaozhuanlan.com/topic/6257931840
     */
    protected open fun <T : ViewModel> getActivityScopeViewModel(modelClass: Class<T>): T {
        return mActivityProvider[modelClass]
    }

    protected open fun <T : ViewModel> getApplicationScopeViewModel(modelClass: Class<T>): T {
        return mApplicationProvider[modelClass]
    }

    private fun getAppFactory(activity: Activity): ViewModelProvider.Factory {
        val application: Application = checkApplication(activity)
        return ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }

    private fun checkApplication(activity: Activity): Application {
        return activity.application
            ?: throw IllegalStateException(
                "Your activity/fragment is not yet attached to "
                        + "Application. You can't request ViewModel before onCreate call."
            )
    }

    protected open fun openUrlInBrowser(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

}