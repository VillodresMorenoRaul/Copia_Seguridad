package com.example.ejercicio2_villodres_raul_aplicaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Toast;

import com.example.ejercicio2_villodres_raul_aplicaciones.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {

    ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.abrirEnNavegador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirEnNavegador();
            }
        });

        binding.abrirEnActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirEnActividad();
            }
        });
    }

    public void AbrirEnNavegador(){

        try {
            Uri uri = Uri.parse(String.valueOf(binding.campoPaginaWeb.getText()));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } catch (Exception e){
            Toast.makeText(this, " El enlace introducido no es válido", Toast.LENGTH_SHORT).show();
        }

    }

    public void AbrirEnActividad(){

        boolean UrlValida = URLUtil.isValidUrl(String.valueOf(binding.campoPaginaWeb.getText()));

        if(UrlValida == true) {
            Intent intent = new Intent(this, WebViewEjercicio2.class);
            intent.putExtra("campoPaginaWeb", String.valueOf(binding.campoPaginaWeb.getText()));
            startActivity(intent);
        } else {
            Toast.makeText(this, " El enlace introducido no es válido", Toast.LENGTH_SHORT).show();
        }
    }

}