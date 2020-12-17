package me.typ.zuiyou.ui.page

import android.os.Bundle
import com.kunminx.architecture.ui.page.DataBindingConfig
import me.typ.scaffold.base.BaseFragment
import me.typ.zuiyou.BR
import me.typ.zuiyou.R
import me.typ.zuiyou.ui.adapter.FeedListAdapter
import me.typ.zuiyou.ui.state.HomeViewModel

class FeedListFragment : BaseFragment() {

    val mState by lazy { getFragmentScopeViewModel(HomeViewModel::class.java) }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.feed_list_fragment, BR.vm, mState)
            .addBindingParam(BR.adapter, FeedListAdapter(mActivity))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //mState.mRequest.requestFeedList()
    }

}