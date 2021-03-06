package com.example.pmdm_to03_raul_villodres.Actividad2;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Memoria {
    public static boolean escribirExterna(String fichero, String cadena) throws IOException {
        File miFichero, tarjeta;

        tarjeta = Environment.getExternalStorageDirectory();
        miFichero = new File(tarjeta.getAbsolutePath(), fichero);

        return escribir(miFichero, cadena);
    }

    private static boolean escribir(File fichero, String cadena) throws IOException {

        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        boolean correcto = true;

        fos = new FileOutputStream(fichero);
        osw = new OutputStreamWriter(fos);
        bw = new BufferedWriter(osw);

        bw.write(cadena);
        bw.close();

        return correcto;

    }

    public static String mostrarPropiedades(File fichero) {

        SimpleDateFormat formato;
        StringBuilder texto = new StringBuilder();

        if (fichero.exists()) {
            texto.append("Nombre: " + fichero.getName() + "\n");
            texto.append("Ruta: " + fichero.getAbsolutePath() + "\n");
            texto.append("Tamaño en Bytes: " + Long.toString(fichero.length()) + "\n");
            formato = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.getDefault());
            texto.append("Fecha: " + formato.format(new Date(fichero.lastModified())));

        } else {
            texto.append("No existe el fichero: " + fichero.getName());
        }

        return texto.toString();

    }

    public static String mostrarPropiedadesExterna(String fichero) {

        File miFichero, tarjeta;

        tarjeta = Environment.getExternalStorageDirectory();
        miFichero = new File(tarjeta.getAbsolutePath(), fichero);

        return mostrarPropiedades(miFichero);

    }

    public static boolean disponibleEscritura() {

        boolean escritura = false;

        String estado = Environment.getExternalStorageState();

        if (estado.equals(Environment.MEDIA_MOUNTED)) {
            escritura = true;
        }

        return escritura;

    }

    public static boolean disponibleLectura() {

        boolean lectura = false;

        String estado = Environment.getExternalStorageState();

        if (estado.equals(Environment.MEDIA_MOUNTED) || estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            lectura = true;
        }

        return lectura;

    }

    public static String leerExterna(String fichero) throws IOException {

        File miFichero, tarjeta;

        tarjeta = Environment.getExternalStorageDirectory();
        miFichero = new File(tarjeta.getAbsolutePath(), fichero);

        return leer(miFichero);

    }

    private static String leer(File fichero) throws IOException {

        FileInputStream fis = null;
        InputStreamReader isw = null;
        BufferedReader br = null;
        String linea;
        StringBuilder miCadena = new StringBuilder();

        fis = new FileInputStream(fichero);
        isw = new InputStreamReader(fis);
        br = new BufferedReader(isw);
        while ((linea = br.readLine()) != null) {
            //miCadena.append(linea.append() + "\n");
            miCadena.append(linea + "\n");
            br.close();
        }
        return miCadena.toString();
    }
}