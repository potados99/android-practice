package com.potados.practice

import android.app.Application
import android.content.Context
import android.content.Intent
import com.potados.practice.data.movie.BGMovieProvider
import com.potados.practice.data.storage.ExternalStorageProvider
import com.potados.practice.di.appModule
import com.potados.practice.ui.ErrorActivity
import com.potados.practice.util.LineReader
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin

class MyApp : Application() {
    companion object {
        lateinit var context: Context
    }

    private val movieProvider: BGMovieProvider by inject()
    private val storageProvider: ExternalStorageProvider by inject()

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        startKoin(this, listOf(appModule))

        checkFiles()
    }

    private fun checkFiles() {
        /**
         * Conditions not to run:
         * 1. OTG storage not exist.
         * 2. Data file not exist.
         *
         * 3. Video file not exist.
         * 4. Video file exist but invalid.
         */

        var okayToGo = true
        var msg = ""

        if (storageProvider.volumes.isEmpty()) {
            msg = "저장소가 연결되지 않았습니다."
            okayToGo = false
        }
        else if (storageProvider.volumes.size > 1) {
            msg = "저장소를 하나만 연결해 주세요."
            okayToGo = false
        }
        else if (LineReader(R.raw.movies).fileExists.not()) {
            msg = "데이터 파일이 존재하지 않아 계속할 수 없습니다."
            okayToGo = false
        }
        else if (movieProvider.isAllValid.not()) {
            msg = "파일이 변조되어 계속할 수 없습니다."
            okayToGo = false
        }

        if (!okayToGo) {
            val intent = Intent(this, ErrorActivity::class.java).apply {
                putExtra("MSG", msg)
            }
            startActivity(intent)
        }
    }
}