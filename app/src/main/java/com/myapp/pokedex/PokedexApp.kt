package com.myapp.pokedex

import android.app.Application
import com.myapp.pokedex.di.AppContainer

class PokedexApp : Application() {
    val container = AppContainer()
}