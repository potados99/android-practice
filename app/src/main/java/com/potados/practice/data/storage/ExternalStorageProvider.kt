package com.potados.practice.data.storage

import android.os.storage.StorageVolume
import java.io.File
import java.nio.file.Paths

class ExternalStorageProvider(private val repository: ExternalStorageRepository) {

    fun updateRepository() = repository.update()

    val mountRoot: String
        get() = repository.mountRoot

    val volumes: List<StorageVolume>
        get() = repository.getVolumes()

    val uuids: List<String>
        get() = repository.getUuids()

    val theOnlyOneAndGoodVolume: StorageVolume?
        get() = if (volumes.size == 1) volumes[0] else null

    val theOnlyOneAndGoodVolumePath: String?
        get() = theOnlyOneAndGoodVolume?.let {
             File(mountRoot + File.separator + it.uuid).absolutePath
        }
}