package com.example.premierleagueapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun MatchesScreen(
    viewModel: MatchesViewModel = hiltViewModel(),
    onMatchClick: (Match) -> Unit
) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val matches = viewModel.matches.collectAsLazyPagingItems()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            Column(modifier = Modifier.padding(8.dp)) {
                SearchBar(
                    query = searchQuery,
                    onQueryChange = viewModel::onSearch
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(matches.itemCount, key = { it }) { index ->
                val match = matches[index] ?: return@items

                MatchItem(
                    match = match,
                    onClick = { onMatchClick(match) }
                )
            }
        }
    }
}