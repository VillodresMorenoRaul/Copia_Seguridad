package com.example.pmdm_to03_raul_villodres.Activity3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pmdm_to03_raul_villodres.R;
import com.example.pmdm_to03_raul_villodres.databinding.ActivityAgregarEnlaceBinding;

public class AgregarEnlace extends AppCompatActivity {

    private ActivityAgregarEnlaceBinding binding;
    View view;
    String nombre;
    String email;
    String link;
    String categoria = "deportes";
    private EnlaceController enlaceController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_enlace);
        binding = ActivityAgregarEnlaceBinding.inflate(getLayoutInflater());

        view = binding.getRoot();
        setContentView(view);

        //Creamos el controlador
        enlaceController = new EnlaceController(AgregarEnlace.this);

        //Agregamos un listener al botón guardar
        binding.Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    //Reseteamos errores
                    binding.campoNombre.setError(null);
                    binding.campoLink.setError(null);
                    binding.campoEmail.setError(null);

                    //Añadimos los campos de texto a una cadena
                    nombre = binding.campoNombre.getText().toString();
                    link = binding.campoLink.getText().toString();
                    email = binding.campoEmail.getText().toString();

                    //String añadimos dependiendo del radio button una categoría e imagen.
                    if (binding.RatioButtonDeporte.isChecked()) {
                        categoria = "deportes";
                        //binding.imagenAñadir.setImageResource(R.drawable.logodeporte);
                    }

                    if (binding.RatioButtonMusica.isChecked()) {
                        categoria = "musicas";
                        //binding.imagenAñadir.setImageResource(R.drawable.logomusica);
                    }

                    if (binding.RatioButtonNoticias.isChecked()) {
                        categoria = "noticias";
                        //binding.imagenAñadir.setImageResource(R.drawable.logonoticia);
                    }

                    if (binding.RatioButtonTecnologia.isChecked()) {
                        categoria = "tecnologia";
                        //binding.imagenAñadir.setImageResource(R.drawable.logotecnologia);
                    }

                    Enlaces nuevoEnlace = new Enlaces(nombre, email, link, categoria);
                    long id = enlaceController.nuevoEnlace(nuevoEnlace);
                    if (id == -1) {
                        // De alguna manera ocurrió un error
                        Toast.makeText(AgregarEnlace.this, "Error al guardar. Intenta de nuevo", Toast.LENGTH_SHORT).show();
                    } else {
                        // Terminar
                        finish();
                    }
            }
        });

        binding.Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.RatioButtonDeporte.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //binding.imagenAñadir.setImageResource(R.drawable.logodeporte);
            }
        });

        binding.RatioButtonMusica.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //binding.imagenAñadir.setImageResource(R.drawable.logomusica);
            }
        });

        binding.RatioButtonNoticias.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //binding.imagenAñadir.setImageResource(R.drawable.logonoticia);
            }
        });

        binding.RatioButtonTecnologia.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //binding.imagenAñadir.setImageResource(R.drawable.logotecnologia);
            }
        });


    }


}