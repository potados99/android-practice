package com.potados.practice.util

import com.potados.practice.MyApp
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception

class LineReader(private val dataFileId: Int) {
    private val c = MyApp.context
    private val r = c.resources

    val lines: List<String>
        get() {
            var list: List<String>

            try {
                val inputStream = r.openRawResource(dataFileId)
                val reader = BufferedReader(InputStreamReader(inputStream))

                list = reader.readLines()
            }
            catch (exception: Exception) {
                list = ArrayList() /* empty list */
            }

            return list
        }
}