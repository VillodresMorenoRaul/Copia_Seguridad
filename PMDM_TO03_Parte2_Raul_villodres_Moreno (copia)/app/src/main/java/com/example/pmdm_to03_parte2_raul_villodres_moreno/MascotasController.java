package com.example.pmdm_to03_parte2_raul_villodres_moreno;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MascotasController {
    private com.example.pmdm_to03_parte2_raul_villodres_moreno.AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private String NOMBRE_TABLA = "tablamascotas";

    public MascotasController(Context contexto) {
        ayudanteBaseDeDatos = new com.example.pmdm_to03_parte2_raul_villodres_moreno.AyudanteBaseDeDatos(contexto);
    }


    public int eliminarMascota(com.example.pmdm_to03_parte2_raul_villodres_moreno.Mascota mascota) {

        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(mascota.getId())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }

    public long nuevaMascota(com.example.pmdm_to03_parte2_raul_villodres_moreno.Mascota mascota) {
        // writable porque vamos a insertar
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombre", mascota.getNombre());
        valoresParaInsertar.put("edad", mascota.getEdad());
        valoresParaInsertar.put("color", mascota.getColor());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public int guardarCambios(com.example.pmdm_to03_parte2_raul_villodres_moreno.Mascota mascotaEditada) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombre", mascotaEditada.getNombre());
        valoresParaActualizar.put("edad", mascotaEditada.getEdad());
        valoresParaActualizar.put("color", mascotaEditada.getColor());
        // where id...
        String campoParaActualizar = "id = ?";
        // ... = idMascota
        String[] argumentosParaActualizar = {String.valueOf(mascotaEditada.getId())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public ArrayList<Mascota> obtenerMascotas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();
        // readable porque no vamos a modificar, solamente leer
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getReadableDatabase();
        // SELECT nombre, edad, id
        String[] columnasAConsultar = {"nombre", "edad", "color", "id"};
        Cursor cursor = baseDeDatos.query(
                NOMBRE_TABLA,//from mascotas
                columnasAConsultar,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor == null) {
            /*
                Salimos aquí porque hubo un error, regresar
                lista vacía
             */
            return mascotas;

        }
        // Si no hay datos, igualmente regresamos la lista vacía
        if (!cursor.moveToFirst()) return mascotas;

        // En caso de que sí haya, iteramos y vamos agregando los
        // datos a la lista de mascotas
        do {
            // El 0 es el número de la columna, como seleccionamos
            // nombre, edad,id entonces el nombre es 0, edad 1 e id es 2
            String nombreObtenidoDeBD = cursor.getString(0);
            int edadObtenidaDeBD = cursor.getInt(1);
            String colorObtenidoDeBD = cursor.getString(2);
            long idMascota = cursor.getLong(3);
            com.example.pmdm_to03_parte2_raul_villodres_moreno.Mascota mascotaObtenidaDeBD = new com.example.pmdm_to03_parte2_raul_villodres_moreno.Mascota(nombreObtenidoDeBD, edadObtenidaDeBD, colorObtenidoDeBD, idMascota);
            mascotas.add(mascotaObtenidaDeBD);
        } while (cursor.moveToNext());

        // Fin del ciclo. Cerramos cursor y regresamos la lista de mascotas :)
        cursor.close();
        return mascotas;
    }
}