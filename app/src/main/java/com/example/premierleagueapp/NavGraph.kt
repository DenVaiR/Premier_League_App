package com.example.premierleagueapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "matches"
    ) {
        composable("matches") {
            MatchesScreen(
                onMatchClick = { match ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("match", match)
                    navController.navigate("match_details")
                }
            )
        }

        composable("match_details") {
            MatchDetailsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}