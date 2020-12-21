package me.typ.lib_network

import android.annotation.SuppressLint
import android.text.TextUtils
import android.util.Log
import androidx.annotation.IntDef
import androidx.arch.core.executor.ArchTaskExecutor
import me.typ.lib_network.cache.CacheManager
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import java.io.Serializable
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

@Suppress("UNCHECKED_CAST")
abstract class Request<T, R : Request<T, R>>(protected var mUrl: String) : Cloneable {

    protected val headers = HashMap<String, String>()
    protected val params = HashMap<String, Any>()

    companion object {
        //仅仅只访问本地缓存，即便本地缓存不存在，也不会发起网络请求
        const val CACHE_ONLY = 1

        //先访问缓存，同时发起网络的请求，成功后缓存到本地
        const val CACHE_FIRST = 2

        //仅仅只访问服务器，不存任何存储
        const val NET_ONLY = 3

        //先访问网络，成功后缓存到本地
        const val NET_CACHE = 4
    }

    @IntDef(value = [CACHE_ONLY, CACHE_FIRST, NET_CACHE, NET_ONLY])
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class CacheStrategy

    private var mType: Type? = null
    private var mCacheKey: String? = null
    private var mCacheStrategy = NET_ONLY

    fun addHeader(key: String, value: String): R {
        headers[key] = value
        return this as R
    }

    fun addParam(key: String, value: Any?): R {
        if (value != null) {
            if (value::class.java == String::class.java) {
                //添加字符串类型
                params[key] = value
            } else {
                try {
                    val field = value::class.java.getField("TYPE")
                    val clazz = field.get(null) as Class<*>
                    /**
                     * @return true if and only if this class represents a primitive type
                     * @see    java.lang.Class.isPrimitive
                     */
                    if (clazz.isPrimitive) params[key] = value
                } catch (e1: NoSuchFieldException) {
                    e1.printStackTrace()
                } catch (e2: IllegalAccessException) {
                    e2.printStackTrace()
                }
            }
        }
        return this as R
    }

    fun cacheStrategy(@CacheStrategy cacheStrategy: Int): R {
        mCacheStrategy = cacheStrategy
        return this as R
    }

    fun cacheKey(key: String): R {
        mCacheKey = key
        return this as R
    }

    fun responseType(type: Type): R {
        mType = type
        return this as R
    }

    fun responseType(clazz: Class<*>): R {
        mType = clazz
        return this as R
    }

    protected abstract fun generateRequest(builder: okhttp3.Request.Builder): okhttp3.Request

    fun execute(): ApiResponse<T> {
        if (mType == null) {
            throw RuntimeException("同步方法,response 返回值 类型必须设置")
        }
        if (mCacheStrategy == CACHE_ONLY) {
            return readCache()
        }
        var result: ApiResponse<T>
        try {
            val response = getCall().execute()
            result = parseResponse(response, null)
        } catch (e: IOException) {
            e.printStackTrace()
            result = ApiResponse()
            result.message = e.message
        }
        return result
    }

    @SuppressLint("RestrictedApi")
    fun execute(callback: JsonCallback<T>?) {
        if (mCacheStrategy != NET_ONLY) {
            ArchTaskExecutor.getIOThreadExecutor().execute {
                val response = readCache()
                if (callback != null && response.body != null) {
                    callback.onCacheSuccess(response)
                }
            }
        }
        if (mCacheStrategy != CACHE_ONLY) {
            getCall().enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    val result = ApiResponse<T>()
                    result.message = e.message
                    callback?.onError(result)
                }

                override fun onResponse(call: Call, response: Response) {
                    val result = parseResponse(response, callback)
                    if (!result.success) {
                        callback?.onError(result)
                    } else {
                        callback?.onSuccess(result)
                    }
                }
            })
        }
    }

    private fun getCall(): Call {
        val builder = okhttp3.Request.Builder()
        addHeaders(builder)
        val request = generateRequest(builder)
        return ApiService.okHttpClient.newCall(request)
    }

    private fun addHeaders(builder: okhttp3.Request.Builder) {
        headers.entries.forEach {
            builder.addHeader(it.key, it.value)
        }
    }

    private fun readCache(): ApiResponse<T> {
        val key = if (TextUtils.isEmpty(mCacheKey)) generateCacheKey() else mCacheKey
        val cache = CacheManager.getCache(key!!) as T
        val result = ApiResponse<T>()
        result.status = 304
        result.message = "缓存获取成功"
        result.body = cache
        result.success = true
        return result
    }

    private fun parseResponse(response: Response, callback: JsonCallback<T>?): ApiResponse<T> {
        var message: String? = null
        var status = response.code
        var success = response.isSuccessful
        val result = ApiResponse<T>()
        val convert = ApiService.sConvert
        try {
            val content = response.body?.string() ?: ""
            if (success) {
                if (callback != null) {
                    val type = callback.javaClass.genericSuperclass as ParameterizedType
                    val argument = type.actualTypeArguments[0]
                    result.body = convert?.convert(content, argument) as T
                } else if (mType != null) {
                    result.body = convert?.convert(content, mType!!) as T
                } else {
                    Log.e("request", "parseResponse: 无法解析 ")
                }
            } else {
                message = content
            }
        } catch (e: Exception) {
            message = e.message
            success = false
            status = -1
        }
        result.success = success
        result.status = status
        result.message = message

        if (mCacheStrategy != NET_ONLY && result.success && result.body is Serializable) {
            saveCache(result.body!!)
        }
        return result
    }

    private fun saveCache(body: T) {
        val key = if (TextUtils.isEmpty(mCacheKey)) generateCacheKey() else mCacheKey
        CacheManager.save(key!!, body)
    }

    private fun generateCacheKey(): String {
        mCacheKey = UrlCreator.createUrlFromParams(mUrl, params)
        return mCacheKey!!
    }

    override fun clone(): Any {
        return super.clone() as Request<T, R>
    }
}