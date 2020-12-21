package me.typ.lib_network

import java.io.UnsupportedEncodingException
import java.net.URLEncoder

object UrlCreator {
    fun createUrlFromParams(url: String, params: Map<String, Any?>): String {
        return StringBuilder().run {
            append(url)
            if (url.indexOf("?") > 0 || url.indexOf("&") > 0) {
                append("&")
            } else {
                append("?")
            }
            params.entries.forEach {
                try {
                    val value = URLEncoder.encode(it.value.toString(), "UTF-8")
                    append("${it.key}=$value&")
                } catch (e: UnsupportedEncodingException) {
                    e.printStackTrace()
                }
            }
            deleteCharAt(length - 1)
            toString()
        }
    }
}