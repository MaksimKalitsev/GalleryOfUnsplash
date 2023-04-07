package com.example.galleryofunsplash.di

import com.example.galleryofunsplash.repository.UnsplashApi
import com.example.galleryofunsplash.util.Const.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor


class AppModule(private val accessKey: String) {
    private val gson: Gson = GsonBuilder().create()
    private val authInterceptor = AuthInterceptor(accessKey)
    private val okHttpClient = createOkHttpClient()
    private val retrofit = createRetrofit(gson, okHttpClient)

    val unsplashApi = retrofit.create(UnsplashApi::class.java)

    private fun createRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(createLoggingInterceptor())
            .addInterceptor(authInterceptor)
            .build()
    }

    private fun createLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    class AuthInterceptor(private val accessKey: String) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val newRequest = request.newBuilder()
                .addHeader( "Client-ID", accessKey)
                .build()
            return chain.proceed(newRequest)
        }
    }
}