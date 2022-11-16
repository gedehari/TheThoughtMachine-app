package com.gedehari.thethoughtmachine.network

import com.gedehari.thethoughtmachine.data.Thought
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://squirrel-cloud1.ddns.net/test/"

private val moshi = Moshi.Builder()
    .add(ThoughtJsonAdapter())
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ThoughtsApiService {
    @GET("thoughts")
    suspend fun getThoughts(
        @Query("page") page: Int = 0
    ): List<Thought>
}

object ThoughtsApi {
    val retrofitService: ThoughtsApiService by lazy { retrofit.create(ThoughtsApiService::class.java) }
}
