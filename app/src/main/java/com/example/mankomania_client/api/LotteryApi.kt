package com.example.mankomania_client.api

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LotteryApi {
    @GET("lottery/current")
    suspend fun getCurrentAmount(): Response<Int>

    @POST("lottery/pay")
    suspend fun payToLottery(
        @Query("playerId") playerId: String,
        @Query("amount") amount: Int,
        @Query("reason") reason: String
    ): Response<PaymentResponse>

    @POST("lottery/claim")
    suspend fun claimLottery(@Query("playerId") playerId: String): Response<ClaimResponse>

    companion object {
        private const val BASE_URL = "http://se2-demo.aau.at:53210/"

        fun create(): LotteryApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LotteryApi::class.java)
        }
    }

    data class PaymentResponse(
        val success: Boolean,
        val newAmount: Int,
        val message: String
    )

    data class ClaimResponse(
        val wonAmount: Int,
        val newAmount: Int,
        val message: String
    )
}