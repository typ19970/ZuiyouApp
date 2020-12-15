package me.typ.zuiyou.util

import android.annotation.SuppressLint
import android.app.Application
import java.lang.reflect.InvocationTargetException

/**
 * 反射 ActivityThread#currentApplication 方法全局获取Application
 */
object AppGlobals {
    private var sApplication: Application? = null

    @JvmStatic
    @SuppressLint("PrivateApi")
    fun getApplication(): Application {
        if (sApplication == null) {
            try {
                sApplication = Class.forName("android.app.ActivityThread")
                    .getMethod("currentApplication")
                    .invoke(null) as Application
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            }
        }
        return sApplication!!
    }
}
