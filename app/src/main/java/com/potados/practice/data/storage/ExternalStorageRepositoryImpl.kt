package com.potados.practice.data.storage

import android.content.Context
import android.os.storage.StorageManager
import android.os.storage.StorageVolume
import com.potados.practice.MyApp
import java.io.File

class ExternalStorageRepositoryImpl : ExternalStorageRepository {

    private val volumes: MutableList<StorageVolume> = ArrayList()
    private val uuids: MutableList<String> = ArrayList()

    init {
        update()
    }

    override val mountRoot: String
        get() = "/storage"

    override fun update() {
        val mgr = MyApp.context.getSystemService(Context.STORAGE_SERVICE) as StorageManager
        val vols = mgr.storageVolumes.filter {
            File(mountRoot + File.separator + it.uuid).exists() /* path exists. */
        }

        volumes.apply {
            clear()
            addAll(vols) /* volume itself. */
        }

        uuids.apply {
            clear()
            addAll(vols.map { it.uuid }.filter { it != null }) /* uuid of volume. not null. */
        }
    }

    override fun getVolumes(): List<StorageVolume> {
        return volumes
    }

    override fun getUuids(): List<String> {
        return uuids
    }
}