package com.jefisu.plugins

import com.jefisu.data.repository.AnimeRepositoryImpl
import com.jefisu.routes.animeRoute
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val repository by inject<AnimeRepositoryImpl>()

    routing {
        animeRoute(repository)
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
    }
}
