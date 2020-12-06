package com.example.ejercicio2_villodres_raul_aplicaciones;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

import com.example.ejercicio2_villodres_raul_aplicaciones.databinding.ActivityMain1Binding;

public class MainActivity1 extends AppCompatActivity implements View.OnClickListener {

    private ActivityMain1Binding binding;
    private ContadorEjercicio1 contador;
    private  MyCountDownTimer miContadorDescendente;
    private static final int PAUSA = 3;
    private int segundos;
    private int minutos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMain1Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        contador = new ContadorEjercicio1(0, PAUSA);
        binding.tiempo.setText(PAUSA + ":00");
        binding.botonMenos.setOnClickListener(this);
        binding.botonMas.setOnClickListener(this);
        binding.botonComenzar.setOnClickListener(this);
        binding.ResetearCafes.setEnabled(false);


        binding.ResetearCafes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contador.resetearContadorCafes();
                binding.cuenta.setText(String.valueOf(contador.obtenerCafes()));
                binding.ResetearCafes.setEnabled(false);
            }
        });

    }

    @Override
    public void onClick(View view) {


        if(contador.obtenerCafes() < 10) {
            if (view == binding.botonMenos) {
                binding.tiempo.setText(contador.disminuirTiempo());
            }
            if (view == binding.botonMas)  {
                binding.tiempo.setText(contador.aumentarTiempo());
            }
            if (view == binding.botonComenzar) {

                MediaPlayer reproducirSonidoCafe = MediaPlayer.create(this, R.raw.cafe);
                reproducirSonidoCafe.start();

                if (binding.switchAscendente.isChecked()){

                    binding.Cronometro.setBase(SystemClock.elapsedRealtime());
                    binding.Cronometro.start();
                    binding.botonMenos.setEnabled(false);
                    binding.botonMas.setEnabled(false);
                    binding.botonComenzar.setEnabled(false);

                    binding.Cronometro.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                        @Override
                        public void onChronometerTick(Chronometer chronometer) {

                            segundos++;

                            if(segundos == 60){
                                minutos++;
                                segundos = 0;
                            }

                            if(minutos == contador.getTiempo()){

                                binding.Cronometro.stop();
                                binding.botonMenos.setEnabled(true);
                                binding.botonMas.setEnabled(true);
                                binding.botonComenzar.setEnabled(true);

                                binding.Cronometro.setBase(SystemClock.elapsedRealtime());

                                Toast.makeText(MainActivity1.this, "Pausa terminada", Toast.LENGTH_SHORT).show();
                                binding.cuenta.setText(String.valueOf(contador.obtenerCafes()));

                                minutos = 0;
                                segundos = -2;

                            }
                        }

                    });

                    contador.aumentarCafes();

                } else {
                    miContadorDescendente = new MyCountDownTimer(contador.getTiempo() * 60 * 1000, 1000);

                    miContadorDescendente.start();
                    binding.botonMenos.setEnabled(false);
                    binding.botonMas.setEnabled(false);
                    binding.botonComenzar.setEnabled(false);

                }
            }
        } else {
            AlertDialog.Builder popup=new AlertDialog.Builder(this);
            popup.setTitle("Máximo contador de cafés alcanzado");
            popup.setMessage("Aunque hayas alcanzado este límite, puedes resetear la cuenta de cafés pulsando el botón ahora disponible encima del de comenzar");
            popup.setPositiveButton("Entendido...", null);
            popup.show();
            binding.ResetearCafes.setEnabled(true);

        }

    }


    public class MyCountDownTimer extends CountDownTimer {
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
            Toast.makeText(MainActivity1.this, "Pausa terminada", Toast.LENGTH_SHORT).show();
            binding.tiempo.setText(contador.getTiempo() + ":00");
            contador.aumentarCafes();
            binding.cuenta.setText(String.valueOf(contador.obtenerCafes()));
            binding.botonMenos.setEnabled(true);
            binding.botonMas.setEnabled(true);
            binding.botonComenzar.setEnabled(true);
        }
    }
}