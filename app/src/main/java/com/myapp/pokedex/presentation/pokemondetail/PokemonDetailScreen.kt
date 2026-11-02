package com.myapp.pokedex.presentation.pokemondetail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    onBack: () -> Unit,
    viewModel: PokemonDetailViewModel = viewModel(factory = PokemonDetailViewModel.Factory)
) {
    val state by viewModel.uiState.collectAsState()
    val pokemon = state.pokemon
    val error = state.error
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(pokemon?.name ?: "") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else if (error != null) {
                Text(error)
            } else if (pokemon != null) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AsyncImage(
                        model = pokemon.imageUrl,
                        contentDescription = pokemon.name,
                        modifier = Modifier.size(200.dp)
                    )
                    Text(pokemon.name, style = MaterialTheme.typography.headlineMedium)
                    Text("Tipo: ${pokemon.types.joinToString()}")
                    Text("Altura: ${pokemon.height / 10.0} m")
                    Text("Peso: ${pokemon.weight / 10.0} kg")
                }
            }
        }
    }
}
