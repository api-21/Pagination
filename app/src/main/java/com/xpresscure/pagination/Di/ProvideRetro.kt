package com.xpresscure.pagination.Di

import com.xpresscure.pagination.Retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ProvideRetro {

    @Provides
    @Singleton
    fun ProvideApiService(): ApiService {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiService.BASE_URL).build().create(ApiService::class.java)
    }

}