package com.example.pmdm_to03_parte2_raul_villodres_moreno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pmdm_to03_parte2_raul_villodres_moreno.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityMainBinding binding;

        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.AccederEjercicio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirActividad1();
            }
        });
    }


    //Método AbrirActividad1(), que abrirá la clase asignada al ejercicio 1
    public void AbrirActividad1() {
        Intent intent = new Intent(this, MainActivity1.class);
        startActivity(intent);
    }
}