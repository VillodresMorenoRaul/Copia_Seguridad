package com.example.pmdm_to03_parte2_raul_villodres_moreno;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pmdm_to03_parte2_raul_villodres_moreno.databinding.ActivityAgregarMascotaBinding;
import com.example.pmdm_to03_parte2_raul_villodres_moreno.databinding.ActivityEditarMascotaBinding;

public class EditarMascotaActivity extends AppCompatActivity {
    private com.example.pmdm_to03_parte2_raul_villodres_moreno.Mascota mascota;//La mascota que vamos a estar editando
    private MascotasController mascotasController;
    View view;
    private ActivityEditarMascotaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Configuramos el ViewBinding
        setContentView(R.layout.activity_main1);
        binding = ActivityEditarMascotaBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);

        // Recuperar datos que enviaron
        Bundle extras = getIntent().getExtras();
        // Si no hay datos (cosa rara) salimos
        if (extras == null) {
            finish();
            return;
        }
        // Instanciar el controlador de las mascotas
        mascotasController = new MascotasController(EditarMascotaActivity.this);

        // Rearmar la mascota
        // Nota: igualmente solamente podríamos mandar el id y recuperar la mascota de la BD
        long idMascota = extras.getLong("idMascota");
        String nombreMascota = extras.getString("nombreMascota");
        int edadMascota = extras.getInt("edadMascota");
        String colorMascota = extras.getString("colorMascota");
        mascota = new com.example.pmdm_to03_parte2_raul_villodres_moreno.Mascota(nombreMascota, edadMascota, colorMascota, idMascota);

        // Rellenar los EditText con los datos de la mascota
        binding.etEditarEdad.setText(String.valueOf(mascota.getEdad()));
        binding.etEditarNombre.setText(mascota.getNombre());
        binding.etEditarColor.setText(String.valueOf(mascota.getColor()));

        // Listener del click del botón para salir, simplemente cierra la actividad
        binding.btnCancelarEdicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Listener del click del botón que guarda cambios
        binding.btnGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remover previos errores si existen
                binding.etEditarNombre.setError(null);
                binding.etEditarEdad.setError(null);
                binding.etEditarColor.setError(null);
                // Crear la mascota con los nuevos cambios pero ponerle
                // el id de la anterior
                String nuevoNombre =  binding.etEditarNombre.getText().toString();
                String posibleNuevaEdad =  binding.etEditarEdad.getText().toString();
                String nuevoColor =  binding.etEditarColor.getText().toString();
                if (nuevoNombre.isEmpty()) {
                    binding.etEditarNombre.setError("Escribe el nombre");
                    binding.etEditarNombre.requestFocus();
                    return;
                }
                if (posibleNuevaEdad.isEmpty()) {
                    binding.etEditarEdad.setError("Escribe la edad");
                    binding.etEditarEdad.requestFocus();
                    return;
                }
                if (nuevoColor.isEmpty()) {
                    binding.etEditarColor.setError("Escribe el nombre");
                    binding.etEditarColor.requestFocus();
                    return;
                }
                // Si no es entero, igualmente marcar error
                int nuevaEdad;
                try {
                    nuevaEdad = Integer.parseInt(posibleNuevaEdad);
                } catch (NumberFormatException e) {
                    binding.etEditarEdad.setError("Escribe un número");
                    binding.etEditarEdad.requestFocus();
                    return;
                }
                // Si llegamos hasta aquí es porque los datos ya están validados
                com.example.pmdm_to03_parte2_raul_villodres_moreno.Mascota mascotaConNuevosCambios = new com.example.pmdm_to03_parte2_raul_villodres_moreno.Mascota(nuevoNombre, nuevaEdad, nuevoColor, mascota.getId());
                int filasModificadas = mascotasController.guardarCambios(mascotaConNuevosCambios);
                if (filasModificadas != 1) {
                    // De alguna forma ocurrió un error porque se debió modificar únicamente una fila
                    Toast.makeText(com.example.pmdm_to03_parte2_raul_villodres_moreno.EditarMascotaActivity.this, "Error guardando cambios. Intente de nuevo.", Toast.LENGTH_SHORT).show();
                } else {
                    // Si las cosas van bien, volvemos a la principal
                    // cerrando esta actividad
                    finish();
                }
            }
        });
    }
}
