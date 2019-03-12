package com.potados.practice.data.movie

import android.graphics.drawable.Drawable
import com.potados.practice.MyApp
import com.potados.practice.R
import com.potados.practice.util.OTGStorage
import java.io.File
import java.nio.file.Path
import java.time.Duration
import kotlin.collections.ArrayList

/**
 * Manipulation of BGMovie.
 */
class BGMovieDescriptor(private val movie: BGMovie?) {

    val isValid: Boolean
        get() {
            /**
             * Conditions where movie is not valid:
             * 1. BGMovie instance is null.
             * 2. File pointed by the instance not exists.
             * 3. The header of file is invalid. (UUID not match)
             */

            if (isNull) return false
            if (id < 0) return false

            val movieFile = File(File(OTGStorage.getVolumes()[0]), filename)
            // if (!movieFile.exists()) return false

            /* TODO: Check UUID at file header. */

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

    fun makePlayable(playable: Boolean): Boolean {
        if (!isValid) return false

        val movieFile = File(File(OTGStorage.getVolumes()[0]), movie?.filename)

        /* TODO: Something to make this file playable */

        return true
    }

    companion object {
        const val NULL_INT = 0
        const val NULL_STRING = "NULL"
    }
}