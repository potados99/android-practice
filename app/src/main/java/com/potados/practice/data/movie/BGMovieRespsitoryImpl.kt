package com.potados.practice.data.movie

import com.potados.practice.R
import com.potados.practice.util.LineReader
import java.lang.Exception
import java.time.Duration

/**
 * Provide collection of BGMovie, based on data file.
 * NO validation check here!
 */
class BGMovieRepositoryImpl : BGMovieRepository {

    private val itemsList: MutableList<BGMovie> = ArrayList() /* empty declaration. */
    private val itemsMap: MutableMap<Int, BGMovie> = HashMap()

    init {
        update()
    }

    override fun update() {
        clear()
        getFromDataFile()
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

    private fun getFromDataFile() {
        LineReader(R.raw.movies).lines.forEach { line ->
            val tokens = line.split(',').map { eachPart -> eachPart.trim() }

            try {
                addItem(
                    BGMovie(
                        id = tokens[0].toInt(),
                        title = tokens[1],
                        description = "Descriptions?",
                        filename = tokens[2],
                        duration = 0
                    )
                )
            }
            catch(exception: Exception) {
                addItem(InvalidBGMovie.dataRowInvalid(-1))
            }
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
            duration = 0
        )

    fun wrongId(originId: Int): BGMovie =
        invalidObject(originId, "ID is null.")
    fun nullObject(originId: Int): BGMovie =
        invalidObject(originId, "Object is null.")
    fun fileNotFound(originId: Int): BGMovie =
        invalidObject(originId, "File is not found.")
    fun contentInvalid(originId: Int): BGMovie =
        invalidObject(originId, "Content is invalid.")
    fun notInitialized(originId: Int): BGMovie =
        invalidObject(originId, "Object not initialized yet.")
    fun dataRowInvalid(originId: Int): BGMovie =
        invalidObject(originId, "Data file has invalid row.")
}
