package com.example.pmdm_to03_parte2_raul_villodres_moreno;

import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pmdm_to03_parte2_raul_villodres_moreno.databinding.ActivityEditarReservaBinding;

public class EditarReservaActivity extends AppCompatActivity {
    private Reserva reserva;//La reserva que vamos a estar editando
    private ReservasController reservasController;
    View view;
    private ActivityEditarReservaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Configuramos el ViewBinding
        setContentView(R.layout.activity_main1);
        binding = ActivityEditarReservaBinding.inflate(getLayoutInflater());
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
        reservasController = new ReservasController(EditarReservaActivity.this);

        // Rearmar la reserva
        // Nota: igualmente solamente podríamos mandar el id y recuperar la reserva de la BD
        long idReserva = extras.getLong("idReserva");
        String nombreJinete = extras.getString("nombreJinete");
        int numeroMovil = extras.getInt("numeroMovil");
        String nombreCaballo = extras.getString("nombreCaballo");
        String fechaReserva = extras.getString("fechaReserva");
        String horaReserva = extras.getString("horaReserva");
        String comentario = extras.getString("comentario");
        reserva = new Reserva(nombreJinete, numeroMovil, nombreCaballo, fechaReserva, horaReserva, comentario ,idReserva);

        // Rellenar los EditText con los datos de la reserva
        binding.etEditarMovil.setText(String.valueOf(reserva.getMovil()));
        binding.etEditarJinete.setText(reserva.getJinete());
        binding.etEditarCaballo.setText(String.valueOf(reserva.getCaballo()));
        binding.etEditarFecha.setText(String.valueOf(reserva.getFecha()));
        binding.etEditarHora.setText(String.valueOf(reserva.getHora()));
        binding.etEditarComentario.setText(String.valueOf(reserva.getComentario()));

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
                binding.etEditarMovil.setError(null);
                binding.etEditarJinete.setError(null);
                binding.etEditarCaballo.setError(null);
                binding.etEditarFecha.setError(null);
                binding.etEditarHora.setError(null);
                binding.etEditarComentario.setError(null);
                // Crear la reserva con los nuevos cambios pero ponerle
                // el id de la anterior
                String nuevoJinete =  binding.etEditarJinete.getText().toString();
                String posibleNuevoMovil =  binding.etEditarMovil.getText().toString();
                String nuevoCaballo =  binding.etEditarCaballo.getText().toString();
                String nuevaFecha =  binding.etEditarFecha.getText().toString();
                String nuevaHora =  binding.etEditarHora.getText().toString();
                String nuevoComentario =  binding.etEditarComentario.getText().toString();

                if (nuevoJinete.isEmpty()) {
                    binding.etEditarJinete.setError("Escribe el Jinete");
                    binding.etEditarJinete.requestFocus();
                    return;
                }

                if (posibleNuevoMovil.isEmpty()) {
                    binding.etEditarMovil.setError("Escribe el Movil");
                    binding.etEditarMovil.requestFocus();
                    return;
                }

                if (nuevoCaballo.isEmpty()) {
                    binding.etEditarCaballo.setError("Escribe el nombre");
                    binding.etEditarCaballo.requestFocus();
                    return;
                }

                if (nuevaFecha.isEmpty()) {
                    binding.etEditarFecha.setError("Escribe la fecha");
                    binding.etEditarFecha.requestFocus();
                    return;
                }

                if (nuevaHora.isEmpty()) {
                    binding.etEditarHora.setError("Escribe la hora");
                    binding.etEditarHora.requestFocus();
                    return;
                }

                if (nuevoComentario.isEmpty()) {
                    binding.etEditarComentario.setError("Escribe el comentario");
                    binding.etEditarComentario.requestFocus();
                    return;
                }

                // Si no es entero, igualmente marcar error
                int nuevoMovil;
                try {
                    nuevoMovil = Integer.parseInt(posibleNuevoMovil);
                } catch (NumberFormatException e) {
                    binding.etEditarMovil.setError("Escribe un número");
                    binding.etEditarMovil.requestFocus();
                    return;
                }
                // Si llegamos hasta aquí es porque los datos ya están validados
                Reserva reservaConNuevosCambios = new Reserva(nuevoJinete, nuevoMovil, nuevoCaballo, nuevaFecha, nuevaHora, nuevoComentario ,reserva.getId());
                int filasModificadas = reservasController.guardarCambios(reservaConNuevosCambios);
                if (filasModificadas != 1) {
                    // De alguna forma ocurrió un error porque se debió modificar únicamente una fila
                    Toast.makeText(EditarReservaActivity.this, "Error guardando cambios. Intente de nuevo.", Toast.LENGTH_SHORT).show();
                } else {
                    // Si las cosas van bien, volvemos a la principal
                    // cerrando esta actividad
                    finish();
                }
            }
        });
    }
}
