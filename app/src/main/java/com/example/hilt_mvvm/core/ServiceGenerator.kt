package com.example.hilt_mvvm.core.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import kotlin.jvm.Throws


/**
 * Created By @HashimHabtur on 18/08/2020 AD
 */

//class ServiceGenerator(private val token:String) {
class ServiceGenerator() {
    fun <S> createService(serviceClass: Class<S>?): S {
        val builder = Retrofit.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder().addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                val request: Request =
                    chain.request().newBuilder()
//                        .addHeader(
//                        "Authorization",
//                        "Bearer ${token}"
//                    )
                        .addHeader("Content-Type", "application/json").build()
                return chain.proceed(request)
            }
        }).build()
        val retrofit: Retrofit = builder.baseUrl(Config.TEST).client(httpClient).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(CoroutineCallAdapterFactory()).build()
        return retrofit.create(serviceClass)
    }
}