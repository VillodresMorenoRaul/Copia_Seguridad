package com.example.pdmd_examenfinal_raul_villodres;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Enlaces> listaDeEnlaces;
    private RecyclerView recyclerView;
    private com.example.pdmd_examenfinal_raul_villodres.AdaptadorEnlaces adaptadorEnlaces;
    private EnlaceController enlaceController;
    private FloatingActionButton fabAgregarEnlace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        enlaceController = new EnlaceController(com.example.pdmd_examenfinal_raul_villodres.MainActivity.this);

        // Instanciar vistas
        recyclerView = findViewById(R.id.recyclerEnlaces);
        fabAgregarEnlace = findViewById(R.id.floatButtonAgregarEnlace);


        // Por defecto es una lista vacía,
        // se la ponemos al adaptador y configuramos el recyclerView
        listaDeEnlaces = new ArrayList<>();
        adaptadorEnlaces = new com.example.pmdm_to03_raul_villodres.Activity3.AdaptadorEnlaces(listaDeEnlaces);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptadorEnlaces);

        // Una vez que ya configuramos el RecyclerView le ponemos los datos de la BD
        refrescarListaDeEnlaces();

        // Listener de los clicks en la lista, o sea el RecyclerView
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override // Un toque sencillo
            public void onClick(View view, int position) {
                // Pasar a la actividad EditarenlaceActivity.java
                Enlaces enlaceSeleccionado = listaDeEnlaces.get(position);
                Intent intent = new Intent(com.example.pmdm_to03_raul_villodres.Activity3.MainActivity3.this, EditarEnlaceActivity.class);
                intent.putExtra("id", enlaceSeleccionado.getId());
                intent.putExtra("nombre", enlaceSeleccionado.getNombre());
                intent.putExtra("link", enlaceSeleccionado.getLink());
                intent.putExtra("email", enlaceSeleccionado.getEmail());
                intent.putExtra("categoria", enlaceSeleccionado.getEmail());
                startActivity(intent);
            }

            //Acción con un toque largo
            @Override
            public void onLongClick(View view, int position) {
                final Enlaces enlaceParaEliminar = listaDeEnlaces.get(position);
                AlertDialog dialog = new AlertDialog
                        .Builder(com.example.pmdm_to03_raul_villodres.Activity3.MainActivity3.this)
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                enlaceController.eliminarEnlace(enlaceParaEliminar);
                                refrescarListaDeEnlaces();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar")
                        .setMessage("¿Eliminar a la enlace " + enlaceParaEliminar.getNombre() + "?")
                        .create();
                dialog.show();

            }
        }));

        // Listener del boton flotante
        fabAgregarEnlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.pmdm_to03_raul_villodres.Activity3.MainActivity3.this, AgregarEnlace.class);
                startActivity(intent);
            }
        });

        fabAgregarEnlace.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(com.example.pmdm_to03_raul_villodres.Activity3.MainActivity3.this)
                        .setTitle("Acerca de")
                        .setMessage("Crud creado por Raul")
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
        refrescarListaDeEnlaces();
    }

    public void refrescarListaDeEnlaces() {
        if (adaptadorEnlaces == null) return;
        listaDeEnlaces = enlaceController.obtenerEnlace();
        adaptadorEnlaces.setListaDeEnlaces(listaDeEnlaces);
        adaptadorEnlaces.notifyDataSetChanged();
    }
}

