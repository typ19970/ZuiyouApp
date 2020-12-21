package me.typ.lib_network

import java.lang.reflect.Type

interface Convert<T :Any> {
    fun convert(response: String, type: Type): T
}