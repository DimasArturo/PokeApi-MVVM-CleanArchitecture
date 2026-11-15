package com.myapp.pokedex.data.local

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon ORDER BY id LIMIT :limit")
    suspend fun getPokemonList(limit: Int): List<PokemonEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemons: List<PokemonEntity>)

    @Query("SELECT * FROM pokemon_detail WHERE id = :id")
    suspend fun getPokemonDetail(id: Int): PokemonDetailEntity?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(detail: PokemonDetailEntity)
}