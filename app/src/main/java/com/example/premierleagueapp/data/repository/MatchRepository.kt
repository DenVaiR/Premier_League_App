package com.example.premierleagueapp.data.repository

import com.example.premierleagueapp.data.api.MatchApi
import com.example.premierleagueapp.data.model.Match
import javax.inject.Inject

class MatchRepository @Inject constructor(
    private val matchApi: MatchApi
) {
    private var cachedMatches: List<Match>? = null

    suspend fun getMatches(): List<Match> {
        if (cachedMatches == null) {
            cachedMatches = matchApi.getMatches()
                .mapIndexed { index, match -> match.copy(id = index) }
        }
        return cachedMatches!!
    }

    suspend fun getMatchById(id: Int): Match? {
        return getMatches().firstOrNull { it.id == id }
    }
}