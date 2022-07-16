package com.mv.crops

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.Timer
import kotlin.concurrent.schedule

class LoadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)
        Timer("SettingUp", false).schedule(3000) {
            changeActivity()
        }
    }

    fun changeActivity() {
        val intent = Intent(this, NewUserActivity::class.java)
        startActivity(intent)
    }

}