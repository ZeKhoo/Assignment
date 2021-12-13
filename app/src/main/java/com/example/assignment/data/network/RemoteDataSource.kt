package com.example.assignment.data.network

import com.example.assignment.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {
    companion object {
        private const val BASE_URL = "https://login-e218b-default-rtdb.asia-southeast1.firebasedatabase.app"
    }

    fun<Api> buildApi(
        api:Class<Api>
    ): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().also{ client->
                    if(BuildConfig.DEBUG) {
                        val logging = HttpLoggingInterceptor()
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(logging)
                    }
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)

    }
}