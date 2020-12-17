package me.typ.zuiyou

import com.drake.net.NetConfig.logEnabled
import com.drake.net.cacheEnabled
import com.drake.net.initNet
import me.typ.scaffold.base.BaseApplication
import me.typ.zuiyou.util.GsonConvert

class App : BaseApplication() {
    override fun onCreate() {
        super.onCreate()

        initNet("http://123.56.232.18:8080/serverdemo") {
            converter(GsonConvert()) // 转换器
            cacheEnabled() // 开启缓存
            setLogRecord(BuildConfig.DEBUG) // 日志记录器
            logEnabled = BuildConfig.DEBUG // LogCat异常日志
        }

    }
}