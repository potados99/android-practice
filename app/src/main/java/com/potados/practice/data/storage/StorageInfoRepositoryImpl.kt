package com.potados.practice.data.storage

import android.content.Context
import android.os.storage.StorageManager
import android.os.storage.StorageVolume
import com.potados.practice.MyApp

class StorageInfoRepositoryImpl : StorageInfoRepository {

    private val volumes: MutableList<StorageVolume> = ArrayList()
    private val uuids: MutableList<String> = ArrayList()

    init {
        update()
    }

    override fun update() {
        val mgr = MyApp.context.getSystemService(Context.STORAGE_SERVICE) as StorageManager
        val vols = mgr.storageVolumes.filter { it != null }

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