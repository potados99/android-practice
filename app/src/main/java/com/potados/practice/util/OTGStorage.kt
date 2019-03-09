package com.potados.practice.util

import android.app.Application
import android.content.Context
import android.hardware.usb.UsbManager
import android.os.Environment
import android.os.storage.StorageManager
import android.os.storage.StorageVolume
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.nio.Buffer

object OTGStorage {

    // /mnt/media_rw/0FFC-2B18
    // /storage/emulated/0

    private val tokens = listOf("USBstorage1", "USBstorage2", "UsbDriveA", "UsbDriveB",
        "emulated") /* test */

    fun getVolumes(): List<String> = VolumeManager.getStorageSet().toList().filter {
        it.removePrefix("/storage/").split("/").first() in tokens
    }
}