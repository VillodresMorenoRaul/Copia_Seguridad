package com.example.psp_to05_raul_villodres;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.psp_to05_raul_villodres.databinding.ActivityMain3Binding;

public class MainActivity3 extends AppCompatActivity {

    //Variables
    private ActivityMain3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Configuramos el ViewBinding
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3;
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }
}