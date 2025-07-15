package com.example.premierleagueapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.premierleagueapp.data.model.Match

class MatchesPagingSource(
    private val repository: MatchRepository,
    private val query: String
) : PagingSource<Int, Match>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Match> {
        return try {
            val matches = repository.getMatches()
            val filteredMatches = if (query.isNotEmpty()) {
                matches.filter {
                    it.homeTeam.contains(query, ignoreCase = true) ||
                            it.awayTeam.contains(query, ignoreCase = true)
                }
            } else {
                matches
            }

            LoadResult.Page(
                data = filteredMatches,
                prevKey = null,
                nextKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Match>): Int? {
        return null
    }
}