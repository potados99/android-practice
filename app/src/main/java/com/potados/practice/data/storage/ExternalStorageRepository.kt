package com.potados.practice.data.storage

import android.os.storage.StorageVolume

interface ExternalStorageRepository {
    /**
     * Path where external USB drive is mounted.
     * Has separator at the begin, not at the end.
     * example: /storage
     */
    val mountRoot: String

    fun update()
    fun getVolumes(): List<StorageVolume>
    fun getUuids(): List<String>
}