package com.potados.practice.data

import java.time.Duration

/**
 * Immutable data class
 * BG is BaguaZhang.
 */
data class BGMovie(
    val id: Int,
    val title: String,
    val description: String? = null,
    val filename: String? = null,
    val duration: Duration? = null
)