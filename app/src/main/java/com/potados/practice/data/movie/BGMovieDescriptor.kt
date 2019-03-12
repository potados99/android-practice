package com.potados.practice.data.movie

import android.graphics.drawable.Drawable
import com.potados.practice.MyApp
import com.potados.practice.R
import java.time.Duration
import kotlin.collections.ArrayList

class BGMovieDescriptor(private val movie: BGMovie?) {

    val isValid: Boolean
        get() {
            if (isNull) return false
            // others..

            return true
        }

    val isNull: Boolean
        get() = (movie == null)

    /**
     * Default properties of BGMovie.
     */
    val id: Int get() = movie?.id ?: NULL_INT
    val title: String get() = movie?.title ?: NULL_STRING
    val description: String get() = movie?.description ?: NULL_STRING
    val filename: String get() = movie?.filename ?: NULL_STRING
    val duration: Duration get() = movie?.duration ?: Duration.ZERO

    val thumbNail: Drawable
        get() {
            return MyApp.context.getDrawable(R.drawable.forest) ?: MyApp.context.getDrawable(R.drawable.nullsorry)!!
        }

    val fieldsMap: List<Map<String,String>>
        get() = ArrayList<Map<String,String>>().apply{
                    add(mapOf(Pair("ID", id.toString())))
                    add(mapOf(Pair("Title", title)))
                    add(mapOf(Pair("Description", description)))
                    add(mapOf(Pair("Filename", filename)))
                    add(mapOf(Pair("Duration", "${duration.toMinutes()} minutes")))
        }

    fun assertNonNull(): BGMovieDescriptor =
        if (isNull) BGMovieDescriptor(
            InvalidBGMovie.nullObject(
                -99
            )
        ) else this

    companion object {
        const val NULL_INT = 0
        const val NULL_STRING = "NULL"
    }
}