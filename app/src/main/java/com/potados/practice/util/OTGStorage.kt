package com.potados.practice.util

import android.app.Application
import android.content.Context
import android.hardware.usb.UsbManager
import android.os.Environment
import android.os.storage.StorageManager
import android.os.storage.StorageVolume
import android.support.v4.content.ContextCompat.getSystemService
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.nio.Buffer

class OTGStorage {
    fun getVolumes(): List<StorageVolume>? {
        val context = Application()?.applicationContext
        val manager = context?.getSystemService(StorageManager::class.java)

        return manager?.storageVolumes
    }
}