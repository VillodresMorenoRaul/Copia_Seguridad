package com.example.ejercicio2_villodres_raul_aplicaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class WebViewEjercicio2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_ejercicio2);
        WebView webview = (WebView) findViewById(R.id.webview);
        Bundle extras = getIntent().getExtras();
        String nombrePaginaWeb = extras.getString("campoPaginaWeb");
        webview.loadUrl(nombrePaginaWeb);
    }
}