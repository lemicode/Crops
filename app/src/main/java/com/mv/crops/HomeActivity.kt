package com.mv.crops

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_home)
    }
}