package me.typ.lib_network

class GetRequest<T>(url: String) : Request<T, GetRequest<T>>(url) {
    override fun generateRequest(builder: okhttp3.Request.Builder): okhttp3.Request {
        //get 请求把参数拼接在 url后面 即可
        val url = UrlCreator.createUrlFromParams(mUrl, params)
        return builder.get().url(url).build()
    }
}