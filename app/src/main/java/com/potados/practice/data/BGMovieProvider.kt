package com.potados.practice.data

class BGMovieProvider(private val repository: BGMovieRepository) {
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