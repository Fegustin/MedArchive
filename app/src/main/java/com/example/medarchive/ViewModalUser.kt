package com.example.medarchive

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.medarchive.pojo.UserDate

class ViewModalUser : ViewModel() {
    val user = MutableLiveData<UserDate>()

    fun setUser(item: UserDate) {
        user.value = item
    }

//    private val users: MutableLiveData<User> by lazy {
//        MutableLiveData<User>().also {
//            loadUsers()
//        }
//    }
//
//    fun getUsers(): LiveData<User> {
//        return users
//    }
//
//    private fun loadUsers() {
//        // Do an asynchronous operation to fetch users.
//    }
}