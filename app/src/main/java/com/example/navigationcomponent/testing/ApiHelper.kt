package com.example.navigationcomponent.testing

import com.example.navigationcomponent.model.MainData

import retrofit2.Call

interface ApiHelper {

    fun getEmployeeDetails(): Call<MainData>
}