package me.typ.lib_network.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response

/**
 * 日志输出拦截器
 */
class LoggingInterceptor : Interceptor {

    private val TAG = "LoggingInterceptor"

    override fun intercept(chain: Interceptor.Chain): Response {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        val request = chain.request()

        //请求发起的时间
        val t1 = System.nanoTime()
        val connection = chain.connection()
        Log.i(
            TAG, String.format(
                "发送 [%s] 方法 协议: [%s] 请求Url: %s  %s",
                request.method,
                connection?.protocol() ?: Protocol.HTTP_1_1,
                request.url,
                request.headers
            )
        )

        val response = chain.proceed(request)
        //收到响应的时间
        val t2 = System.nanoTime()
        val responseBody = response.peekBody(1024 * 2048)
        Log.i(
            TAG, String.format(
                "收到响应: 状态码 [%s]:  响应时间: %.1fms%n%s",
                response.code,
                (t2 - t1) / 1e6,// 1e6 ==1000000 1*10^6 即毫秒=>秒
                response.headers
            )
        )
        print(responseBody.string())

        return response
    }

    private fun print(json: String?) {
        if (json == null) return
        try {
            Log.i(TAG, "----------logging begin-----------")
            val willPrintString = format(json)
            Log.i(TAG, willPrintString ?: "")
            Log.i(TAG, "----------logging end-----------")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun format(json: String?): String {
        if (json == null || json == "") {
            return ""
        }
        val soruce = StringBuilder(json)
        var offset = 0 //目标字符串插入空格偏移量
        var bOffset = 0 //空格偏移量
        for (i in json.indices) {
            val charAt: Char = json[i]
            if (charAt == '{' || charAt == '[') {
                bOffset += 4
                soruce.insert(i + offset + 1, generateBlank(bOffset).trimIndent())
                offset += bOffset + 1
            } else if (charAt == ',') {
                soruce.insert(i + offset + 1, generateBlank(bOffset).trimIndent())
                offset += bOffset + 1
            } else if (charAt == '}' || charAt == ']') {
                bOffset -= 4
                soruce.insert(i + offset, generateBlank(bOffset).trimIndent())
                offset += bOffset + 1
            }
        }
        return soruce.toString()
    }

    private fun generateBlank(num: Int): String {
        val stringBuilder = StringBuilder()
        for (i in 0 until num) {
            stringBuilder.append(" ")
        }
        return stringBuilder.toString()
    }
}