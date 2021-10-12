package com.example.navigationcomponent.dependencyinjection.data.repository

import com.example.navigationcomponent.dependencyinjection.data.api.ApiHelper

import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getUsers() =  apiHelper.getUsers()

}