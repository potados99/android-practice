package com.potados.practice.data.movie

import com.potados.practice.util.OTGStorage
import java.io.File

/**
 * Provide qualified collection of BGMovie.
 * Check whole validity using isAllValid.
 */
class BGMovieProvider(private val repository: BGMovieRepository) {

    val isAllValid: Boolean
        get() = (repository.getAllList().filter {
            BGMovieDescriptor(it).isValid.not()
        }.size) == 0

    val moviesInList: List<BGMovie>
        get() = repository.getAllList().filter {
            BGMovieDescriptor(it).isValid /* heavy but sexy */
        }

    val moviesInMap: Map<Int, BGMovie>
        get() = repository.getAllMap().filter {
            BGMovieDescriptor(it.value).isValid /* huh? */
        }

    fun getMovieById(id: Int?): BGMovie {
        return if (id == null) InvalidBGMovie.wrongId(0) else repository.getById(id)
    }

    fun updateRepository() = repository.update()
}