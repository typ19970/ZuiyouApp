package me.typ.zuiyou.ui.page

import android.os.Bundle
import android.util.Log
import com.kunminx.architecture.ui.page.DataBindingConfig
import me.typ.lib_navannotation.FragmentDestination
import me.typ.scaffold.base.BaseFragment
import me.typ.zuiyou.BR
import me.typ.zuiyou.R
import me.typ.zuiyou.ui.adapter.HomeTabFragmentAdapter
import me.typ.zuiyou.ui.state.HomeViewModel

@FragmentDestination(pageUrl = "main/tabs/home", asStarter = true)
class HomeFragment : BaseFragment() {

    private val mState by lazy { getFragmentScopeViewModel(HomeViewModel::class.java) }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.home_fragment, BR.vm, mState)
            .addBindingParam(BR.click, ClickProxy())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", "onCreate: HomeFragment")

        mState.viewPagerAdapter.set(HomeTabFragmentAdapter(childFragmentManager, lifecycle))
    }

    class ClickProxy {
        fun search() {}
    }

}