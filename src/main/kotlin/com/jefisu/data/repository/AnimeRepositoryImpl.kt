package com.jefisu.data.repository

import com.jefisu.data.model.Anime
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class AnimeRepositoryImpl(
    db: CoroutineDatabase
) : AnimeRepository {

    private val animeCol = db.getCollection<Anime>()

    override suspend fun insertAnime(anime: Anime): Boolean {
        return animeCol.insertOne(anime).wasAcknowledged()
    }

    override suspend fun updateAnime(anime: Anime): Boolean {
        return animeCol.updateOneById(id = anime.id, update = anime)
            .wasAcknowledged()
    }

    override suspend fun getAnimeById(id: String): Anime? {
        return animeCol.findOne(Anime::id eq id)
    }

    override suspend fun getAnimes(): List<Anime> {
        return animeCol.find()
            .descendingSort(Anime::id)
            .toList()
    }

    override suspend fun deleteAnime(anime: Anime): Boolean {
        return animeCol.deleteOneById(anime.id)
            .wasAcknowledged()
    }
}