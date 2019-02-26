package com.potados.practice.data

import android.graphics.drawable.Drawable
import com.potados.practice.MyApp
import com.potados.practice.R

class BGMovieDescriptor(private val movie: BGMovie?) {

    val targetMovie: BGMovie?
        get() = movie

    val isValid: Boolean
        get() {
            if (isNull) return false
            // others..

            return true
            TODO("Validation check here")
        }

    val isNull: Boolean
        get() = (movie == null)

    val fieldsList: List<String>
        get() = ArrayList<String>().apply {
            movie?.let {
                with(it) {
                    add("Title: $title")
                    add("Description: $description")
                    add("Duration: $duration")
                }
            }
        }

    val thumbNail: Drawable
        get() {
            return MyApp.context.getDrawable(R.drawable.forest) ?: MyApp.context.getDrawable(R.drawable.nullsorry)!!
            TODO("Get thumbnail from file (movie.filename)")
        }

    fun assertNonNull(): BGMovieDescriptor = if (isNull) BGMovieDescriptor(InvalidBGMovie.nullObject(-99)) else this

    override fun toString(): String = fieldsList.joinToString("\n")
}