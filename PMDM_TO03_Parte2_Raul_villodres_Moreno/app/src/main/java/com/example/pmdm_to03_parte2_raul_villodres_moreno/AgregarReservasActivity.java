package com.example.pmdm_to03_parte2_raul_villodres_moreno;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pmdm_to03_parte2_raul_villodres_moreno.databinding.ActivityAgregarReservaBinding;

public class AgregarReservasActivity extends AppCompatActivity {
    private ReservasController reservasController;
    View view;
    private ActivityAgregarReservaBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Configuramos el ViewBinding
        setContentView(R.layout.activity_main1);
        binding = ActivityAgregarReservaBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);

        // Crear el controlador
        reservasController = new ReservasController(AgregarReservasActivity.this);

        // Agregar listener del botón de guardar
        binding.btnAgregarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Resetear errores a ambos
                binding.etJinete.setError(null);
                binding.etMovil.setError(null);
                binding.etCaballo.setError(null);
                binding.etFecha.setError(null);
                binding.etHora.setError(null);
                binding.etComentario.setError(null);

                String jinete = binding.etJinete.getText().toString();
                String movilComoCadena = binding.etMovil.getText().toString();
                String caballo = binding.etCaballo.getText().toString();
                String fecha = binding.etFecha.getText().toString();
                String hora = binding.etHora.getText().toString();
                String comentario = binding.etComentario.getText().toString();

                if ("".equals(jinete)) {
                    binding.etJinete.setError("Escribe el nombre del jinete");
                    binding.etJinete.requestFocus();
                    return;
                }

                if ("".equals(movilComoCadena)) {
                    binding.etMovil.setError("Escribe un móvil válido");
                    binding.etMovil.requestFocus();
                    return;
                }

                if ("".equals(caballo)) {
                    binding.etCaballo.setError("Escribe el nombre del caballo");
                    binding.etCaballo.requestFocus();
                    return;
                }

                if ("".equals(fecha)) {
                    binding.etFecha.setError("Escribe la fecha de reserva");
                    binding.etFecha.requestFocus();
                    return;
                }

                if ("".equals(hora)) {
                    binding.etHora.setError("Escribe la hora la reserva");
                    binding.etHora.requestFocus();
                    return;
                }

                if ("".equals(comentario)) {
                    binding.etCaballo.setError("Escribe un comentario");
                    binding.etCaballo.requestFocus();
                    return;
                }

                // Comprobamos si el móvil está formado solo por números.
                int movil;
                try {
                    movil = Integer.parseInt(binding.etMovil.getText().toString());
                } catch (NumberFormatException e) {
                    binding.etMovil.setError("Escribe un número");
                    binding.etMovil.requestFocus();
                    return;
                }

                // Ya pasó la validación
                Reserva nuevaReserva = new Reserva(jinete, movil, caballo, fecha, hora, comentario);
                long id = reservasController.nuevaReserva(nuevaReserva);
                if (id == -1) {
                    // De alguna manera ocurrió un error
                    Toast.makeText(AgregarReservasActivity.this, "Error al guardar. Intenta de nuevo", Toast.LENGTH_SHORT).show();
                } else {
                    // Terminar
                    finish();
                }
            }
        });

        // El de cancelar simplemente cierra la actividad
        binding.btnCancelarNuevaReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
