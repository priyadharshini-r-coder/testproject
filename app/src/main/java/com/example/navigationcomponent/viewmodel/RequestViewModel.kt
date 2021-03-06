package com.example.navigationcomponent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navigationcomponent.model.MainData
import com.manager.retrofitesting.model.api.ApiHelperImpl
import com.manager.retrofitesting.model.api.RetrofitBuilder
import com.manager.retrofitesting.util.Resource

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestViewModel : ViewModel() {

    private val result = MutableLiveData<Resource<MainData>?>()

    fun fetchDetails() {
        val apiHelper = ApiHelperImpl(RetrofitBuilder.apiInterface)
        apiHelper.getEmployeeDetails()
            .enqueue(object : Callback<MainData> {
                override fun onFailure(call: Call<MainData>, t: Throwable) {
                    result.postValue(Resource.error("Something went wrong!",null))
                }

                override fun onResponse(call: Call<MainData>, response: Response<MainData>) {
                    result.postValue(Resource.success(response.body()))
                }
            })
    }

    fun getDetail(): LiveData<Resource<MainData>?> {
        return result
    }
}