package com.example.hlc_to04_raul_villodres;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DownloadService extends Service {

    public static double VALORES;
    public DownloadService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mostrarMensaje("Creando el servicio . . .");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        URL url = null;
        try {
            url = new URL(MainActivity1.URL);
            descargaOkHTTP(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            mostrarMensaje("Error en la URL: " + MainActivity1.URL);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mostrarMensaje("Servicio destruido");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void descargaOkHTTP(URL web) {
        final OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(web)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("Error: ", e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        //throw new IOException("Unexpected code " + response);
                        Log.e("Error: ", "Unexpected code " + response);
                    } else {
                        // Read data on the worker thread
                        final String responseData = response.body().string();
                        // guardar el fichero descargado en memoria externa
                        if (escribirExterna(responseData)) {
                            Log.i("Descarga: ", "fichero descargado");
                            VALORES = Double.parseDouble(responseData);
                            System.out.println(VALORES);
                        } else
                            Log.e("Error ", "no se ha podido descargar");
                    }
                }
            }
        });
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();
    }

    private boolean escribirExterna(String cadena) {
        File miFichero, tarjeta;
        BufferedWriter bw = null;
        boolean correcto = false;

        try {
            tarjeta = Environment.getExternalStorageDirectory();
            miFichero = new File(tarjeta.getAbsolutePath(), "frases.html");
            bw = new BufferedWriter(new FileWriter(miFichero));
            bw.write(cadena);
            Log.i("Información: ", miFichero.getAbsolutePath());
        } catch (IOException e) {
            if (cadena != null)
                Log.e("Error: ", cadena);
            Log.e("Error de E/S", e.getMessage());
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                    correcto = true;
                }
            } catch (IOException e) {
                Log.e("Error al cerrar", e.getMessage());
            }
        }
        return correcto;
    }
}