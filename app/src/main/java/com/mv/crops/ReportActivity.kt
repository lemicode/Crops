package com.mv.crops

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button

class ReportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()

        setContentView(R.layout.activity_report)

        var boton_balance = findViewById<Button>(R.id.report_btn_balance)
        var boton_clima = findViewById<Button>(R.id.report_btn_clima)

        var cultivo = getIntent().getExtras()!!.getString("cultivo")

        boton_balance.setOnClickListener {
            val intent = Intent(this, BalanceActivity::class.java)
            intent.putExtra("cultivo",cultivo)
            startActivity(intent)
        }

        boton_clima.setOnClickListener {
            val intent = Intent(this, WeatherActivity::class.java)
            intent.putExtra("cultivo",cultivo)
            startActivity(intent)
        }

    }
}