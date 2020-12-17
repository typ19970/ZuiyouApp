package me.typ.zuiyou.ui.page

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import androidx.navigation.fragment.NavHostFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zackratos.ultimatebarx.library.UltimateBarX
import me.typ.scaffold.base.BaseActivity
import me.typ.zuiyou.BR
import me.typ.zuiyou.R
import me.typ.zuiyou.ui.state.MainViewModel
import me.typ.zuiyou.util.NavGraphBuilder

class MainActivity : BaseActivity() {

    private val mState by lazy { getActivityScopeViewModel(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragment =supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val navController =NavHostFragment.findNavController(fragment!!)
        NavGraphBuilder.build(this, fragment.childFragmentManager, navController, fragment.id)

        mState.navController.set(navController)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_main, BR.vm, mState)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    inner class ClickProxy {
        fun change() {
            startActivity(Intent(this@MainActivity, SecondActivity::class.java))
        }
    }
}