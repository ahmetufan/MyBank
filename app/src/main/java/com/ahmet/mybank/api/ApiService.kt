package com.ahmet.mybank.api

import com.ahmet.mybank.models.Bank
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

//    https://raw.githubusercontent.com/         fatiha380/mockjson/main/bankdata

    @GET("fatiha380/mockjson/main/bankdata")
    fun getData(): Single<List<Bank>>
}