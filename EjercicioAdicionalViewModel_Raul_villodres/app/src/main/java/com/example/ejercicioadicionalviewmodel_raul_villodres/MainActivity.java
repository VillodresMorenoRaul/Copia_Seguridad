package com.example.ejercicioadicionalviewmodel_raul_villodres;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ejercicioadicionalviewmodel_raul_villodres.databinding.ActivityMainBinding;
import com.example.ejercicioadicionalviewmodel_raul_villodres.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    //Variables
    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    public int contador = 0;
    public int contadorCache = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Instanciamos la clase con nuestro ViewBinding
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);

        //Iniciamos nuestro viewModel y le pasamos el contexto adecuado, además de juntarlo con la clase correspondiente
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        //Actualizamos el valor al almacenado en el MainActivity
        try {
            binding.Contador.setText(String.valueOf(viewModel.contador));
        } catch(Exception e){
            binding.Contador.setText("Nop");
        }

        //Sumar
        binding.Sumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.sumarNumero();

                if(viewModel.getContador() >= 100) {
                    mostrarMensaje("Has alcanzado el número límite, el valor se ha reseteado a 0");
                    viewModel.setContador(0);
                }

                binding.Contador.setText(Integer.toString(viewModel.getContador()));
            }
        });

        //Restar
        binding.Restar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.restarNumero();

                if(viewModel.getContador() < 0) {
                    mostrarMensaje("El número tras la resta es negativo, el valor se ha reseteado a 0");
                    viewModel.setContador(0);
                }

                    binding.Contador.setText(Integer.toString(viewModel.getContador()));

            }
        });
    }

    private void mostrarMensaje(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }


}