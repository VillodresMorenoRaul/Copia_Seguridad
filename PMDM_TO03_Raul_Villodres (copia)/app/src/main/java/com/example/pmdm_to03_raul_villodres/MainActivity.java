package com.example.pmdm_to03_raul_villodres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pmdm_to03_raul_villodres.Actividad1.MainActivity1;
import com.example.pmdm_to03_raul_villodres.Actividad2.MainActivity2;
import com.example.pmdm_to03_raul_villodres.Activity3.MainActivity3;
import com.example.pmdm_to03_raul_villodres.Activity4.MainActivity4;
import com.example.pmdm_to03_raul_villodres.databinding.ActivityMainBinding;

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

        binding.AccederEjercicio4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirActividad4();
            }
        });
    }


    //Método AbrirActividad1(), que abrirá la clase asignada al ejercicio 1

    public void AbrirActividad1() {
        Intent intent = new Intent(this, MainActivity1.class);
        startActivity(intent);
    }

    public void AbrirActividad2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void AbrirActividad3() {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }

    public void AbrirActividad4() {
        Intent intent = new Intent(this, MainActivity4.class);
        startActivity(intent);
    }

}
