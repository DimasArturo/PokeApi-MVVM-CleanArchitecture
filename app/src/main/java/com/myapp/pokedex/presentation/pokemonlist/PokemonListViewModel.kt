package com.myapp.pokedex.presentation.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.myapp.pokedex.PokedexApp
import com.myapp.pokedex.domain.usecase.GetPokemonListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val getPokemonList: GetPokemonListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonListUiState())
    val uiState: StateFlow<PokemonListUiState> = _uiState.asStateFlow()

    init { load() }

    fun load() {
        viewModelScope.launch {
            _uiState.value = PokemonListUiState(isLoading = true)
            try {
                _uiState.value = PokemonListUiState(pokemons = getPokemonList(limit = 1500))
            } catch (e: Exception) {
                _uiState.value = PokemonListUiState(error = "No se pudo cargar la lista")
            }
        }
    }

    fun onQueryChange(query: String){
        _uiState.update { it.copy(query=query) }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as PokedexApp
                PokemonListViewModel(app.container.getPokemonListUseCase)
            }
        }
    }
}