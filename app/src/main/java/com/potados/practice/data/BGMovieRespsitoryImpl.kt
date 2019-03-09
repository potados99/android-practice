package com.potados.practice.data

import android.content.Context
import android.content.res.Resources
import android.os.storage.StorageManager
import android.os.storage.StorageVolume
import com.potados.practice.MyApp
import com.potados.practice.R
import java.time.Duration

class BGMovieRepositoryImpl() : BGMovieRepository {

    private val itemsList: MutableList<BGMovie> = ArrayList() /* empty declaration. */
    private val itemsMap: MutableMap<Int, BGMovie> = HashMap()

    init {
        update()
    }

    override fun update() {
        clear()
        getFromResource()
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

    private fun getFromResource() {
        for (i in contentStartId..contentsCount) {
            addItem(
                BGMovie(
                    id = i,
                    title = getStringByIdString("id_${i}_type_plane"),
                    description = "Hey $i square is ${i*i}.",
                    filename = "${getStringByIdString("id_${i}_type_encrypted")}.Bagua",
                    duration = Duration.ofSeconds(300)
                )
            )
        }

        addItem(
            BGMovie(
                id = 99,
                title = "** Under are storage volumes** ",
                description = "hi.",
                filename = "d",
                duration = Duration.ZERO
            )
        )

        val storageMgr = MyApp.context.getSystemService(Context.STORAGE_SERVICE) as StorageManager
        val vols = storageMgr.storageVolumes

        for (v in vols) {
            addItem(
                BGMovie(
                    id = v.hashCode() % 1000,
                    title = v.uuid ?: "UUID unresolved.",
                    description =
                    (v.getDescription(MyApp.context) ?: "description unresolved") + ", " +
                    (if (v.isEmulated) "emulated" else "not emulated") + ", " +
                    (if (v.isPrimary) "primary" else "not primary") + ", " +
                    (if (v.isRemovable) "removable" else "not removable") + ", " +
                    v.state,
                    filename = "d",
                    duration = Duration.ZERO
                )
            )
        }
    }

    private fun getStringByIdString(id: String): String {
        val context = MyApp.context
        val pack = context.packageName
        val res = context.resources

        with(res) {
            return res.getString(
                getIdentifier(id, "string", pack)
            )
        }
    }

    private val contentStartId: Int
        get() = MyApp.context.resources.getInteger(R.integer.content_start_id)

    private val contentsCount: Int
        get() = MyApp.context.resources.getInteger(R.integer.contents_count)
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
