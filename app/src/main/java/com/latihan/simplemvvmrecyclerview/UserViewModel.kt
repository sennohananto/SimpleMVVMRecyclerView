package com.latihan.simplemvvmrecyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    private val repository = UserRepository()

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private val _operationResult = MutableLiveData<String>()
    val operationResult: LiveData<String> get() = _operationResult

    fun fetchUsers() {
        repository.getUsers().enqueue(object : Callback<BaseResponse<List<User>>> {
            override fun onResponse(call: Call<BaseResponse<List<User>>>, response: Response<BaseResponse<List<User>>>) {
                if (response.isSuccessful) {
                    _users.value = response.body()?.data
                }
            }

            override fun onFailure(call: Call<BaseResponse<List<User>>>, t: Throwable) {
                _operationResult.value = "Failed to fetch users: ${t.message}"
            }
        })
    }

    fun addUser(user: User) {
        repository.createUser(user).enqueue(object : Callback<BaseResponse<User>> {
            override fun onResponse(call: Call<BaseResponse<User>>, response: Response<BaseResponse<User>>) {
                if (response.isSuccessful) {
                    _operationResult.value = "User created successfully"
                    fetchUsers()  // Refresh the list
                }
            }

            override fun onFailure(call: Call<BaseResponse<User>>, t: Throwable) {
                _operationResult.value = "Failed to create user: ${t.message}"
            }
        })
    }

    fun updateUser(id: Int, user: User) {
        repository.updateUser(id, user).enqueue(object : Callback<BaseResponse<User>> {
            override fun onResponse(call: Call<BaseResponse<User>>, response: Response<BaseResponse<User>>) {
                if (response.isSuccessful) {
                    _operationResult.value = "User updated successfully"
                    fetchUsers()  // Refresh the list
                }
            }

            override fun onFailure(call: Call<BaseResponse<User>>, t: Throwable) {
                _operationResult.value = "Failed to update user: ${t.message}"
            }
        })
    }

    fun deleteUser(id: Int) {
        repository.deleteUser(id).enqueue(object : Callback<BaseResponse<Void>> {
            override fun onResponse(call: Call<BaseResponse<Void>>, response: Response<BaseResponse<Void>>) {
                if (response.isSuccessful) {
                    _operationResult.value = "User deleted successfully"
                    fetchUsers()  // Refresh the list
                }
            }

            override fun onFailure(call: Call<BaseResponse<Void>>, t: Throwable) {
                _operationResult.value = "Failed to delete user: ${t.message}"
            }
        })
    }
}