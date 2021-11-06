package com.example.apprecetas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class Navegacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navegacion)

        val bundle = intent.extras
        val url = bundle?.getString("direccion")

        val webview = findViewById<WebView>(R.id.wbPagina)

        webview.loadUrl("${url}")

    }
}