package com.example.ejercicioadicionallivedata_raul_villodres;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ejercicioadicionallivedata_raul_villodres.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Instanciamos la clase con nuestro ViewBinding
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);

        //Desactivamos el botón cancelar y creamos un nuevo ViewModel
        binding.BotonCancelar.setEnabled(false);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        //Obtenemos los segundos
        viewModel.getSegundos().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.Contador.setText(integer.toString());
            }
        });

        //Declaramos el Boton al iniciar y le asignamos un método
        binding.BotonEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccionIniciar();
            }
        });

        //Declaramos el botón cancelar y le asignamos un método con su acción
        binding.BotonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccionCancelar();
            }
        });

    }

    //Al pulsar el boton iniciar se comprueba que el valor sea mayor que 1 segundo y menor que 11 y en caso positivo comienza el contador con nuestro tiempo indicado
    public void AccionIniciar(){

        try {
            long segundos = Long.parseLong(binding.CampoSegundos.getText().toString()) * 1000;
            binding.BotonCancelar.setEnabled(true);

            if(segundos > 1000 && segundos < 11000){
                viewModel.setTimerValue(segundos);
                viewModel.StartTimer();

            } else {
                mostrarMensaje("Número demasiado pequeño (Debe ser entre 2 y 10 segundos");
            }
        } catch (Exception e){
            mostrarMensaje("El campo está vacío o contiene un valor no válido");
        }


    }

    //Volvemos el contador a 0, lo detenemos y desactivamos el boton
    public void AccionCancelar(){
            binding.Contador.setText("0");
            viewModel.StopTimer();
            binding.BotonCancelar.setEnabled(false);
            mostrarMensaje("Contador reiniciado");

    }

    //Método para mostrar Toasts
    public void mostrarMensaje(String texto){
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show();
    }
}