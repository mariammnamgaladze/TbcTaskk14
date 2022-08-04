package com.example.tbctaskk14.network

import com.example.tbctaskk14.model.Data
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

object RetroObj {
    private const val BASE_URL = "https://run.mocky.io/"

    val builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    fun getData() = builder.create(DataGetter::class.java)
}

interface DataGetter{
    @GET("v3/f4864c66-ee04-4e7f-88a2-2fbd912ca5ab")
    suspend fun info(): Response<Data>
}