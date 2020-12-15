package me.typ.zuiyou.ui.page

import android.os.Bundle
import android.util.Log
import com.kunminx.architecture.ui.page.DataBindingConfig
import me.typ.lib_navannotation.FragmentDestination
import me.typ.scaffold.base.BaseFragment
import me.typ.zuiyou.BR
import me.typ.zuiyou.R
import me.typ.zuiyou.ui.state.ChannelViewModel

@FragmentDestination(pageUrl = "main/tabs/channel")
class ChannelFragment : BaseFragment() {

    private val mState by lazy { getFragmentScopeViewModel(ChannelViewModel::class.java) }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.channel_fragment, BR.vm, mState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v("TAG", "onCreate: ChannelFragment $this",)
    }
}