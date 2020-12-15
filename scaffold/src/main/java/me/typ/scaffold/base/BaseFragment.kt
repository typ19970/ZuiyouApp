package me.typ.scaffold.base

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.kunminx.architecture.BaseApplication
import com.kunminx.architecture.ui.page.DataBindingFragment

/**
 * TODO tip 1: DataBinding 严格模式
 * 将 DataBinding 实例限制于 base 页面中，默认不向子类暴露，
 * 通过这样的方式，来彻底解决 视图调用的一致性问题，
 * 如此，视图调用的安全性将和基于函数式编程思想的 Jetpack Compose 持平。
 * 详解：https://xiaozhuanlan.com/topic/9816742350 和 https://xiaozhuanlan.com/topic/2356748910
 */
abstract class BaseFragment :DataBindingFragment() {

    protected var mAnimationLoaded = false

    private val mFragmentProvider by lazy { ViewModelProvider(this) }
    private val mActivityProvider by lazy { ViewModelProvider(mActivity) }
    private val mApplicationProvider by lazy {
        ViewModelProvider(
            mActivity.applicationContext as BaseApplication, getApplicationFactory(
                mActivity
            )
        )
    }

    override fun initViewModel() {}

    protected open fun <T : ViewModel> getFragmentScopeViewModel(modelClass: Class<T>): T {
        return mFragmentProvider[modelClass]
    }

    protected open fun <T : ViewModel> getActivityScopeViewModel(modelClass: Class<T>): T {
        return mActivityProvider[modelClass]
    }

    protected open fun <T : ViewModel> getApplicationScopeViewModel(modelClass: Class<T>): T {
        return mApplicationProvider[modelClass]
    }

    private fun getApplicationFactory(activity: Activity): ViewModelProvider.Factory {
        checkActivity(this)
        val application = checkApplication(activity)
        return ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }

    private fun checkApplication(activity: Activity): Application {
        return activity.application
            ?: throw IllegalStateException(
                "Your activity/fragment is not yet attached to "
                        + "Application. You can't request ViewModel before onCreate call."
            )
    }

    private fun checkActivity(fragment: Fragment) {
        fragment.activity
            ?: throw IllegalStateException("Can't create ViewModelProvider for detached fragment")
    }

    protected open fun nav(): NavController? {
        return NavHostFragment.findNavController(this)
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        //错开动画转场与 UI 刷新的时机，避免掉帧卡顿的现象
        Handler(Looper.getMainLooper()).postDelayed({
            if (!mAnimationLoaded) {
                mAnimationLoaded = true
                loadInitData()
            }
        }, 280)
        return super.onCreateAnimation(transit, enter, nextAnim)
    }

    protected open fun loadInitData() {}

    protected open fun openUrlInBrowser(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}