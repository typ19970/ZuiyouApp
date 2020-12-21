package me.typ.lib_network

class ApiResponse<T> {
    var success: Boolean = true
    var status: Int = 0
    var message: String? = null
    var body: T? = null
}