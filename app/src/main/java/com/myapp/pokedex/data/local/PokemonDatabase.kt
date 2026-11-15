package com.myapp.pokedex.data.local

import androidx.room3.Database
import androidx.room3.RoomDatabase

@Database(
    entities = [PokemonEntity::class, PokemonDetailEntity::class],
    version = 2,
    exportSchema = false
)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}