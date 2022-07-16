package com.mv.crops

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_main)
        val boton_login = findViewById<Button>(R.id.login_btn_iniciar_sesion)
        val txt_correo=findViewById<EditText>(R.id.login_txt_usuario)
        val txt_contrasena=findViewById<EditText>(R.id.login_txt_contrasena)
        val boton_registro = findViewById<Button>(R.id.login_btn_registrate)


        val greenString = "<font color='#03A63D'>ahora</font>"
        val blackString = "<font color='#000000'>Registrate </font>"

        boton_registro.text = Html.fromHtml( blackString + greenString )

        auth = Firebase.auth


        boton_registro.setOnClickListener {
            val intent = Intent(this, NewUserActivity::class.java)
            startActivity(intent)
        }

        boton_login.setOnClickListener {
            try {
                val correo = txt_correo.text.toString().lowercase()
                val contrasena = txt_contrasena.text.toString()

                if (correo.isEmpty() || contrasena.isEmpty()) {
                    throw Exception("Los campos no deben estar vacios")
                } else {
                    auth.signInWithEmailAndPassword(correo, contrasena)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(ContentValues.TAG, "signInWithEmailAndPassword:success")
                                Toast.makeText(
                                    baseContext, "Iniciando sesión",
                                    Toast.LENGTH_SHORT
                                ).show()
                                // Back to main page
                                val intent = Intent(this, HomeActivity::class.java)
                                startActivity(intent)
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(
                                    ContentValues.TAG,
                                    "signInWithEmailAndPassword:failure",
                                    task.exception
                                )
                                Toast.makeText(
                                    baseContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }

        }

    }

}

