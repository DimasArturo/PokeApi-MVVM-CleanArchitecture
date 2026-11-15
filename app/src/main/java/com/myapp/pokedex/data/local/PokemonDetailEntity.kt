package com.myapp.pokedex.data.local
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.myapp.pokedex.domain.model.PokemonDetail

@Entity(tableName = "pokemon_detail")
data class PokemonDetailEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
    val height: Int,
    val weight: Int,
    val types: String
) {
    fun toDomain() = PokemonDetail(
        id = id,
        name = name,
        imageUrl = imageUrl,
        height = height,
        weight = weight,
        types = types.split("|")
    )

    companion object {
        fun fromDomain(detail: PokemonDetail) = PokemonDetailEntity(
            id = detail.id,
            name = detail.name,
            imageUrl = detail.imageUrl,
            height = detail.height,
            weight = detail.weight,
            types = detail.types.joinToString("|")
        )
    }
}