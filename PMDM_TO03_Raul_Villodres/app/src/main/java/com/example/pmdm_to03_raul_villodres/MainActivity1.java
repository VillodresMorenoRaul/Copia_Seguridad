package com.example.pmdm_to03_raul_villodres;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.pmdm_to03_raul_villodres.databinding.ActivityMain1Binding;

public class MainActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityMain1Binding binding;

        super.onCreate(savedInstanceState);

        binding = ActivityMain1Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }
}