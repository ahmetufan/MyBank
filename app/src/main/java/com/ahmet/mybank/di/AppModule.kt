package com.ahmet.mybank.di

import com.ahmet.mybank.api.ApiService
import com.ahmet.mybank.api.ApiServiceInterfaceRepository
import com.ahmet.mybank.api.ApiServiceRepository
import com.ahmet.mybank.utils.Util
import com.ahmet.mybank.utils.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun provideBaseUrl() = Util.BASE_URL

    @Provides
    @Singleton
    fun injectRetrofitAPI(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun injectNormalRepo(api:ApiService) = ApiServiceRepository(api) as ApiServiceInterfaceRepository


}