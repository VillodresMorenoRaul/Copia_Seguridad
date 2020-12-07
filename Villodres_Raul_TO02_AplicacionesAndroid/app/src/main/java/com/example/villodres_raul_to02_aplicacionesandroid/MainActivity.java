package com.example.villodres_raul_to02_aplicacionesandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.villodres_raul_to02_aplicacionesandroid.databinding.ActivityMain1Binding;
import com.example.villodres_raul_to02_aplicacionesandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Botón para acceder a la actividad 1
        binding.AccederEjercicio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirActividad1();
            }
        });

        //Botón para acceder a la actividad 2
        binding.AccederEjercicio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirActividad2();
            }
        });

        //Botón para acceder a la actividad 3
        binding.AccederEjercicio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirActividad3();
            }
        });
    }

    //Método AbrirActividad1(), que abrirá la clase asignada al ejercicio 1
    public void AbrirActividad1() {
        Intent intent = new Intent(this, MainActivity1.class);
        startActivity(intent);
    }

    //Método AbrirActividad2(), que abrirá la clase asignada al ejercicio 2
    public void AbrirActividad2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    //Método AbrirActividad3(), que abrirá la clase asignada al ejercicio 3
    public void AbrirActividad3() {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }
}