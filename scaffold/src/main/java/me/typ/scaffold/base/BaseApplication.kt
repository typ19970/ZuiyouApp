package me.typ.scaffold.base

import android.app.Activity
import android.os.Bundle
import com.kunminx.architecture.BaseApplication as ViewModelStoreOwnerApplication

open class BaseApplication : ViewModelStoreOwnerApplication() {

    override fun onCreate() {
        super.onCreate()



        registerActivityLifecycleCallbacks(object : SimpleActivityLifecycleCallback() {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

            }
        })
    }
}