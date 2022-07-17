package com.mv.crops

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class NewCropActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_new_crop)

        val txt_ubicacion = findViewById<EditText>(R.id.new_crop_txt_ubicacion)
        val txt_tipo_cultivo = findViewById<EditText>(R.id.new_crop_txt_tipo_cultivo)
        val txt_nombre_cultivo = findViewById<EditText>(R.id.new_crop_txt_nombre_cultivo)
        val txt_area_cultivo = findViewById<EditText>(R.id.new_crop_txt_area_cultivo)
        val txt_fecha_inicio = findViewById<EditText>(R.id.new_crop_txt_fecha_inicio)
        val boton_anadir = findViewById<Button>(R.id.new_crop_btn_anadir)

        val boton_regresar=findViewById<ImageView>(R.id.new_crop_btn_regresar)

        boton_regresar.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        boton_anadir.setOnClickListener {
            try {

                val ubicacion = txt_ubicacion.text.toString().lowercase()
                val tipo_cultivo = txt_tipo_cultivo.text.toString().lowercase()
                val nombre_cultivo = txt_nombre_cultivo.text.toString().lowercase()
                val area_cultivo = txt_area_cultivo.text.toString()
                val fecha_inicio = txt_fecha_inicio.text.toString()



            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }

        }

    }
}