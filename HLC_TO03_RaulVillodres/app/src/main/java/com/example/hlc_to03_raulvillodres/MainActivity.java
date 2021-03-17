package com.example.hlc_to03_raulvillodres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hlc_to03_raulvillodres.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ActivityMainBinding binding;

        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Botón para acceder a la actividad 3
        binding.AccederEjercicio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirActividad1();
            }
        });

        binding.AccederEjercicio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirActividad2();
            }
        });
    }


    //Método AbrirActividad1(), que abrirá la clase asignada al ejercicio 1
    public void AbrirActividad1() {
        Intent intent = new Intent(this, MainActivity11.class);
        startActivity(intent);
    }

    public void AbrirActividad2() {
        Intent intent = new Intent(this, MainActivity1.class);
        startActivity(intent);
    }
}