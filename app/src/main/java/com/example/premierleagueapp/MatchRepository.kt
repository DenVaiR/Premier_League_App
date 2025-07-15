package com.example.premierleagueapp

import javax.inject.Inject

class MatchRepository @Inject constructor(
    private val matchApi: MatchApi
) {
    suspend fun getMatches(): List<Match> {
        return matchApi.getMatches()
    }
}