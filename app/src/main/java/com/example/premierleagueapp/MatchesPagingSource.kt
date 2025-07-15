package com.example.premierleagueapp

import androidx.paging.PagingSource
import androidx.paging.PagingState

class MatchesPagingSource(
    private val repository: MatchRepository,
    private val query: String
) : PagingSource<Int, Match>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Match> {
        return try {
            val matches = repository.getMatches()
            val filteredMatches = if (query.isNotEmpty()) {
                matches.filter {
                    it.HomeTeam.contains(query, ignoreCase = true) ||
                            it.AwayTeam.contains(query, ignoreCase = true)
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