package com.potados.practice.data

import android.graphics.drawable.Drawable
import com.potados.practice.MyApp
import com.potados.practice.R
import java.util.*
import kotlin.collections.ArrayList

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

    val title: String
        get() = movie?.title ?: "NULL"

    val thumbNail: Drawable
        get() {
            return MyApp.context.getDrawable(R.drawable.forest) ?: MyApp.context.getDrawable(R.drawable.nullsorry)!!
            TODO("Get thumbnail from file (movie.filename)")
        }

    val fieldsMap: List<Map<String,String>>
        get() = ArrayList<Map<String,String>>().apply{
            movie?.let {
                with(it) {
                    add(mapOf(Pair("ID", id.toString())))
                    add(mapOf(Pair("Title", title)))
                    add(mapOf(Pair("Description", description)))
                    add(mapOf(Pair("Filename", filename)))
                    add(mapOf(Pair("Duration", "${duration.toMinutes()} minutes")))
                }
            }
        }

    fun assertNonNull(): BGMovieDescriptor = if (isNull) BGMovieDescriptor(InvalidBGMovie.nullObject(-99)) else this
}