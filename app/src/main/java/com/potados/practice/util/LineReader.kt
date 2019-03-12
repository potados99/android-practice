package com.potados.practice.util

import com.potados.practice.MyApp
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception

class LineReader(private val dataFileId: Int) {
    private val c = MyApp.context
    private val r = c.resources

    val fileExists: Boolean
        get() = try {
            r.openRawResource(dataFileId)
            true
        } catch (exception: Exception) {
            false
        }

    val lines: List<String>
        get() = try {
            val inputStream = r.openRawResource(dataFileId)
            val reader = BufferedReader(InputStreamReader(inputStream))

            reader.readLines()
        }
        catch (exception: Exception) {
            ArrayList() /* empty list */
        }

}