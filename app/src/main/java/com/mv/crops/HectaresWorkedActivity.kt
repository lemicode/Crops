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

class HectaresWorkedActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val auth: FirebaseAuth = Firebase.auth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_hectares_worked)

        var cultivo = getIntent().getExtras()!!.getString("cultivo")

        val txt_hectareas_trabajadas = findViewById<EditText>(R.id.hectares_worked_txt_amount)
        val txt_fecha = findViewById<EditText>(R.id.hectares_worked_txt_fecha)
        val boton_anadir = findViewById<Button>(R.id.hectares_worked_btn_anadir)
        val boton_regresar = findViewById<ImageView>(R.id.hectares_worked_btn_regresar)

        boton_regresar.setOnClickListener {
            val intent = Intent(this, CropActivity::class.java)
            intent.putExtra("cultivo",cultivo)
            startActivity(intent)
        }

        boton_anadir.setOnClickListener {
            try {
                val hectareas_trabajadas = txt_hectareas_trabajadas.text.toString()
                val fecha = txt_fecha.text.toString()

                if (hectareas_trabajadas.isEmpty() || fecha.isEmpty()) throw Exception("Diligencie el formulario")

                db.collection("crops")
                    .document("${auth.currentUser!!.email}")
                    .collection("cultivos")
                    .document("${cultivo}")
                    .collection("hectareas_trabajadas")
                    .add(
                        hashMapOf(
                            "cantidad" to hectareas_trabajadas,
                            "fecha" to fecha
                        )
                    )
                    .addOnSuccessListener {
                        Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!")
                        Toast.makeText(
                            baseContext, "Hectáreas trabajadas guardadas correctamente",
                            Toast.LENGTH_LONG
                        ).show()
                        txt_hectareas_trabajadas.setText(null)
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
        findViewById<EditText>(R.id.hectares_worked_txt_fecha).setText("${day}/${month + 1}/${year}")
    }
}