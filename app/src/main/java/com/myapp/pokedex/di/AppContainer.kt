package com.myapp.pokedex.di

import androidx.room3.Room
import com.myapp.pokedex.PokedexApp
import com.myapp.pokedex.data.local.PokemonDatabase
import com.myapp.pokedex.data.remote.api.PokeApiService
import com.myapp.pokedex.data.repository.PokemonRepositoryImpl
import com.myapp.pokedex.domain.repository.PokemonRepository
import com.myapp.pokedex.domain.usecase.GetPokemonDetailUseCase
import com.myapp.pokedex.domain.usecase.GetPokemonListUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

class AppContainer(app: PokedexApp) {
    private val database = Room.databaseBuilder(
        app.applicationContext,
        PokemonDatabase::class.java,
        "pokedex.db"
    ).build()
    private val pokemonDao = database.pokemonDao()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: PokeApiService = retrofit.create(PokeApiService::class.java)

    private val repository: PokemonRepository = PokemonRepositoryImpl(api, pokemonDao)

    val getPokemonListUseCase = GetPokemonListUseCase(repository)
    val getPokemonDetailUseCase = GetPokemonDetailUseCase(repository)
}