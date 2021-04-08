package com.example.pmdm_to03_parte2_raul_villodres_moreno;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmdm_to03_parte2_raul_villodres_moreno.databinding.ActivityMain1Binding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity1 extends AppCompatActivity {
    private List<Reserva> listaDeReservas;
    private AdaptadorReservas adaptadorReservas;
    private ReservasController reservasController;
    private ActivityMain1Binding binding;
    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Configuramos el ViewBinding
        setContentView(R.layout.activity_main1);
        binding = ActivityMain1Binding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);

        //Toolbar
        setSupportActionBar(binding.miToolbar);

        // Definir nuestro controlador
        reservasController = new ReservasController(MainActivity1.this);

        // Por defecto es una lista vacía,
        // se la ponemos al adaptador y configuramos el recyclerView
        listaDeReservas = new ArrayList<>();
        adaptadorReservas = new AdaptadorReservas(listaDeReservas);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        binding.recyclerView.setLayoutManager(mLayoutManager);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setAdapter(adaptadorReservas);

        // Una vez que ya configuramos el RecyclerView le ponemos los datos de la BD
        refrescarListaDeReservas();

        // Listener de los clicks en la lista, o sea el RecyclerView
        binding.recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), binding.recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override // Un toque sencillo
            public void onClick(View view, int position) {
                // Pasar a la actividad EditarReservaActivity.java
                Reserva reservaSeleccionada = listaDeReservas.get(position);
                Intent intent = new Intent(MainActivity1.this, EditarReservaActivity.class);
                intent.putExtra("idReserva", reservaSeleccionada.getId());
                intent.putExtra("nombreJinete", reservaSeleccionada.getJinete());
                intent.putExtra("numeroMovil", reservaSeleccionada.getMovil());
                intent.putExtra("nombreCaballo", reservaSeleccionada.getCaballo());
                intent.putExtra("fechaReserva", reservaSeleccionada.getFecha());
                intent.putExtra("horaReserva", reservaSeleccionada.getHora());
                intent.putExtra("comentario", reservaSeleccionada.getComentario());
                startActivity(intent);
            }

            @Override // Un toque largo
            public void onLongClick(View view, int position) {
                final Reserva reservaParaEliminar = listaDeReservas.get(position);
                AlertDialog dialog = new AlertDialog
                        .Builder(MainActivity1.this)
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                reservasController.eliminarReserva(reservaParaEliminar);
                                refrescarListaDeReservas();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar")
                        .setMessage("¿Eliminar la reserva del jinete " + reservaParaEliminar.getJinete() + "?")
                        .create();
                dialog.show();

            }
        }));

        // Listener del FAB
        binding.fabAgregarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simplemente cambiamos de actividad
                Intent intent = new Intent(MainActivity1.this, AgregarReservasActivity.class);
                startActivity(intent);
            }
        });

        // Créditos
        binding.fabAgregarReserva.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(MainActivity1.this)
                        .setTitle("Acerca de")
                        .setMessage("CRUD de Android con SQLite creado por parzibyte [parzibyte.me]\n\nIcons made by Freepik from www.flaticon.com ")
                        .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogo, int which) {
                                dialogo.dismiss();
                            }
                        })
                        .setPositiveButton("Sitio web", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intentNavegador = new Intent(Intent.ACTION_VIEW, Uri.parse("https://parzibyte.me"));
                                startActivity(intentNavegador);
                            }
                        })
                        .create()
                        .show();
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        refrescarListaDeReservas();
    }

    //Obtenemos la lista de la BD y se la ponemos al RecyclerView
    public void refrescarListaDeReservas() {
        if (adaptadorReservas == null) return;
        listaDeReservas = reservasController.obtenerReservas();
        adaptadorReservas.setListaDeReservas(listaDeReservas);
        adaptadorReservas.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbarmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Settings) {
            startActivity(new Intent(MainActivity1.this, SettingsActivity.class));
            return true;
        }

        if(id == R.id.acercaDe){
            Toast.makeText(getApplicationContext(), "Nombre: Raul" + "\nApellidos: Villodres Moreno" + "\nCurso: 2DAM",Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

}
