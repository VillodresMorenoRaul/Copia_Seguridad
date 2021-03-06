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
        etEditarNombre.setText(enlace.getNombre());
        etEditarLink.setText(enlace.getLink());
        etEditarEmail.setText(enlace.getLink());

        // Listener del click del botón para salir, simplemente cierra la actividad
        btnCancelarEdicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Listener del click del botón que guarda cambios
        btnGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remover previos errores si existen
                etEditarNombre.setError(null);
                etEditarLink.setError(null);
                etEditarEmail.setError(null);

                // Creamos el enlace con los nuevos datos
                // Primero obtenemos el id de la anterior
                String nuevoNombre = etEditarNombre.getText().toString();
                String nuevoLink = etEditarLink.getText().toString();
                String nuevoEmail = etEditarEmail.getText().toString();
                String nuevaCategoria = "deportes";

                if (binding.RatioButtonDeporte.isChecked()) {
                    nuevaCategoria = "deportes";
                    //binding.imagenAñadir.setImageResource(R.drawable.logodeporte);
                }

                if (binding.RatioButtonMusica.isChecked()) {
                    nuevaCategoria = "musicas";
                    //binding.imagenAñadir.setImageResource(R.drawable.logomusica);
                }

                if (binding.RatioButtonNoticias.isChecked()) {
                    nuevaCategoria = "noticias";
                    //binding.imagenAñadir.setImageResource(R.drawable.logonoticia);
                }

                if (binding.RatioButtonTecnologia.isChecked()) {
                    nuevaCategoria = "tecnologia";
                    //binding.imagenAñadir.setImageResource(R.drawable.logotecnologia);
                }

                if (nuevoNombre.isEmpty()) {
                    etEditarNombre.setError("Escribe el nombre");
                    etEditarNombre.requestFocus();
                    return;
                }
                if (nuevoEmail.isEmpty()) {
                    etEditarEmail.setError("Escribe la edad");
                    etEditarEmail.requestFocus();
                    return;
                }

                if(nuevoLink.isEmpty()){
                    etEditarLink.setError("Escribe el link");
                    etEditarLink.requestFocus();
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