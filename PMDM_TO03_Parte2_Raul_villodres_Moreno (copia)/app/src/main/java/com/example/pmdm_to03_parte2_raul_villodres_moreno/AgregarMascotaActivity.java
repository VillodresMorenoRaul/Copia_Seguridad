package com.example.pmdm_to03_parte2_raul_villodres_moreno;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pmdm_to03_parte2_raul_villodres_moreno.databinding.ActivityAgregarMascotaBinding;

public class AgregarMascotaActivity extends AppCompatActivity {
    private MascotasController mascotasController;
    View view;
    private ActivityAgregarMascotaBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Configuramos el ViewBinding
        setContentView(R.layout.activity_main1);
        binding = ActivityAgregarMascotaBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);

        // Crear el controlador
        mascotasController = new MascotasController(com.example.pmdm_to03_parte2_raul_villodres_moreno.AgregarMascotaActivity.this);

        // Agregar listener del botón de guardar
        binding.btnAgregarMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Resetear errores a ambos
                binding.etNombre.setError(null);
                binding.etEdad.setError(null);
                binding.etColor.setError(null);
                String nombre = binding.etNombre.getText().toString();
                String edadComoCadena = binding.etEdad.getText().toString();
                String color = binding.etColor.getText().toString();
                if ("".equals(nombre)) {
                    binding.etNombre.setError("Escribe el nombre de la mascota");
                    binding.etNombre.requestFocus();
                    return;
                }
                if ("".equals(edadComoCadena)) {
                    binding.etEdad.setError("Escribe la edad de la mascota");
                    binding.etEdad.requestFocus();
                    return;
                }
                if ("".equals(color)) {
                    binding.etColor.setError("Escribe el color usado");
                    binding.etColor.requestFocus();
                    return;
                }

                // Ver si es un entero
                int edad;
                try {
                    edad = Integer.parseInt(binding.etEdad.getText().toString());
                } catch (NumberFormatException e) {
                    binding.etEdad.setError("Escribe un número");
                    binding.etEdad.requestFocus();
                    return;
                }
                // Ya pasó la validación
                Mascota nuevaMascota = new Mascota(nombre, edad, color);
                long id = mascotasController.nuevaMascota(nuevaMascota);
                if (id == -1) {
                    // De alguna manera ocurrió un error
                    Toast.makeText(AgregarMascotaActivity.this, "Error al guardar. Intenta de nuevo", Toast.LENGTH_SHORT).show();
                } else {
                    // Terminar
                    finish();
                }
            }
        });

        // El de cancelar simplemente cierra la actividad
        binding.btnCancelarNuevaMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
