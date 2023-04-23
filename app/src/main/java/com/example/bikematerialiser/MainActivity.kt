package com.example.bikematerialiser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textTitle: TextView = findViewById<TextView>(R.id.textTitle)
        textTitle.animate().apply {
            duration = 0
            rotationYBy(10f)
        }.start()
    }
}