package com.example.navigationcomponent.retrofit

import omnicurekotlin.example.com.userEndpoints.model.CountryCodeListResponse
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET("countrylistresponse")
    suspend fun getAlldata() : Response<CountryCodeListResponse>
}