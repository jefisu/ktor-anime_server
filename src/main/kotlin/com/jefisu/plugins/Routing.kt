package com.jefisu.plugins

import com.jefisu.data.model.Anime
import com.jefisu.routes.deleteAnime
import com.jefisu.routes.getAnimes
import com.jefisu.routes.postAnime
import com.jefisu.routes.putAnime
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import org.litote.kmongo.coroutine.CoroutineDatabase

fun Application.configureRouting() {

    val db by inject<CoroutineDatabase>()
    val collection = db.getCollection<Anime>()

    routing {
        postAnime(collection)
        putAnime(collection)
        getAnimes(collection)
        deleteAnime(collection)
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
    }
}
