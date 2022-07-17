package com.mv.crops

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class HectaresWorkedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hectares_worked)

        val txt_hectareas_trabajadas = findViewById<EditText>(R.id.hectares_worked_txt_amount)
        val boton_anadir = findViewById<Button>(R.id.hectares_worked_btn_anadir)

        boton_anadir.setOnClickListener {
            try {
                val hectareas_trabajadas = txt_hectareas_trabajadas.text.toString()
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }

    }
}