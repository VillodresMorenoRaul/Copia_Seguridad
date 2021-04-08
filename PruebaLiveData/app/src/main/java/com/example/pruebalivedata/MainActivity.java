package com.example.pruebalivedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pruebalivedata.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;
    private MainActitivyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // creo el ViewModel
        viewModel = new ViewModelProvider(this).get(MainActitivyViewModel.class);
        // asigno observador a los segundos
        viewModel.getSecondsLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.tvNumero.setText(integer.toString());
            }
        });
        // asigno observador al acabado
        viewModel.getFinishedLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean bool) {
                if (bool) {
                    Toast.makeText(getApplicationContext(), "Contador acabado", Toast.LENGTH_LONG).show();
                }
            }
        });
        // asigno los botones para que sean tratados aquí
        binding.btIniciar.setOnClickListener(this);
        binding.btParar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btIniciar) {
            // si se pulsó el botón iniciar
            if (binding.etMilisegundos.getText().length() < 4) {
                // si hay menos de 4 dígitos, no lo acepto
                Toast.makeText(this, "Número incorrecto", Toast.LENGTH_LONG).show();
            }else{
                // si hay más, asigno el valor e inicio el contoador
                viewModel.setTimerValue(Long.parseLong(binding.etMilisegundos.getText().toString()));
                viewModel.startTimer();
            }
        }else if (view == binding.btParar) {
            // si se pulsó el botón parar... asigno 0 al contador
            binding.tvNumero.setText("0");
            // y paro el temporizador
            viewModel.stopTimer();
        }
    }
}