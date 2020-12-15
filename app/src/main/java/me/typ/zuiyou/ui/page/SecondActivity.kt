package me.typ.zuiyou.ui.page

import android.os.Bundle
import android.util.Log
import com.kunminx.architecture.ui.page.DataBindingConfig
import me.typ.lib_navannotation.ActivityDestination
import me.typ.scaffold.base.BaseActivity
import me.typ.zuiyou.BR
import me.typ.zuiyou.R
import me.typ.zuiyou.ui.adapter.SampleAdapter
import me.typ.zuiyou.ui.state.SecondViewModel

@ActivityDestination(pageUrl = "main/tabs/publish")
class SecondActivity : BaseActivity() {

    private val mState by lazy { getActivityScopeViewModel(SecondViewModel::class.java) }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_second, BR.vm, mState)
            .addBindingParam(BR.adapter, SampleAdapter(this))

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mState.sampleRequest.getData().observe(this) {
            mState.list.value = it
        }
        mState.sampleRequest.requestData()

        Log.e("TAG", "onCreate: SecondActivity")
    }


}