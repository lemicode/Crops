package com.mv.crops

import Models.User
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class NewCropActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()?.hide();

        setContentView(R.layout.activity_new_crop)

        auth = Firebase.auth
        val txt_ubicacion = findViewById<EditText>(R.id.new_crop_txt_ubicacion)
        val txt_nombre_cultivo = findViewById<EditText>(R.id.new_crop_txt_nombre_cultivo)
        val txt_area_cultivo = findViewById<EditText>(R.id.new_crop_txt_area_cultivo)
        val txt_fecha_inicio = findViewById<EditText>(R.id.new_crop_txt_fecha_inicio)
        val boton_anadir = findViewById<Button>(R.id.new_crop_btn_anadir)
        val boton_regresar=findViewById<ImageView>(R.id.new_crop_btn_regresar)

        //Creación del selector de cultivos
        /*var posicion_cultivo_seleccionado = 0
        val selector_nombres_cultivos = findViewById<Spinner>(R.id.new_crop_spinner_nombre_cultivo)
        val lista_cultivos = resources.getStringArray(R.array.cultivos)
        val adapter = ArrayAdapter(this, R.layout.spinner_items, lista_cultivos)
        selector_nombres_cultivos.adapter = adapter
        selector_nombres_cultivos.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                posicion_cultivo_seleccionado = position
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }*/

        boton_regresar.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        boton_anadir.setOnClickListener {
            try {
                val ubicacion = txt_ubicacion.text.toString()
                val nombre_cultivo = txt_nombre_cultivo.text.toString()
                val area_cultivo = txt_area_cultivo.text.toString()
                val fecha_inicio = txt_fecha_inicio.text.toString()

                if (nombre_cultivo == "Lista de cultivos") throw Exception("Seleccione un cultivo")

                db.collection("crops").document("${auth.currentUser!!.email}")
                    .collection("cultivos").document(nombre_cultivo)
                    .set(
                        hashMapOf(
                            "ubicacion" to ubicacion,
                            "area" to area_cultivo,
                            "fecha_inicio" to fecha_inicio
                        )
                    )
                    .addOnSuccessListener {
                        Log.d(TAG, "DocumentSnapshot successfully written!")
                        Toast.makeText(
                            baseContext, "El cultivo se guardó correctamente",
                            Toast.LENGTH_LONG
                        ).show()
                        txt_ubicacion.setText(null)
                        txt_nombre_cultivo.setText(null)
                        txt_area_cultivo.setText(null)
                        txt_fecha_inicio.setText(null)
                    }
                    .addOnFailureListener { e -> Log.w(TAG, "Error al guardar en BD", e) }

            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }

        txt_fecha_inicio.setOnClickListener{
            showDatePickerDialog()
        }

    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment{ day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun onDateSelected(day: Int, month: Int, year: Int) {
        findViewById<EditText>(R.id.new_crop_txt_fecha_inicio).setText("${day}/${month + 1}/${year}")
    }

}