package com.example.hlc_to04_raul_villodres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.hlc_to04_raul_villodres.databinding.ActivityMain1Binding;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity1 extends AppCompatActivity {

    //Variables
    private ActivityMain1Binding binding;
    private TextWatcher text = null;
    public static final String URL = "https://dam.org.es/ficheros/rate.txt";
    public static Double Ratio;
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    public static final int MINUTOS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Configuramos el ViewBinding
        setContentView(R.layout.activity_main1);
        binding = ActivityMain1Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Creamos una timer task, que repetira nuestro servicio cada x tiempo (El indicado en el timer.schedule)
        Timer timer = new Timer();
        TimerTask task = new TimerTask(){
            public void run(){
                //Llamamos al servicio
                startService(new Intent(MainActivity1.this, DownloadService.class));
            }
        };

        //Aquí multiplicamos por 1000 y por 60 para convertir a milisegundos la cantidad que deseemos
        timer.schedule(task, 0, 1000 * 60 * MINUTOS);

        //Desactivamos la posibilidad de escribir en el campoResultado
        binding.campoResultado.setEnabled(false);

        //Si se hace click en el botón EurosADolares
        binding.EurosADolares.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EurosADolaresPulsado();
            }
        });

        binding.DolaresAEuros.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                DolaresAEurosPulsado();
            }
        });

        text = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(binding.campoValor.getText().toString().equals("")) {
                    binding.campoResultado.setText("");
                } else {

                    Double cantidad = Double.parseDouble(binding.campoValor.getText().toString());

                                MainActivity1.this.runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {

                                        //Si el switch marcado es el de dolaresAEuros aplicamos el ratio de forma adecuada
                                        if (binding.DolaresAEuros.isChecked()){
                                            binding.campoResultado.setText(String.valueOf(cantidad * Ratio));

                                            //Actuamos en consecuencia en caso de que el valor marcado sea el de EurosADolares
                                        } else if (binding.EurosADolares.isChecked()) {
                                            binding.campoResultado.setText(String.valueOf(cantidad / Ratio));

                                        } else {
                                            binding.campoResultado.setText("");
                                            binding.campoValor.setText("");
                                        }
                                    }
                                });
                            }
                        }


            @Override
            public void afterTextChanged(Editable s) {

            }

        };

        binding.campoValor.addTextChangedListener(text);
    }

    //Estos dos métodos resetean el texto al cambiar el JRadioButton seleccionado
    public void EurosADolaresPulsado(){
        binding.campoValor.setText("");
        binding.campoResultado.setText("");
    }

    public void DolaresAEurosPulsado(){
        binding.campoValor.setText("");
        binding.campoResultado.setText("");
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();
    }

}