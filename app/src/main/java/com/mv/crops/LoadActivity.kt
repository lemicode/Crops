package com.mv.crops

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import java.util.Timer
import kotlin.concurrent.schedule

class LoadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)

        Log.d(ContentValues.TAG,"listo")

        Timer("SettingUp", false).schedule(3000) {
            changeActivity()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyDown(keyCode, event)
    }

    fun changeActivity() {
        val intent = Intent(this, NewUserActivity::class.java)
        startActivity(intent)
    }

}