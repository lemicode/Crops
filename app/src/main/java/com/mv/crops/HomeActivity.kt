package com.mv.crops

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.cardview.widget.CardView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_home)

        var perfil = findViewById<CardView>(R.id.home_card_perfil)
        var agregar_cultivo = findViewById<CardView>(R.id.home_card_agregar_cultivos)

        perfil.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        agregar_cultivo.setOnClickListener{
            val intent = Intent(this, NewCropActivity::class.java)
            startActivity(intent)
        }

    }
}