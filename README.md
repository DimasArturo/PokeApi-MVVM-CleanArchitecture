# PokeApi-MVVM-CleanArchitecture

Aplicación Android para consultar la PokeAPI y explorar información de Pokémon, incluyendo:
- lista de Pokémon con sprites
- búsqueda por nombre
- detalle individual del Pokémon
- almacenamiento local para mejorar la experiencia y reducir llamadas repetidas

La app está construida con Kotlin, Jetpack Compose, MVVM y Clean Architecture, usando inyección de dependencias manual a través de `AppContainer`.

## Arquitectura general

El proyecto sigue una estructura basada en capas:

- `data`: acceso a datos remotos y locales, mappers y repositorios
- `domain`: modelos de negocio, casos de uso y contratos de repositorio
- `presentation`: pantallas, estados UI y ViewModels
- `ui/theme`: configuración visual de la app con Material 3
- `di`: contenedor de dependencias de la aplicación

## Estructura de carpetas

```text
pokedex/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── AndroidManifest.xml
│   │   │   ├── java/com/myapp/pokedex/
│   │   │   │   ├── data/
│   │   │   │   │   ├── local/
│   │   │   │   │   │   ├── PokemonDatabase.kt
│   │   │   │   │   │   ├── PokemonDao.kt
│   │   │   │   │   │   ├── PokemonEntity.kt
│   │   │   │   │   │   └── PokemonDetailEntity.kt
│   │   │   │   │   ├── mapper/
│   │   │   │   │   │   └── PokemonMapper.kt
│   │   │   │   │   ├── remote/
│   │   │   │   │   │   ├── api/
│   │   │   │   │   │   │   └── PokeApiService.kt
│   │   │   │   │   │   └── dto/
│   │   │   │   │   │       ├── PokemonDto.kt
│   │   │   │   │   │       └── PokemonDetailDto.kt
│   │   │   │   │   └── repository/
│   │   │   │   │       └── PokemonRepositoryImpl.kt
│   │   │   │   ├── domain/
│   │   │   │   │   ├── model/
│   │   │   │   │   │   ├── Pokemon.kt
│   │   │   │   │   │   └── PokemonDetail.kt
│   │   │   │   │   ├── repository/
│   │   │   │   │   │   └── PokemonRepository.kt
│   │   │   │   │   └── usecase/
│   │   │   │   │       ├── GetPokemonListUseCase.kt
│   │   │   │   │       └── GetPokemonDetailUseCase.kt
│   │   │   │   ├── presentation/
│   │   │   │   │   ├── navigation/
│   │   │   │   │   │   └── AppNavigation.kt
│   │   │   │   │   ├── pokemonlist/
│   │   │   │   │   │   ├── PokemonListScreen.kt
│   │   │   │   │   │   ├── PokemonListViewModel.kt
│   │   │   │   │   │   └── PokemonListUiState.kt
│   │   │   │   │   └── pokemondetail/
│   │   │   │   │       ├── PokemonDetailScreen.kt
│   │   │   │   │       ├── PokemonDetailViewModel.kt
│   │   │   │   │       └── PokemonDetailUiState.kt
│   │   │   │   ├── di/
│   │   │   │   │   └── AppContainer.kt
│   │   │   │   ├── ui/theme/
│   │   │   │   │   ├── Color.kt
│   │   │   │   │   ├── Theme.kt
│   │   │   │   │   └── Type.kt
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── PokedexApp.kt
│   │   │   │   └── ...
│   │   │   └── res/
│   │   │       ├── drawable/
│   │   │       ├── mipmap/
│   │   │       ├── values/
│   │   │       └── xml/
│   │   └── keepRules/
│   ├── build.gradle.kts
│   └── .gitignore
├── build.gradle.kts
├── settings.gradle.kts
├── gradle/
│   └── libs.versions.toml
├── gradlew
├── gradlew.bat
├── gradle.properties
├── local.properties
├── README.md
├── .gitignore
└── .idea/
```
## Librerías y dependencias principales

El proyecto usa las siguientes librerías y frameworks:

- Jetpack Compose
- Material 3
- AndroidX Core KTX
- Lifecycle + ViewModel
- Navigation Compose
- Retrofit 3
- Gson converter para Retrofit
- Room 3
- Kotlin Coroutines
- Coil 3 para carga de imágenes
- KSP (Kotlin Symbol Processing) para generación de código de Room
