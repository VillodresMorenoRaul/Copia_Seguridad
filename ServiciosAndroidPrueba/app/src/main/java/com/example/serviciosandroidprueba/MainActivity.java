package com.example.serviciosandroidprueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private boolean comprobarPermiso() {
        //https://developer.android.com/training/permissions/requesting?hl=es-419
        String permiso = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        // Manifest.permission.INTERNET
        boolean concedido = false;
        // comprobar los permisos
        if (ActivityCompat.checkSelfPermission(this, permiso) != PackageManager.PERMISSION_GRANTED) {
            // pedir los permisos necesarios, porque no están concedidos
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permiso)) {
                concedido = false;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{permiso}, REQUEST_CONNECT);
                // Cuando se cierre el cuadro de diálogo se ejecutará onRequestPermissionsResult
            }
        } else {
            concedido = true;
        }
        return concedido;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        String permiso = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        //Manifest.permission.INTERNET;
        // chequeo los permisos de nuevo
        if (requestCode == REQUEST_CONNECT)
            if (ActivityCompat.checkSelfPermission(this, permiso) == PackageManager.PERMISSION_GRANTED)
                // permiso concedido
                startService(new Intent(MainActivity.this, DownloadService.class));
            else
                // no hay permiso
                mostrarError("No se ha concedido permiso para conectarse a Internet");
    }
    private void mostrarError(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
}