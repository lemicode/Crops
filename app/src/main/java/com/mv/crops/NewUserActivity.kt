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
import com.google.firebase.ktx.Firebase

class NewUserActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_new_user)

        val txt_correo = findViewById<EditText>(R.id.new_user_txt_email)
        val txt_contraseña = findViewById<EditText>(R.id.new_user_txt_password)
        val txt_confirmar = findViewById<EditText>(R.id.new_user_txt_password2)
        val boton_registrar = findViewById<Button>(R.id.new_user_btn_iniciar_sesion)

        //boton regresa al menu principal
        val boton_regresar=findViewById<ImageView>(R.id.new_crop_btn_regresar)
        boton_regresar.setOnClickListener{
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        auth = Firebase.auth

        boton_registrar.setOnClickListener {
            try {
                val correo = txt_correo.text.toString().lowercase()
                val contrasena = txt_contraseña.text.toString()
                val contrasena2 = txt_confirmar.text.toString()

                if (correo.isEmpty() || contrasena.isEmpty() || contrasena2.isEmpty()) {
                    throw Exception("Los campos no deben estar vacios")
                }
                else {

                    if (contrasena == contrasena2) {

                        auth.createUserWithEmailAndPassword(correo, contrasena)
                            .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(ContentValues.TAG, "createUserWithEmailAndPassword:success")
                                    Toast.makeText(baseContext, "Usuario creado!!", Toast.LENGTH_SHORT).show()
                                    auth.signOut()
                                    val intent= Intent(this, MainActivity::class.java)
                                    startActivity(intent)
                                }
                                else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(ContentValues.TAG, "createUserWithEmailAndPassword:failure", task.exception)
                                    Toast.makeText(baseContext, "Creation failed.",
                                        Toast.LENGTH_SHORT).show()
                                }
                            }

                    }
                    else {
                        throw Exception("Las contraseñas no son iguales!")
                    }
                }
            }
            catch(e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }

    }
}