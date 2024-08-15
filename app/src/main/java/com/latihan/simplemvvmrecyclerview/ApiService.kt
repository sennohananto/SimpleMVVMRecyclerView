package com.latihan.simplemvvmrecyclerview

import retrofit2.Call
import retrofit2.http.GET

import retrofit2.http.*

interface ApiService {

    @GET("users")
    fun getUsers(): Call<BaseResponse<List<User>>>

    @POST("users")
    fun createUser(@Body user: User): Call<BaseResponse<User>>

    @PUT("users/{id}")
    fun updateUser(@Path("id") id: Int, @Body user: User): Call<BaseResponse<User>>

    @DELETE("users/{id}")
    fun deleteUser(@Path("id") id: Int): Call<BaseResponse<Void>>
}