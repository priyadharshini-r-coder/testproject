package com.example.navigationcomponent.dependencyinjection.data.api


import com.example.navigationcomponent.dependencyinjection.data.model.User
import retrofit2.Response

interface ApiHelper {

    suspend fun getUsers(): Response<List<User>>
}