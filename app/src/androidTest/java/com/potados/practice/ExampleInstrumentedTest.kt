package com.potados.practice

import android.os.Environment
import android.os.storage.StorageManager
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.potados.practice.util.LineReader
import com.potados.practice.util.OTGStorage
import com.potados.practice.util.VolumeManager

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        LineReader(R.raw.movies).lines.forEach {
            Log.i("Hey Look: ", it)
        }

    }
}
