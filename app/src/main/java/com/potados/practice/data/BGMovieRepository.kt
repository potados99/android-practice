package com.potados.practice.data

interface BGMovieRepository {
    fun update()
    fun getById(id: Int): BGMovie
    fun getAllList(): List<BGMovie> /* Immutable! */
    fun getAllMap(): Map<Int, BGMovie> /* Also immutable. */
}