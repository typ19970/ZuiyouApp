package me.typ.lib_network

import okhttp3.FormBody

class PostRequest<T>(url: String) : Request<T, PostRequest<T>>(url) {
    override fun generateRequest(builder: okhttp3.Request.Builder): okhttp3.Request {
        //post请求表单提交
        val bodyBuilder = FormBody.Builder()
        params.entries.forEach {
            bodyBuilder.add(it.key, it.value.toString())
        }
        return builder.url(mUrl).post(bodyBuilder.build()).build()
    }
}