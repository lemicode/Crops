package com.mv.crops

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.cardview.widget.CardView
import com.google.android.gms.maps.model.LatLng

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_home)

        var perfil = findViewById<CardView>(R.id.home_card_perfil)
        var agregar_cultivo = findViewById<CardView>(R.id.home_card_agregar_cultivos)
        var ver_cultivos = findViewById<CardView>(R.id.home_card_ver_cultivos)
        var tips = findViewById<CardView>(R.id.home_card_tips)

        perfil.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        agregar_cultivo.setOnClickListener{
            val intent = Intent(this, NewCropActivity::class.java)
            startActivity(intent)
        }

        ver_cultivos.setOnClickListener {
            val intent = Intent(this, CropsViewActivity::class.java)
            startActivity(intent)
        }

        tips.setOnClickListener{
            val intent = Intent(this, MapsActivity::class.java)
            intent.putExtra("latitude",4.72977)
            intent.putExtra("longitude",-73.96835)
            startActivity(intent)
        }

    }
}