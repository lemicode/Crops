package com.mv.crops

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.mv.crops.databinding.ActivityTipsBinding

class TipsActivity : AppCompatActivity() {
    val storage = Firebase.storage
    lateinit var binding: ActivityTipsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()?.hide();
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips)

        val storageRef = storage.reference
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        binding = ActivityTipsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var datos = ""
        val docRef = db.collection("tips")
            .get()
            .addOnSuccessListener { resultado ->
                for (documento in resultado) {
                    datos += "${documento.id}. ${documento.data.values}\n \n"
                    datos = datos.replace("[", "").replace("]", "")
                }
                binding.tvTips.text = datos

            }
            .addOnFailureListener { exception ->
                binding.tvTips.text = "No se ha posiso conectar"
            }

        val boton_regresar=findViewById<ImageView>(R.id.new_crop_btn_regresar)

        boton_regresar.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

    }
}