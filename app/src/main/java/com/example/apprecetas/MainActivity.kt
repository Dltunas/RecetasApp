package com.example.apprecetas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdministrar = findViewById<Button>(R.id.btnAdministrarRecetas)
        btnAdministrar.setOnClickListener{
            val abrirPantalla = Intent(this, AdministrarRecetas::class.java)
            startActivity(abrirPantalla)
        }

        val ibtnVerduras = findViewById<ImageButton>(R.id.ibtnVerduras)
        ibtnVerduras.setOnClickListener{
            val abrirPantalla = Intent(this, ListaRecetas::class.java)
            abrirPantalla.putExtra("tipo", "verduras")
            startActivity(abrirPantalla)
        }

        val ibtnCarnes = findViewById<ImageButton>(R.id.ibtnCarnes)
        ibtnCarnes.setOnClickListener{
            val abrirPantalla = Intent(this, ListaRecetas::class.java)
            abrirPantalla.putExtra("tipo", "carnes")
            startActivity(abrirPantalla)
        }

        val ibtnPanes = findViewById<ImageButton>(R.id.ibtnPanes)
        ibtnPanes.setOnClickListener{
            val abrirPantalla = Intent(this, ListaRecetas::class.java)
            abrirPantalla.putExtra("tipo", "panes")
            startActivity(abrirPantalla)
        }

        val ibtnPastas = findViewById<ImageButton>(R.id.ibtnPastas)
        ibtnPastas.setOnClickListener{
            val abrirPantalla = Intent(this, ListaRecetas::class.java)
            abrirPantalla.putExtra("tipo", "pastas")
            startActivity(abrirPantalla)
        }

    }
}