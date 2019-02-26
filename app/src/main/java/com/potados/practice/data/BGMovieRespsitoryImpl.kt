package com.potados.practice.data

class BGMovieRepositoryImpl(val storagePath: String) : BGMovieRepository {

    private val itemsList: MutableList<BGMovie> = ArrayList() /* empty declaration. */
    private val itemsMap: MutableMap<Int, BGMovie> = HashMap()

    init {
        update()
    }

    override fun update() {
        clear()
        for (i in 1..20) {
            addItem(BGMovie(
                id = i,
                title = "Item with id $i",
                description = "Test dummy data at $i!",
                filename = "blah_blah_at_$i.Bagua")
            )
        }
    }

    override fun getById(id: Int): BGMovie {
        return itemsMap[id] ?: EmptyBGMovie.fileNotFound
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

}

/**
 * Null alternative.
 */
object EmptyBGMovie {
    val fileNotFound = BGMovie(id = -1, title = "Movie not found")
    val contentInvalid = BGMovie(id = -2, title = "Content invalid.")
}