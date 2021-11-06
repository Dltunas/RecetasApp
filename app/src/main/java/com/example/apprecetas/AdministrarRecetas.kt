package com.example.apprecetas

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isVisible

class AdministrarRecetas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrar_recetas)

        val txtTitulo = findViewById<EditText>(R.id.ptTituloReceta)
        val txtCuerpo = findViewById<EditText>(R.id.ptCuerpoReceta)
        val txtReferencia = findViewById<EditText>(R.id.ptReferenciaReceta)
        val txtTipo = findViewById<TextView>(R.id.ptTipo)

        val btnRegistrar = findViewById<Button>(R.id.btnAgregar)
        val btnConsultar = findViewById<Button>(R.id.btnBuscar)
        val btnActualizar = findViewById<Button>(R.id.btnActualizar)
        val btnEliminar = findViewById<Button>(R.id.btnEliminar)

        val listaTipos = findViewById<ListView>(R.id.lstTipos)
        val tipos = arrayOf("verduras","carnes","pastas","panes")
        var tipo = ""

        val adaptador1 = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tipos)
        listaTipos.adapter = adaptador1

        listaTipos.setOnItemClickListener{adapterView, view, i, l ->
            tipo = "${tipos[i]}"
            txtTipo.text = "Categoria seleciconada: ${tipo}"
            txtTipo.isVisible = true
        }

        btnRegistrar.setOnClickListener {
            if(!txtTitulo.text.isEmpty() && !txtCuerpo.text.isEmpty() && !txtReferencia.text.isEmpty() && !tipo.isEmpty()){
                val admin = AdminSqlLite(this, "administracion", null, 1)
                val bd = admin.writableDatabase
                val registro = ContentValues()
                registro.put("titulo", txtTitulo.getText().toString())
                registro.put("cuerpo", txtCuerpo.getText().toString())
                registro.put("referencia", txtReferencia.getText().toString())
                registro.put("tipo", tipo)

                bd.insert("recetas", null, registro)
                bd.close()

                txtTitulo.setText("")
                txtCuerpo.setText("")
                txtReferencia.setText("")
                txtTipo.isVisible = false
                tipo = ""
                txtTipo.setText("")

                Toast.makeText(this, "Se registro la receta", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        btnConsultar.setOnClickListener{
            if(!txtTitulo.text.isEmpty()){
                val admin = AdminSqlLite(this, "administracion", null, 1)
                val bd = admin.writableDatabase
                val fila = bd.rawQuery("SELECT titulo, cuerpo, referencia, tipo FROM recetas WHERE titulo = '${txtTitulo.text.toString()}'", null)

                if(fila.moveToFirst()){
                    txtTitulo.setText(fila.getString(0))
                    txtCuerpo.setText(fila.getString(1))
                    txtReferencia.setText(fila.getString(2))
                    tipo = fila.getString(3)
                    txtTipo.setText("Categoria seleccionada: ${tipo}")
                    txtTipo.isVisible = true

                }else{

                    Toast.makeText(this, "No existe la receta", Toast.LENGTH_SHORT).show()

                }
                bd.close()

            }else{
                Toast.makeText(this, "Introduzca un titulo para buscar", Toast.LENGTH_SHORT).show()
            }
        }


        btnEliminar.setOnClickListener {
            if(!txtTitulo.text.isEmpty()){

                val admin = AdminSqlLite(this, "administracion", null, 1)
                val bd = admin.writableDatabase
                val cantidad = bd.delete("recetas", "titulo = '${txtTitulo.text.toString()}'", null)

                bd.close()

                txtTitulo.setText("")
                txtCuerpo.setText("")
                txtReferencia.setText("")
                tipo = ""
                txtTipo.isVisible = false
                txtTipo.setText("")

                if(cantidad == 1){
                    Toast.makeText(this, "Eliminación exitosa", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "No se encontro el elemento", Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(this, "Introduzca un titulo para eliminar", Toast.LENGTH_SHORT).show()
            }
        }

        btnActualizar.setOnClickListener {
            if(!txtTitulo.text.isEmpty() && !txtCuerpo.text.isEmpty() && !txtReferencia.text.isEmpty() && !tipo.isEmpty()){

                val admin = AdminSqlLite(this, "administracion", null, 1)
                val bd = admin.writableDatabase
                val registro = ContentValues()

                registro.put("titulo", txtTitulo.text.toString())
                registro.put("cuerpo", txtCuerpo.text.toString())
                registro.put("referencia", txtReferencia.text.toString())
                registro.put("tipo", tipo)

                val cantidad = bd.update("recetas", registro, "titulo = '${txtTitulo.text.toString()}'", null)

                if(cantidad == 1){
                    Toast.makeText(this, "Actualizacion exitosa", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "No está permitido actualizar el título", Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(this, "Por favor, no dejes sin rellenar ningún campo", Toast.LENGTH_SHORT).show()
            }
        }


    }
}




