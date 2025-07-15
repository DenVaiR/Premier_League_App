package com.example.premierleagueapp.ui.screens.matches

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.paging.LoadState
import com.example.premierleagueapp.ui.components.MatchItem
import com.example.premierleagueapp.ui.components.SearchBar

@Composable
fun MatchesScreen(
    viewModel: MatchesViewModel = hiltViewModel(),
    navController: NavController
) {
    val searchQuery = viewModel.searchQuery.collectAsState().value
    val matches = viewModel.matches.collectAsLazyPagingItems()
    val scrollState = rememberLazyListState()
    var showSearchBar by remember { mutableStateOf(true) }

    LaunchedEffect(scrollState) {
        snapshotFlow { scrollState.firstVisibleItemScrollOffset }
            .collect { offset ->
                showSearchBar = offset < 20 || scrollState.firstVisibleItemIndex == 0
            }
    }

    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = showSearchBar,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Column(modifier = Modifier.padding(top = 24.dp)) {
                    SearchBar(
                        query = searchQuery,
                        onQueryChange = viewModel::onSearch,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
        }
    ) { paddingValues ->
        when {
            matches.loadState.refresh is LoadState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
            matches.itemCount == 0 -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = if (searchQuery.isNotEmpty()) {
                            "No matches found for \"$searchQuery\""
                        } else {
                            "No matches available"
                        },
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }
            else -> {
                LazyColumn(
                    state = scrollState,
                    contentPadding = paddingValues,
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(matches.itemCount) { index ->
                        val match = matches[index]
                        match?.let {
                            MatchItem(
                                match = it,
                                onClick = { navController.navigate("match_details/${it.id}") }
                            )
                        }
                    }
                }
            }
        }
    }
}