package com.myapp.pokedex.data.local
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.myapp.pokedex.domain.model.Pokemon

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
){
    fun toDomain() = Pokemon(id, name, imageUrl)
    companion object {
        fun fromDomain(pokemon: Pokemon) = PokemonEntity(
            id = pokemon.id,
            name = pokemon.name,
            imageUrl = pokemon.imageUrl
        )
    }
}
