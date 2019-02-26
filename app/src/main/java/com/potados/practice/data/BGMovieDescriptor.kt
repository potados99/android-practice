package com.potados.practice.data

class BGMovieDescriptor(private val movie: BGMovie?) {
    val details: List<String>
        get() = ArrayList<String>().apply{
            movie?.let {
                with(it) {
                    add("Title: $title")
                    add("Description: $description")
                    add("Duration: $duration")
                }
            }
        }

    override fun toString(): String = details.joinToString("\n")
}