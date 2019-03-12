package com.potados.practice.util

import android.widget.Toast
import com.potados.practice.MyApp

class Alert {
    companion object {
        fun toast(msg: String) {
            Toast.makeText(MyApp.context, msg, Toast.LENGTH_SHORT).show()
        }
    }
}