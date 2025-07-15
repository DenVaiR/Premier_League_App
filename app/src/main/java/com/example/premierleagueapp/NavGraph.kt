package com.example.premierleagueapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "matches"
    ) {
        composable("matches") {
            MatchesScreen(navController = navController)
        }

        composable(
            "match_details/{matchId}",
            arguments = listOf(navArgument("matchId") { type = NavType.IntType })
        ) { backStackEntry ->
            val matchId = backStackEntry.arguments?.getInt("matchId") ?: 0
            MatchDetailsScreen(matchId = matchId, navController = navController)
        }
    }
}