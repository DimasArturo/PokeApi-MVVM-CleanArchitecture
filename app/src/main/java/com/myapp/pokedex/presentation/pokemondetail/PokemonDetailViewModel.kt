package com.myapp.pokedex.presentation.pokemondetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.myapp.pokedex.PokedexApp
import com.myapp.pokedex.domain.usecase.GetPokemonDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val getPokemonDetail: GetPokemonDetailUseCase
) : ViewModel() {

    private val id: Int = checkNotNull(savedStateHandle["id"])

    private val _uiState = MutableStateFlow(PokemonDetailUiState())
    val uiState: StateFlow<PokemonDetailUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = PokemonDetailUiState(isLoading = true)
            try {
                _uiState.value = PokemonDetailUiState(pokemon = getPokemonDetail(id))
            } catch (e: Exception) {
                _uiState.value = PokemonDetailUiState(error = "No se pudo cargar el Pokémon")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as PokedexApp
                PokemonDetailViewModel(createSavedStateHandle(), app.container.getPokemonDetailUseCase)
            }
        }
    }
}