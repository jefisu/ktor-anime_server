package com.jefisu.di

import com.jefisu.data.repository.AnimeRepositoryImpl
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {
    single {
        KMongo.createClient()
            .coroutine
            .getDatabase("anime_db")
    }

    single {
        AnimeRepositoryImpl(get())
    }
}