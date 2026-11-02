package com.myapp.pokedex.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.myapp.pokedex.presentation.pokemondetail.PokemonDetailScreen
import com.myapp.pokedex.presentation.pokemonlist.PokemonListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "list") {
        composable("list") {
            PokemonListScreen(onPokemonClick = { id -> navController.navigate("detail/$id") })
        }
        composable(
            route = "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            PokemonDetailScreen(onBack = {
                if (navController.previousBackStackEntry != null) navController.popBackStack()
            })
        }
    }
}