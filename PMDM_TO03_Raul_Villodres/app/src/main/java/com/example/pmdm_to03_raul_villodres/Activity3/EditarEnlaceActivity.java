package com.example.pmdm_to03_raul_villodres.Activity3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pmdm_to03_raul_villodres.R;
import com.example.pmdm_to03_raul_villodres.databinding.ActivityEditarEnlaceBinding;

public class EditarEnlaceActivity extends AppCompatActivity {
    private EditText etEditarNombre, etEditarLink, etEditarEmail;
    private Button btnGuardarCambios, btnCancelarEdicion;
    private Enlaces enlace;//El enlace que estamos editando
    private EnlaceController enlacesController;
    private ActivityEditarEnlaceBinding binding;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_enlace);

        binding = ActivityEditarEnlaceBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);

        // Recuperar datos que enviaron
        Bundle extras = getIntent().getExtras();
        // Si no hay datos (cosa rara) salimos
        if (extras == null) {
            finish();
            return;
        }
        // Instanciamos el controlador de enlaces
        enlacesController = new EnlaceController(EditarEnlaceActivity.this);

        //Rearmamos el enlace
        long idEnlace = extras.getLong("id");
        String nombreEnlace = extras.getString("nombre");
        String linkEnlace = extras.getString("link");
        String emailEnlace = extras.getString("email");
        String categoriaEnlace = extras.getString("categoria");

        enlace = new Enlaces(nombreEnlace, linkEnlace, emailEnlace, categoriaEnlace, idEnlace);


        // Ahora declaramos las vistas
        etEditarNombre = findViewById(R.id.campoNombre);
        etEditarLink = findViewById(R.id.campoLink);
        etEditarEmail = findViewById(R.id.campoEmail);

        btnCancelarEdicion = findViewById(R.id.Cancelar);
        btnGuardarCambios = findViewById(R.id.Guardar);


        // Rellenar los EditText con los datos de los enlaces
        binding.campoNombre.setText(enlace.getNombre());
        binding.campoLink.setText(enlace.getLink());
        binding.campoEmail.setText(enlace.getEmail());

        // Listener del click del botón para salir, simplemente cierra la actividad
            binding.Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Listener del click del botón que guarda cambios
            binding.Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remover previos errores si existen
                binding.campoNombre.setError(null);
                binding.campoLink.setError(null);
                binding.campoEmail.setError(null);

                // Creamos el enlace con los nuevos datos
                // Primero obtenemos el id de la anterior
                String nuevoNombre = binding.campoNombre.getText().toString();
                String nuevoLink = binding.campoLink.getText().toString();
                String nuevoEmail = binding.campoEmail.getText().toString();
                String nuevaCategoria = "deportes";

                if (binding.RatioButtonDeporte.isChecked()) {
                    nuevaCategoria = "deportes";
                    binding.imagenAnadir.setImageResource(R.drawable.logodeporte);
                }

                if (binding.RatioButtonMusica.isChecked()) {
                    nuevaCategoria = "musicas";
                    binding.imagenAnadir.setImageResource(R.drawable.logomusica);
                }

                if (binding.RatioButtonNoticias.isChecked()) {
                    nuevaCategoria = "noticias";
                    binding.imagenAnadir.setImageResource(R.drawable.logonoticia);
                }

                if (binding.RatioButtonTecnologia.isChecked()) {
                    nuevaCategoria = "tecnologia";
                    binding.imagenAnadir.setImageResource(R.drawable.logotecnologia);
                }

                if (nuevoNombre.isEmpty()) {
                    binding.campoNombre.setError("Escribe el nombre");
                    binding.campoNombre.requestFocus();
                    return;
                }

                if (nuevoLink.isEmpty()) {
                    binding.campoLink.setError("Escribe el Link");
                    binding.campoLink.requestFocus();
                    return;
                }


                if (nuevoEmail.isEmpty()) {
                    binding.campoEmail.setError("Escribe el Email");
                    binding.campoEmail.requestFocus();
                    return;
                }



                // Si llegamos hasta aquí es porque los datos ya están validados
                Enlaces EnlaceConNuevosCambios = new Enlaces(nuevoNombre, nuevoLink, nuevoEmail, nuevaCategoria, enlace.getId());
                int filasModificadas = enlacesController.guardarCambios(EnlaceConNuevosCambios);
                if (filasModificadas != 1) {
                    Toast.makeText(EditarEnlaceActivity.this, "Error guardando cambios. Intente de nuevo.", Toast.LENGTH_SHORT).show();
                } else {
                    finish();
                }
            }
        });
    }
}