package com.example.villodres_raul_to02_aplicacionesandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.villodres_raul_to02_aplicacionesandroid.databinding.ActivityMain1Binding;

public class MainActivity1 extends AppCompatActivity {

    private Double valor;
    ActivityMain1Binding binding;
    private boolean Valido = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMain1Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.Moneda2.setEnabled(false); //Esta linea de código evita que el usuario modifique el campo de texto "Moneda2"

        binding.Convertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //El condicional, comprueba si el texto está vacío, en cuyo caso lanza un toast indicando esto mismo, y en caso contrario probando a formatear a Double el valor numérico, si hay un error, la variable "Valido" se pone cómo falso, evitando que el programa realice las operaciones con números que no sean válidos
                if(binding.Moneda1.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"El campo está vacío",Toast.LENGTH_SHORT).show();
                    Valido = false;
                } else {
                    try {
                        valor = Double.parseDouble(String.valueOf(binding.Moneda1.getText()));

                    } catch (Exception e){
                        Toast.makeText(getApplicationContext(),"El valor introducido no es válido",Toast.LENGTH_SHORT).show();
                        Valido = false;
                    }
                }

                //Aquí,en caso de que no haya habido ningún error de parseo o campo vacío, se actuará cómo si presionaramos X botón usando el método "isChecked()"
                    if (!binding.DolaresAEuros.isChecked() && !binding.EurosADolares.isChecked()) {
                        Toast.makeText(getApplicationContext(), "No hay ningún radioButton seleccionado", Toast.LENGTH_SHORT).show();

                    } else if (binding.DolaresAEuros.isChecked() && Valido == true) {
                        valor = Double.parseDouble(String.valueOf(binding.Moneda1.getText()));

                        if(valor >= 0) {
                            binding.Moneda2.setText(String.valueOf(valor * 0.82352));
                        } else {
                            Toast.makeText(getApplicationContext(), "El número no puede ser negativo", Toast.LENGTH_SHORT).show();
                        }

                    } else if (binding.EurosADolares.isChecked() && Valido == true) {
                        valor = Double.parseDouble(String.valueOf(binding.Moneda1.getText()));

                        if(valor >= 0){
                            binding.Moneda2.setText(String.valueOf(valor * 1.21424));
                        } else {
                            Toast.makeText(getApplicationContext(), "El número no puede ser negativo", Toast.LENGTH_SHORT).show();
                        }

                    }
                Valido = true;
            }
        });

    }
}