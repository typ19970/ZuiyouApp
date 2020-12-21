package me.typ.lib_network

interface JsonCallback<T> {
    fun onSuccess(response: ApiResponse<T>) {}

    fun onError(response: ApiResponse<T>) {}

    fun onCacheSuccess(response: ApiResponse<T>) {}
}
