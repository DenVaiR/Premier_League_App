package com.example.premierleagueapp.data.api

import com.example.premierleagueapp.data.model.Match
import retrofit2.http.GET

interface MatchApi {
    @GET("epl-2023")
    suspend fun getMatches(): List<Match>
}