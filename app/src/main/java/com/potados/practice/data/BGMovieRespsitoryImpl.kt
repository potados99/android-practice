package com.potados.practice.data

import java.time.Duration

class BGMovieRespsitoryImpl(val storagePath: String) : BGMovieRepository {

    val items_list: MutableList<BGMovie> = ArrayList() /* empty declaration. */
    val items_map: MutableMap<Int, BGMovie> = HashMap()

    init {
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
        return items_map[id] ?: EmptyBGMovie.fileNotFound
    }

    override fun getAllList(): List<BGMovie> {
        return items_list
    }

    override fun getAllMap(): Map<Int, BGMovie> {
        return items_map
    }

    private fun addItem(item: BGMovie) {
        items_list.add(item)
        items_map[item.id] = item
    }

}

/**
 * Null alternative.
 */
object EmptyBGMovie {
    val fileNotFound = BGMovie(id = -1, title = "Movie not found")
    val contentInvalid = BGMovie(id = -2, title = "Content invalid.")
}