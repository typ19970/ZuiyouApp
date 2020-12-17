package me.typ.zuiyou.data.config

import android.content.res.AssetManager
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import me.typ.zuiyou.data.model.BottomBar
import me.typ.zuiyou.data.model.Destination
import me.typ.zuiyou.data.model.TabMo
import me.typ.zuiyou.util.AppGlobals
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

object AppConfig {

    private val mGson = GsonBuilder().create()

    private var sBottomBar: BottomBar? = null
    private var enableHomeTabs: MutableList<TabMo>? = null
    private var sDestConfig: HashMap<String, Destination>? = null


    fun getDestConfig(): HashMap<String, Destination> {
        if (sDestConfig == null) {
            val content = parseFile("destination.json")
            /*sDestConfig = JSON.parseObject<HashMap<String, Destination>?>(
                content,
                object : TypeReference<HashMap<String, Destination>?>() {})*/
            sDestConfig =
                mGson.fromJson(content, object : TypeToken<HashMap<String, Destination>?>() {}.type)
        }
        return sDestConfig!!
    }

    fun getBottomBarConfig(): BottomBar {
        if (sBottomBar == null) {
            val content = parseFile("main_tabs_config.json")
            //sBottomBar = JSON.parseObject(content, BottomBar::class.java)
            sBottomBar = mGson.fromJson(content, BottomBar::class.java)
        }
        return sBottomBar!!
    }

    fun getHomeEnableTabs(): List<TabMo> {
        if (enableHomeTabs == null) {
            val content = parseFile("home_tabs_config.json")
            enableHomeTabs = mGson.fromJson(content, object : TypeToken<List<TabMo>>() {}.type)
            //过滤掉不开启的tab
            enableHomeTabs!!.forEach {
                if (!it.enable) enableHomeTabs!!.remove(it)
            }
        }
        return enableHomeTabs!!
    }


    private fun parseFile(fileName: String): String {
        val assets: AssetManager = AppGlobals.getApplication().assets
        var inputStream: InputStream? = null
        var bufferedReader: BufferedReader? = null
        val builder = StringBuilder()
        try {
            inputStream = assets.open(fileName)
            bufferedReader = BufferedReader(InputStreamReader(inputStream))
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                builder.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                inputStream?.close()
                bufferedReader?.close()
            } catch (e: Exception) {

            }
        }
        return builder.toString()
    }
}