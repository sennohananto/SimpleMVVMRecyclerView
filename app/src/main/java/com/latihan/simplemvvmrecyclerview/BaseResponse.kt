package com.latihan.simplemvvmrecyclerview

data class BaseResponse<T>(
    val status: String,
    val message: String,
    val data: T? = null
)