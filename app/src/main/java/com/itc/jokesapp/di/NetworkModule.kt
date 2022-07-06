package com.itc.jokesapp.di

import com.google.gson.Gson
import com.itc.jokesapp.api.JokeServiceApi
import com.itc.jokesapp.api.JokeServiceApi.Companion.BASE_URL
import com.itc.jokesapp.util.UnsafeOkHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideGson(): Gson = Gson()

//    @Provides
//    fun loggingInterceptor(): HttpLoggingInterceptor =
//        HttpLoggingInterceptor().apply {
//            level = HttpLoggingInterceptor.Level.BODY
//        }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient : OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

//    @Provides
//    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
//        OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .connectTimeout(30, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
//            .writeTimeout(30, TimeUnit.SECONDS)
//            .build()

    @Provides
    fun provideOkHttpClient(): OkHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient()

    @Provides
    @Singleton
    fun provideJokeService(retrofit : Retrofit) : JokeServiceApi =
        retrofit.create(JokeServiceApi::class.java)

}