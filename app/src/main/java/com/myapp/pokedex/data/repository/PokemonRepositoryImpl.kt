package com.myapp.pokedex.data.repository

import com.myapp.pokedex.data.local.PokemonDao
import com.myapp.pokedex.data.local.PokemonDetailEntity
import com.myapp.pokedex.data.local.PokemonEntity
import com.myapp.pokedex.data.mapper.toDomain
import com.myapp.pokedex.data.remote.api.PokeApiService
import com.myapp.pokedex.domain.model.Pokemon
import com.myapp.pokedex.domain.model.PokemonDetail
import com.myapp.pokedex.domain.repository.PokemonRepository
import java.io.IOException
import retrofit2.HttpException

class PokemonRepositoryImpl(
    private val api: PokeApiService,
    private val pokemonDao: PokemonDao
) : PokemonRepository {

    override suspend fun getPokemonList(limit: Int): List<Pokemon> {
        val pokemons = try {
            api.getPokemonList(limit).results.map { it.toDomain() }
        } catch (e: IOException) {
            val cached = pokemonDao.getPokemonList(limit).map { it.toDomain() }
            if (cached.isEmpty()) throw e
            return cached
        } catch (e: HttpException) {
            val cached = pokemonDao.getPokemonList(limit).map { it.toDomain() }
            if (cached.isEmpty()) throw e
            return cached
        }
        pokemonDao.insertAll(pokemons.map { PokemonEntity.fromDomain(it) })
        return pokemons
    }

    override suspend fun getPokemonDetail(id: Int): PokemonDetail {
        val detail = try {
            api.getPokemonDetail(id).toDomain()
        } catch (e: IOException) {
            return pokemonDao.getPokemonDetail(id)?.toDomain() ?: throw e
        } catch (e: HttpException) {
            return pokemonDao.getPokemonDetail(id)?.toDomain() ?: throw e
        }
        pokemonDao.insertDetail(PokemonDetailEntity.fromDomain(detail))
        return detail
    }
}