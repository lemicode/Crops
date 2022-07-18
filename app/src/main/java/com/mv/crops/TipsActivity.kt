package com.mv.crops

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class TipsActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_tips)

        val txt_tips = findViewById<TextView>(R.id.tips_textview_texto)
        val boton_regresar = findViewById<ImageView>(R.id.tips_btn_regresar)

        boton_regresar.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        try {
            db.collection("tips")
                .get()
                .addOnSuccessListener { result ->
                    Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!")
                    var texto = ""
                    for (document in result) {
                        texto += "${document.id}. ${document.data["tip"]} \n\n"
                    }
                    txt_tips.text = texto
                }
                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error al guardar en BD", e) }
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }
}