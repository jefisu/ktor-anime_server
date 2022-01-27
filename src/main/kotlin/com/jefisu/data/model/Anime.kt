package com.jefisu.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Anime(
    val name: String,
    val timestamp: String,
    val date: String,
    val lastModify: String? = null,
    @BsonId val id: String = ObjectId().toString(),
)