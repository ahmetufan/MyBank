package com.ahmet.mybank.api

import com.ahmet.mybank.models.Bank
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface ApiServiceInterfaceRepository {

    fun getData(): Single<List<Bank>>
}