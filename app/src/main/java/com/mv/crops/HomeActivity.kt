package com.mv.crops

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        Thread.sleep(10000)
        setTheme(R.style.Theme_Crops)

        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_home)
    }
}