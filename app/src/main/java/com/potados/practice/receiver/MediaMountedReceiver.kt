package com.potados.practice.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Environment.MEDIA_MOUNTED
import android.os.storage.StorageVolume

class MediaMountedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent == null) return

        if (intent.action == MEDIA_MOUNTED) {
            //
            val volume = intent.getParcelableExtra<StorageVolume>(StorageVolume.EXTRA_STORAGE_VOLUME)
            volume.createAccessIntent("/")
        }
    }
}