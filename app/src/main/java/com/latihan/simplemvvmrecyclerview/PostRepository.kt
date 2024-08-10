package com.latihan.simplemvvmrecyclerview

import retrofit2.Call

class PostRepository {

    fun getPosts(): Call<List<Post>> {
        return RetrofitInstance.api.getPosts()
    }
}