package com.example.pmdm_to03_raul_villodres.Activity3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class EnlaceController {
    private dbHelper ayudanteBaseDeDatos;
    private String NOMBRE_TABLA = "enlaces";

    public EnlaceController(Context contexto){
        
        ayudanteBaseDeDatos = new dbHelper(contexto);
    }

    public int eliminarEnlace(Enlaces enlace){
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(enlace.getId())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }

    public long nuevoEnlace(Enlaces enlace){
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombre", enlace.getNombre());
        valoresParaInsertar.put("link", enlace.getLink());
        valoresParaInsertar.put("email", enlace.getEmail());
        valoresParaInsertar.put("categoria", enlace.getCategoria());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public int guardarCambios(Enlaces enlaceEditado){
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombre", enlaceEditado.getNombre());
        valoresParaActualizar.put("link", enlaceEditado.getLink());
        valoresParaActualizar.put("email", enlaceEditado.getEmail());
        valoresParaActualizar.put("categoria", enlaceEditado.getCategoria());

        String campoParaActualizar = "id = ?";
        String[] argumentosParaActualizar = {String.valueOf(enlaceEditado.getId())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public ArrayList<Enlaces> obtenerEnlace(){
        ArrayList<Enlaces> enlace = new ArrayList<>();
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getReadableDatabase();
        String[] columnasAConsultar = {"nombre", "link", "email", "categoria"};
        Cursor cursor = baseDeDatos.query(NOMBRE_TABLA, columnasAConsultar, null, null, null, null, null);

        if(cursor == null){
            return enlace;
        }

        if(!cursor.moveToFirst()){
            return enlace;
        }

        do {
            String nombreObtenidoDeBD = cursor.getString(0);
            String linkObtenidoDeBD = cursor.getString(1);
            String emailObtenidoDeBD = cursor.getString(2);
            String categoriaObtenidadDeBD = cursor.getString(3);
            Enlaces enlaceObtenidoDeBD = new Enlaces(nombreObtenidoDeBD, emailObtenidoDeBD, linkObtenidoDeBD, categoriaObtenidadDeBD);
            enlace.add(enlaceObtenidoDeBD);
        } while (cursor.moveToNext());

        cursor.close();
        return enlace;
    }
}
