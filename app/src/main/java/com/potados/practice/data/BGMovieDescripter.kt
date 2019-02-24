package com.potados.practice.data

class BGMovieDescripter(private val movie: BGMovie?) {
    fun getDetails(all: Boolean = false): List<String> =
        ArrayList<String>().apply{
            movie?.let {
                with(it) {
                    if (all) add("Title: $title")
                    add("Description: $description")
                    add("Duration: $duration")
                }
            }
        }

    fun getDetailsString(all: Boolean = false): String =
            getDetails(all).joinToString("\n")
}