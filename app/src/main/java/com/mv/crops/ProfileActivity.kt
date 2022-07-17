package com.mv.crops

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = Firebase.auth

        val boton_cerrar_sesion = findViewById<Button>(R.id.profile_btn_cerrar_sesion)

        boton_cerrar_sesion.setOnClickListener {
            auth.signOut()
            if(auth.currentUser == null){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

    }
}