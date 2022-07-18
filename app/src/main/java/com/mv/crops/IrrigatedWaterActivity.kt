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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class IrrigatedWaterActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val auth: FirebaseAuth = Firebase.auth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_irrigated_water)

        var cultivo = getIntent().getExtras()!!.getString("cultivo")

        val txt_cantidad_agua = findViewById<EditText>(R.id.irrigated_water_txt_amount)
        val txt_fecha = findViewById<EditText>(R.id.irrigated_water_txt_fecha)
        val boton_anadir = findViewById<Button>(R.id.irrigated_water_btn_anadir)
        val boton_regresar = findViewById<ImageView>(R.id.irrigated_water_btn_regresar)

        boton_regresar.setOnClickListener {
            val intent = Intent(this, CropActivity::class.java)
            intent.putExtra("cultivo",cultivo)
            startActivity(intent)
        }

        boton_anadir.setOnClickListener {
            try {
                val cantidad_agua = txt_cantidad_agua.text.toString()
                val fecha = txt_fecha.text.toString()

                if (cantidad_agua.isEmpty() || fecha.isEmpty()) throw Exception("Diligencie el formulario")

                db.collection("crops")
                    .document("${auth.currentUser!!.email}")
                    .collection("cultivos")
                    .document("${cultivo}")
                    .collection("agua_regada")
                    .add(
                        hashMapOf(
                            "cantidad" to cantidad_agua,
                            "fecha" to fecha
                        )
                    )
                    .addOnSuccessListener {
                        Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!")
                        Toast.makeText(
                            baseContext, "Agua regada guardada correctamente",
                            Toast.LENGTH_LONG
                        ).show()
                        txt_cantidad_agua.setText(null)
                        txt_fecha.setText(null)
                    }
                    .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error al guardar en BD", e) }
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }
        txt_fecha.setOnClickListener{
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment{ day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun onDateSelected(day: Int, month: Int, year: Int) {
        findViewById<EditText>(R.id.irrigated_water_txt_fecha).setText("${day}/${month + 1}/${year}")
    }
}