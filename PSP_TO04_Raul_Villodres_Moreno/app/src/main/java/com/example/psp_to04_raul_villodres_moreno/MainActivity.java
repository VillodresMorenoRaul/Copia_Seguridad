package com.example.psp_to04_raul_villodres_moreno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.psp_to04_raul_villodres_moreno.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityMainBinding binding;

        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.AccederEjercicio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirActividad2();
            }
        });

        binding.AccederEjercicio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirActividad3();
            }
        });
    }

    //Método AbrirActividad1(), que abrirá la clase asignada al ejercicio 1
    public void AbrirActividad2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    //Método AbrirActividad1(), que abrirá la clase asignada al ejercicio 1
    public void AbrirActividad3() {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }

}