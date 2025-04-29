package com.example.tangogo.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

data class HiraganaRequest(val text: String)
data class HiraganaResponse(val hiragana: String)

interface KakasiApi {
    @Headers("Content-Type: application/json")
    @POST("to_hiragana")
    fun convertToHiragana(@Body request: HiraganaRequest): Call<HiraganaResponse>
}