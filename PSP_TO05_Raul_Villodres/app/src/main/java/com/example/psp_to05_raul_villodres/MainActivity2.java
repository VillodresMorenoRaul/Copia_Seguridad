package com.example.psp_to05_raul_villodres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.psp_to05_raul_villodres.databinding.ActivityMain2Binding;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity2 extends AppCompatActivity {

    //Variables
    private ActivityMain2Binding binding;
    private TextWatcher text = null;
    public static final String URL = "https://dam.org.es/ficheros/rate.txt";
    public static Double Ratio;
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    public static final int MINUTOS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Configuramos el ViewBinding
        setContentView(R.layout.activity_main2);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

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

                    MainActivity2.this.runOnUiThread(new Runnable() {

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