package com.gedehari.thethoughtmachine.network

import com.gedehari.thethoughtmachine.data.Thought
import com.squareup.moshi.FromJson
import java.time.Instant

class ThoughtJson (
    val id: Int,
    val title: String,
    val author: String,
    val message: String,
    val createdAt: String
)

class ThoughtJsonAdapter {
    @FromJson
    fun thoughtFromJson(thoughtJson: ThoughtJson): Thought {
        return Thought(
            thoughtJson.id,
            thoughtJson.title,
            thoughtJson.author,
            thoughtJson.message,
            Instant.parse(thoughtJson.createdAt).toEpochMilli()
        )
    }
}
