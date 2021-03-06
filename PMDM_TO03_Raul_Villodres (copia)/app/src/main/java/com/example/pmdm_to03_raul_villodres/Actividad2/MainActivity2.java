package com.example.pmdm_to03_raul_villodres.Actividad2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import com.example.pmdm_to03_raul_villodres.R;
import com.example.pmdm_to03_raul_villodres.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    private ActivityMain2Binding binding;
    private  Contador contador;
    private  MyCountDownTimer miContadorDescendente;
    private static final int PAUSA = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        contador = new  Contador (0, PAUSA);
        binding.tiempo.setText(PAUSA + ":00");
        binding.botonMenos.setOnClickListener(this);
        binding.botonMas.setOnClickListener(this);
        binding.botonComenzar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == binding.botonMenos) {
            binding.tiempo.setText(contador.disminuirTiempo());
        }
        if (view == binding.botonMas)  {
            binding.tiempo.setText(contador.aumentarTiempo());
        }
        if (view == binding.botonComenzar) {
            miContadorDescendente = new MyCountDownTimer(contador.getTiempo() * 60 * 1000, 1000);
            //miContadorDescendente = new MyCountDownTimer(contador.getTiempo() * 1000, 1000);
            miContadorDescendente.start();
            binding.botonMenos.setEnabled(false);
            binding.botonMas.setEnabled(false);
            binding.botonComenzar.setEnabled(false);
        }
    }

    public class MyCountDownTimer extends CountDownTimer
    {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override public void onTick (long millisUntilFinished) {
            long minutos, segundos;

            minutos = (millisUntilFinished / 1000) / 60;
            segundos = (millisUntilFinished / 1000) % 60;
            binding.tiempo.setText(minutos + ":" + String.format("%02d",segundos));

        }
        @Override
        public void onFinish() {
            //binding.tiempo.setText("Pausa terminada!!");
            Toast.makeText(MainActivity2.this, "Pausa terminada", Toast.LENGTH_SHORT).show();
            binding.tiempo.setText(contador.getTiempo() + ":00");
            binding.cuenta.setText(contador.aumentarCafes());
            binding.botonMenos.setEnabled(true);
            binding.botonMas.setEnabled(true);
            binding.botonComenzar.setEnabled(true);
        }
    }
}