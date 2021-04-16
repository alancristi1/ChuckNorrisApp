package com.app.chucknorrisapp.networking

import com.app.chucknorrisapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private val retrofitClient: Retrofit.Builder by lazy {

        val levelType: Level = if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
            Level.BODY else Level.NONE

        val logging = HttpLoggingInterceptor()
        logging.level = levelType

        val okhttpClient = OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
        okhttpClient.addInterceptor(logging)

        Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okhttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
    }

    val apiInterface: ApiService by lazy {
        retrofitClient
                .build()
                .create(ApiService::class.java)
    }
}