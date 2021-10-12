package com.example.navigationcomponent.testing


import com.example.navigationcomponent.model.MainData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/api/v1/employees")
    fun getEmployeeDetails(): Call<MainData>
}