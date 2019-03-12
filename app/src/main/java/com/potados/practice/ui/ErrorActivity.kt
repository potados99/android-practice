package com.potados.practice.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import com.potados.practice.R
import kotlinx.android.synthetic.main.activity_error.*

class ErrorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)

        messageView.text = intent.getStringExtra("MSG")

        exitButton.setOnClickListener {
            ActivityCompat.finishAffinity(this)
            System.runFinalizersOnExit(true)
            System.exit(0)
        }
    }
}
