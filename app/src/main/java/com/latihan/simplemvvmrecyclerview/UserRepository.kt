package com.latihan.simplemvvmrecyclerview

import retrofit2.Call

class UserRepository {

    fun getUsers(): Call<BaseResponse<List<User>>> {
        return RetrofitInstance.api.getUsers()
    }

    fun createUser(user: User): Call<BaseResponse<User>> {
        return RetrofitInstance.api.createUser(user)
    }

    fun updateUser(id: Int, user: User): Call<BaseResponse<User>> {
        return RetrofitInstance.api.updateUser(id, user)
    }

    fun deleteUser(id: Int): Call<BaseResponse<Void>> {
        return RetrofitInstance.api.deleteUser(id)
    }
}