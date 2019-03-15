package com.potados.practice.data.movie

import android.graphics.drawable.Drawable
import com.potados.practice.MyApp
import com.potados.practice.R
import com.potados.practice.data.storage.ExternalStorageProvider
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.io.File
import kotlin.collections.ArrayList

/**
 * Manipulation of BGMovie.
 */
class BGMovieDescriptor(private val movie: BGMovie?) : KoinComponent {

    val storageProvider: ExternalStorageProvider by inject()

    val isValid: Boolean
        get() {
            /**
             * Conditions where movie is not valid:
             * 1. BGMovie instance is null.
             * 2. Id is under zero.
             * 3. File pointed by the instance not exists.
             * 4. The header of file is invalid. (UUID not match)
             */

            if (isNull) return false
            if (id < 0) return false
            if (!file.exists()) return false

            /* TODO: Check UUID at file header. */

            return true
        }

    val isNull: Boolean
        get() = (movie == null)

    fun assertNonNull(): BGMovieDescriptor =
        if (isNull) BGMovieDescriptor(InvalidBGMovie.nullObject(-99))
        else this

    /**
     * Default properties of BGMovie.
     */
    val id: Int get() = movie?.id ?: NULL_INT
    val title: String get() = movie?.title ?: NULL_STRING
    val description: String get() = movie?.description ?: NULL_STRING
    val filename: String get() = movie?.filename ?: NULL_STRING
    val duration: Int get() = movie?.duration ?: NULL_INT

    val fieldsMap: List<Map<String,String>>
        get() = ArrayList<Map<String,String>>().apply{
                    add(mapOf(Pair("ID", id.toString())))
                    add(mapOf(Pair("Title", title)))
                    add(mapOf(Pair("Description", description)))
                    add(mapOf(Pair("Filename", filename)))
                    add(mapOf(Pair("Duration", "$duration seconds")))
        }

    val thumbNail: Drawable
        get() {
            return MyApp.context.getDrawable(R.drawable.forest) ?: MyApp.context.getDrawable(R.drawable.nullsorry)!!
        }

    private val file: File = File(
        storageProvider.theOnlyOneAndGoodVolumePath +
                File.separator +
                MOVIE_DIR +
                File.separator +
                filename)

    val playableFile: File? get() =
        if (isValid) file else null

    fun makePlayable(playable: Boolean): Boolean {
        if (!isValid) return false

        /* TODO: Something to make this file playable */

        return true
    }

    companion object {
        const val NULL_INT = 0
        const val NULL_STRING = "NULL"
        const val MOVIE_DIR = "/movies"
    }
}