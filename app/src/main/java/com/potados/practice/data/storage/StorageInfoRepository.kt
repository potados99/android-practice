package com.potados.practice.data.storage

import android.os.storage.StorageVolume

interface StorageInfoRepository {
    fun update()
    fun getVolumes(): List<StorageVolume>
    fun getUuids(): List<String>
}