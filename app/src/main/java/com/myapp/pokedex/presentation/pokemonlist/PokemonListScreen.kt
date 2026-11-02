package com.myapp.pokedex.presentation.pokemonlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
    onPokemonClick: (Int) -> Unit,
    viewModel: PokemonListViewModel = viewModel(factory = PokemonListViewModel.Factory)
) {
    val state by viewModel.uiState.collectAsState()
    val error = state.error

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pokédex") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(Modifier.fillMaxSize().padding(innerPadding)) {
            OutlinedTextField(
                value = state.query,
                onValueChange = viewModel::onQueryChange,
                label = { Text("Buscar Pokémon") },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )
            Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                if (state.isLoading) {
                    CircularProgressIndicator()
                } else if (error != null) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(error)
                        Button(onClick = { viewModel.load() }) { Text("Reintentar") }
                    }
                } else if (state.filteredPokemons.isEmpty()) {
                    Text("No se encontraron resultados")
                } else {
                    LazyColumn(Modifier.fillMaxSize()) {
                        items(state.filteredPokemons, key = { it.id }) { pokemon ->
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .clickable { onPokemonClick(pokemon.id) }
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = pokemon.imageUrl,
                                    contentDescription = pokemon.name,
                                    modifier = Modifier.size(64.dp)
                                )
                                Spacer(Modifier.width(16.dp))
                                Text(pokemon.name, style = MaterialTheme.typography.titleMedium)
                            }
                        }
                    }
                }
            }
        }
    }
}