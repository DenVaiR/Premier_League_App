package com.example.premierleagueapp

import retrofit2.http.GET

interface MatchApi {
    @GET("epl-2023")
    suspend fun getMatches(): List<Match>
}