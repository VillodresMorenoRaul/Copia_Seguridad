package com.example.pmdm_to03_raul_villodres.Activity3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.pmdm_to03_raul_villodres.Actividad2.MainActivity2;
import com.example.pmdm_to03_raul_villodres.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    private List<Enlaces> listaDeEnlaces;
    private RecyclerView recyclerView;
    private AdaptadorEnlaces adaptadorEnlaces;
    private EnlaceController enlaceController;
    private FloatingActionButton fabAgregarEnlace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        
        enlaceController = new EnlaceController(MainActivity3.this);

        // Instanciar vistas
        recyclerView = findViewById(R.id.recyclerEnlaces);
        fabAgregarEnlace = findViewById(R.id.floatButtonAgregarEnlace);


        // Por defecto es una lista vacía,
        // se la ponemos al adaptador y configuramos el recyclerView
        listaDeEnlaces = new ArrayList<>();
        adaptadorEnlaces = new AdaptadorEnlaces(listaDeEnlaces);
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
                Intent intent = new Intent(MainActivity3.this, EditarEnlaceActivity.class);
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
                android.app.AlertDialog dialog = new android.app.AlertDialog
                        .Builder(MainActivity3.this)
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
                Intent intent = new Intent(MainActivity3.this, AgregarEnlace.class);
                startActivity(intent);
            }
        });

        fabAgregarEnlace.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(MainActivity3.this)
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

