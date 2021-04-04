package com.example.travelrecordapp.di

import com.example.travelrecordapp.data.NetworkResponseAdapterFactory
import com.example.travelrecordapp.data.RequestInterface
import com.example.travelrecordapp.data.RetrofitService
import com.example.travelrecordapp.data.repository.AuthRepository
import com.example.travelrecordapp.data.repository.TourRepository
import com.example.travelrecordapp.data.source.remote.AuthRemoteDataSource
import com.example.travelrecordapp.data.source.remote.TourRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton


private val baseURL = "https://azanghs.cafe24.com/itstudy/"

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    //retrofit, repository

    @Singleton
    @Provides
    fun provideRetrofitService(): RequestInterface = Retrofit.Builder()
        .baseUrl(baseURL)
        // 만든 NetworkResponse 이거 사용하려고
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        //response String으로 받아오려고
        .addConverterFactory(ScalarsConverterFactory.create())
        //response Json 으로
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RequestInterface::class.java)


    @Singleton
    @Provides
    fun provideAuthRepository(
        authRemoteDataSource: AuthRemoteDataSource
    ):AuthRepository = AuthRepository(authRemoteDataSource)

    @Singleton
    @Provides
    fun provideTourRepository(
            tourRemoteDataSource: TourRemoteDataSource
    ):TourRepository = TourRepository(tourRemoteDataSource)

}