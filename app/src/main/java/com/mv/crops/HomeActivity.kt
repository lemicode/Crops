package com.mv.crops

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        Thread.sleep(3000)
        setTheme(R.style.Theme_Crops)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}