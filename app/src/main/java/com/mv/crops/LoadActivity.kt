package com.mv.crops

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.google.android.gms.tasks.Tasks.withTimeout

class LoadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)

        val timer = object: CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                return
            }
            override fun onFinish() {
                changeActivity();
            }
        }
        timer.start()

    }

    fun changeActivity() {
        val intent = Intent(this, NewUserActivity::class.java)
        startActivity(intent)
    }

}