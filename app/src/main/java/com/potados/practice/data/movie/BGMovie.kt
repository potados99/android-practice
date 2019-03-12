package com.potados.practice.data.movie

import java.time.Duration

/**
 * Immutable data class
 * BG is BaguaZhang.
 */
data class BGMovie(
    val id: Int,
    val title: String,
    val description: String,
    val filename: String,
    val duration: Duration
)