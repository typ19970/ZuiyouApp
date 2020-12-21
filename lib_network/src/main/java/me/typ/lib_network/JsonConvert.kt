package me.typ.lib_network

import com.google.gson.GsonBuilder
import java.lang.reflect.Type

class JsonConvert : Convert<Any> {
    override fun convert(response: String, type: Type): Any {
        val gson = GsonBuilder().create()
        return gson.fromJson<Type>(response, type) //TODO
    }
}