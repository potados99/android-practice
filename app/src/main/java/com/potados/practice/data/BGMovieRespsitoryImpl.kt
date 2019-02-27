package com.potados.practice.data

import java.time.Duration

class BGMovieRepositoryImpl(val storagePath: String) : BGMovieRepository {

    private val itemsList: MutableList<BGMovie> = ArrayList() /* empty declaration. */
    private val itemsMap: MutableMap<Int, BGMovie> = HashMap()

    init {
        update()
    }

    override fun update() {
        clear()
        getMoviesFromStorage()
    }

    override fun getById(id: Int): BGMovie {
        return itemsMap[id] ?: InvalidBGMovie.nullObject(id)
    }

    override fun getAllList(): List<BGMovie> {
        return itemsList
    }

    override fun getAllMap(): Map<Int, BGMovie> {
        return itemsMap
    }

    private fun clear() {
        itemsList.clear()
        itemsMap.clear()
    }

    private fun addItem(item: BGMovie) {
        itemsList.add(item)
        itemsMap[item.id] = item
    }

    private fun getMoviesFromStorage() {
        for (i in 1..20) {
            addItem(BGMovie(
                id = i,
                title = "Item with id $i",
                description = "Test dummy data at $i!",
                filename = "blah_blah_at_$i.Bagua",
                duration = Duration.ofSeconds(300))
            )
        }
    }
}

object InvalidBGMovie {
    private fun invalidObject(id: Int, description: String) =
        BGMovie(
            id = id,
            title = "INVALID",
            description = "id $id: $description",
            filename = "no filename",
            duration = Duration.ZERO
        )

    fun wrongId(originId: Int): BGMovie = invalidObject(originId, "ID is null.")
    fun nullObject(originId: Int): BGMovie = invalidObject(originId, "Object is null.")
    fun fileNotFound(originId: Int): BGMovie = invalidObject(originId, "File is not found.")
    fun contentInvalid(originId: Int): BGMovie = invalidObject(originId, "Content is invalid.")
    fun notInitialized(originId: Int): BGMovie = invalidObject(originId, "Object not initialized yet.")

}