package com.ahmet.mybank.api

import com.ahmet.mybank.models.Bank
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class ApiServiceRepository @Inject constructor(private val repository:ApiService) :ApiServiceInterfaceRepository{


    override fun getData(): Single<List<Bank>> {
        return repository.getData()
    }


}
