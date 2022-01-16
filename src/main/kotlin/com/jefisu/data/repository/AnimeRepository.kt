package com.jefisu.data.repository

import com.jefisu.data.model.Anime

interface AnimeRepository {

    suspend fun insertAnime(anime: Anime): Boolean

    suspend fun updateAnime(anime: Anime): Boolean

    suspend fun getAnimeById(id: String): Anime?

    suspend fun getAnimes(): List<Anime>

    suspend fun deleteAnime(anime: Anime): Boolean
}