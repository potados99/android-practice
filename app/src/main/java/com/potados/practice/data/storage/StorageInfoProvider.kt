package com.potados.practice.data.storage

import android.os.storage.StorageVolume

class StorageInfoProvider(private val repository: StorageInfoRepository) {
    val volumes: List<StorageVolume>
        get() = repository.getVolumes()

    val uuids: List<String>
        get() = repository.getUuids()

    fun hasUuid(uuid: String): Boolean {
        return uuid in uuids
    }

    fun updateRepository() = repository.update()
}