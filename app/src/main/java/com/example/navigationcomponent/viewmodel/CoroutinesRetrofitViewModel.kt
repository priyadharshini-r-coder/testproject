package com.example.navigationcomponent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.navigationcomponent.model.CountryCodeListResponse
import com.example.navigationcomponent.retrofit.RetrofitBuilder
import com.example.navigationcomponent.retrofit.RetrofitService

import retrofit2.Response

class CoroutinesRetrofitViewModel : ViewModel() {

    private var countryListObservable: MutableLiveData<CountryCodeListResponse>? = null
    private lateinit var retService: RetrofitService

    fun getCountry(): LiveData<Response<CountryCodeListResponse>> =  liveData  {
        retService = RetrofitBuilder.getretrofit().create(RetrofitService::class.java)
        countryListObservable = MutableLiveData<CountryCodeListResponse>()

        val response = retService.getAlldata()
        emit(response)
    }
}