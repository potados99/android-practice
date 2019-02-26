package com.potados.practice.data

class BGMovieProvider(private val repository: BGMovieRepository) {
    val moviesInList: List<BGMovie>
        get() = repository?.getAllList().filter {
            BGMovieDescriptor(it).isValid /* heavy but sexy */
        }

    val moviesInMap: Map<Int, BGMovie>
        get() = repository?.getAllMap().filter {
            BGMovieDescriptor(it.value).isValid /* huh? */
        }

    fun updateRepository() = repository.update()
}