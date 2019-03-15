package com.potados.practice.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Environment.MEDIA_MOUNTED
import android.os.storage.StorageVolume
import com.potados.practice.util.Alert

class MediaMountedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent == null) return

        if (intent.action == MEDIA_MOUNTED) {
            Alert.toast("새로운 기기가 연결되었습니다. 앱을 다시 실행해주세요.")
        }
    }
}