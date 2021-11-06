package com.example.apprecetas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.widget.*

class ListaRecetas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_recetas)

        val lstRecetas = findViewById<ListView>(R.id.lstRecetas)
        val txtTituloReceta = findViewById<TextView>(R.id.txtTituloReceta)
        val txtCuerpoReceta = findViewById<TextView>(R.id.txtCuerpoReceta)
        var referencia = ""

        val bundle = intent.extras
        val tipo = bundle?.getString("tipo")

        val admin = AdminSqlLite(this, "administracion", null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery("SELECT titulo, cuerpo, referencia, tipo FROM recetas WHERE tipo = '${tipo}'", null)

        //recorrer la lista
        val itemTitulo = mutableListOf<String>()
        with(fila) {
            while (moveToNext()){
                val itemId = fila.getString(0)
                itemTitulo.add(itemId)
            }
        }

        bd.close()

        val adaptador1 = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, itemTitulo)
        lstRecetas.adapter = adaptador1
        lstRecetas.setOnItemClickListener{adapterView, view, i, l ->
            val admin = AdminSqlLite(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val fila = bd.rawQuery("SELECT titulo, cuerpo, referencia, tipo FROM recetas WHERE titulo = '${itemTitulo[i].toString()}'", null)

            if(fila.moveToFirst()) {
                txtTituloReceta.setText(fila.getString(0))
                txtCuerpoReceta.setText(fila.getString(1))
                referencia = fila.getString(2)
            }

            bd.close()

        }

        val btnIr = findViewById<Button>(R.id.btnMasInformacion)

        btnIr.setOnClickListener {
            val intento1 = Intent(this, Navegacion::class.java)
            intento1.putExtra("direccion", referencia)
            startActivity(intento1)
        }


    }
}