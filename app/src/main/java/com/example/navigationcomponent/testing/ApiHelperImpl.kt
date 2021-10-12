package com.example.navigationcomponent.testing


import com.example.navigationcomponent.model.MainData
import retrofit2.Call

class ApiHelperImpl(private val apiInterface: ApiInterface) : ApiHelper {

    override fun getEmployeeDetails(): Call<MainData> = apiInterface.getEmployeeDetails()

}