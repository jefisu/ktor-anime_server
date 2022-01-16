package com.jefisu.routes

import com.jefisu.data.model.Anime
import com.jefisu.data.repository.AnimeRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.animeRoute(repository: AnimeRepository) {
    get("api/anime/{id}") {
        val id = call.parameters["id"] ?: call.respond(HttpStatusCode.NotFound)
        val anime = repository.getAnimeById(id.toString())
        anime?.let { call.respond(HttpStatusCode.OK, anime) }
    }
    get("api/animes") {
        call.respond(HttpStatusCode.OK, repository.getAnimes())
    }

    post("api/anime") {
        val anime = call.receive<Anime>()
        val result = repository.insertAnime(anime)
        call.respond(HttpStatusCode.OK, result)
    }

    put("api/anime") {
        val anime = call.receive<Anime>()
        val result = repository.updateAnime(anime)
        call.respond(HttpStatusCode.OK, result)
    }

    delete("api/anime") {
        val anime = call.receive<Anime>()
        val result = repository.deleteAnime(anime)
        call.respond(HttpStatusCode.OK, result)
    }
}