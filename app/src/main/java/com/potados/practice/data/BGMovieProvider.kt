package com.potados.practice.data

class BGMovieProvider(private val repository: BGMovieRepository) {
    fun updateRepository() = repository.update()

    fun getValidList(): List<BGMovie>? {
        return repository?.getAllList()
        TODO("Validation check here. Return only playable contents.")
    }
    fun getValidMap(): Map<Int, BGMovie>? {
        return repository?.getAllMap()
        TODO("Validation check here. Return only playable contents.")
    }
}