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

class HoursWorkedActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val auth: FirebaseAuth = Firebase.auth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_hours_worked)

        var cultivo = getIntent().getExtras()!!.getString("cultivo")

        val txt_tiempo_trabajado = findViewById<EditText>(R.id.hours_worked_txt_time_worked)
        val txt_fecha = findViewById<EditText>(R.id.hours_worked_txt_fecha)
        val boton_anadir = findViewById<Button>(R.id.hours_worked_btn_anadir)
        val boton_regresar = findViewById<ImageView>(R.id.hours_worked_btn_regresar)

        boton_regresar.setOnClickListener {
            val intent = Intent(this, CropActivity::class.java)
            intent.putExtra("cultivo",cultivo)
            startActivity(intent)
        }

        boton_anadir.setOnClickListener {
            try {
                val tiempo_trabajado = txt_tiempo_trabajado.text.toString()
                val fecha = txt_fecha.text.toString()

                if (tiempo_trabajado.isEmpty() || fecha.isEmpty()) throw Exception("Diligencie el formulario")

                db.collection("crops")
                    .document("${auth.currentUser!!.email}")
                    .collection("cultivos")
                    .document("${cultivo}")
                    .collection("tiempos_trabajados")
                    .add(
                        hashMapOf(
                            "minutos" to tiempo_trabajado,
                            "fecha" to fecha
                        )
                    )
                    .addOnSuccessListener {
                        Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!")
                        Toast.makeText(
                            baseContext, "Horas trabajadas guardadas correctamente",
                            Toast.LENGTH_LONG
                        ).show()
                        txt_tiempo_trabajado.setText(null)
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
        findViewById<EditText>(R.id.hours_worked_txt_fecha).setText("${day}/${month + 1}/${year}")
    }

}