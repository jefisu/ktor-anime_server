package com.jefisu.routes

import com.jefisu.data.model.Anime
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.litote.kmongo.coroutine.CoroutineCollection

fun Route.postAnime(collection: CoroutineCollection<Anime>) {
    post("anime") {
        val requestBody = call.receive<Anime>()
        val actionResult = collection.insertOne(requestBody).wasAcknowledged()
        call.respond(HttpStatusCode.OK, actionResult)
    }
}

fun Route.putAnime(collection: CoroutineCollection<Anime>) {
    put("anime/{id}") {
        val id = call.parameters["id"] ?: call.respond(HttpStatusCode.NotFound)
        val update = call.receive<Anime>()
        val successfullyUpdated = collection.updateOneById(id = id, update = update).wasAcknowledged()
        call.respond(HttpStatusCode.OK, successfullyUpdated)
    }
}

fun Route.getAnimes(collection: CoroutineCollection<Anime>) {
    get("/anime/{id}") {
        val id = call.parameters["id"] ?: call.respond(HttpStatusCode.NotFound)
        val anime = collection.findOneById(id)
        anime?.let { call.respond(anime) }
    }
    get("/animes") {
        val animes = collection.find()
            .ascendingSort(Anime::name)
            .toList()
        call.respond(HttpStatusCode.OK, animes)
    }
}

fun Route.deleteAnime(collection: CoroutineCollection<Anime>) {
    delete("anime/{id}") {
        val id = call.parameters["id"] ?: call.respond(HttpStatusCode.NotFound)
        val anime = collection.deleteOneById(id).wasAcknowledged()
        call.respond(HttpStatusCode.OK, anime)
    }
}
