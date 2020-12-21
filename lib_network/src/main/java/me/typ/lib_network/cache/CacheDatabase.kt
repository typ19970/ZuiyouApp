package me.typ.lib_network.cache

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.lang.reflect.InvocationTargetException

@Database(entities = [Cache::class], version = 1)
abstract class CacheDatabase : RoomDatabase() {

    companion object {
        private var database: CacheDatabase =
            Room.databaseBuilder(
                getApplication(),
                CacheDatabase::class.java, "zuiyou_cache"
            )
                .allowMainThreadQueries()
                .build()

        //不想引入整个基础模块 直接把方法copy过来
        private var application: Application? = null
        private fun getApplication(): Application {
            if (application == null) {
                try {
                    application = Class.forName("android.app.ActivityThread")
                        .getMethod("currentApplication")
                        .invoke(null, null as Array<Any?>?) as Application
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
            return application!!
        }

        fun get(): CacheDatabase {
            return database
        }
    }

    abstract fun getCache(): CacheDao
}
