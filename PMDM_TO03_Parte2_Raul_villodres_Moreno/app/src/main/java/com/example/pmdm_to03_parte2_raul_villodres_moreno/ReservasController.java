package com.example.pmdm_to03_parte2_raul_villodres_moreno;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ReservasController {
    private AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private String NOMBRE_TABLA = "tablareservas";

    public ReservasController(Context contexto) {
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(contexto);
    }


    public int eliminarReserva(Reserva reserva) {

        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(reserva.getId())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }

    public long nuevaReserva(Reserva reserva) {
        // writable porque vamos a insertar
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("jinete", reserva.getJinete());
        valoresParaInsertar.put("movil", reserva.getMovil());
        valoresParaInsertar.put("caballo", reserva.getCaballo());
        valoresParaInsertar.put("fecha", reserva.getFecha());
        valoresParaInsertar.put("hora", reserva.getHora());
        valoresParaInsertar.put("comentario", reserva.getComentario());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public int guardarCambios(Reserva reservaEditada) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("jinete", reservaEditada.getJinete());
        valoresParaActualizar.put("movil", reservaEditada.getMovil());
        valoresParaActualizar.put("caballo", reservaEditada.getCaballo());
        valoresParaActualizar.put("fecha", reservaEditada.getFecha());
        valoresParaActualizar.put("hora", reservaEditada.getHora());
        valoresParaActualizar.put("comentario", reservaEditada.getComentario());
        // where id...
        String campoParaActualizar = "id = ?";
        // ... = idReserva
        String[] argumentosParaActualizar = {String.valueOf(reservaEditada.getId())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public ArrayList<Reserva> obtenerReservas() {
        ArrayList<Reserva> reservas = new ArrayList<>();
        // readable porque no vamos a modificar, solamente leer
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getReadableDatabase();

        // SELECT
        String[] columnasAConsultar = {"jinete", "movil", "caballo", "fecha", "hora", "comentario", "id"};
        Cursor cursor = baseDeDatos.query(
                NOMBRE_TABLA,//from reservas
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
            return reservas;

        }
        // Si no hay datos, igualmente regresamos la lista vacía
        if (!cursor.moveToFirst()) return reservas;

        // En caso de que sí haya, iteramos y vamos agregando los
        // datos a la lista de reservas
        do {

            String jineteObtenidoDeBD = cursor.getString(0);
            int movilObtenidoDeBD = cursor.getInt(1);
            String caballoObtenidoDeBD = cursor.getString(2);
            String fechaObtenidaDeBD = cursor.getString(3);
            String horaObtenidaDeBD = cursor.getString(4);
            String comentarioObtenidoDeBD = cursor.getString(5);
            long idReserva = cursor.getLong(6);
            Reserva reservaObtenidaDeBD = new Reserva(jineteObtenidoDeBD, movilObtenidoDeBD , caballoObtenidoDeBD, fechaObtenidaDeBD, horaObtenidaDeBD, comentarioObtenidoDeBD, idReserva);
            reservas.add(reservaObtenidaDeBD);
        } while (cursor.moveToNext());

        // Fin del ciclo. Cerramos cursor y regresamos la lista de reservas :)
        cursor.close();
        return reservas;
    }
}