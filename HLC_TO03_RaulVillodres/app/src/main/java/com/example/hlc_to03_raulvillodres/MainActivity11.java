package com.example.hlc_to03_raulvillodres;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hlc_to03_raulvillodres.databinding.ActivityMain11Binding;
import com.example.hlc_to03_raulvillodres.databinding.ActivityMain1Binding;
import com.example.hlc_to03_raulvillodres.network.Conexion;
import com.example.hlc_to03_raulvillodres.network.Resultado;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity11 extends AppCompatActivity implements View.OnClickListener {
    private ActivityMain11Binding binding;
    private static final int REQUEST_CONNECT = 1;
    TareaAsincrona tareaAsincrona;
    URL url;
    String URL = "";
    ProgressDialog barraProgreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);
        binding = ActivityMain11Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.Descargar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {

            //Declaramos la URL con el valor del campo de texto
            url = new URL(binding.CampoUrl.getText().toString());
            URL = binding.CampoUrl.getText().toString();

            //Obtenemos la extensión y se la asignamos a una variable
            String fileExt = MimeTypeMap.getFileExtensionFromUrl(url.toString());
            binding.Titulo.setText("La extensión es de tipo: " + fileExt);

            //Actuamos dependiendo de si la extensión es la de una imagen o no, volviendo visible el webview o imageView e invisible el que no se usa
            if(fileExt.equals("png") || fileExt.equals("jpg") || fileExt.equals("jpeg")){
                binding.imagen.setVisibility(View.VISIBLE);
                binding.WebView.setVisibility(View.INVISIBLE);

                new DownloadImage().execute(URL);

            } else {

                //En este caso la visibilidad se alterna por ser un caso distinto a una imagen
                binding.WebView.setVisibility(View.VISIBLE);
                binding.imagen.setVisibility(View.INVISIBLE);

                tareaAsincrona = new TareaAsincrona(this);
                tareaAsincrona.execute(url);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
            mostrarError(e.getMessage());
        }
    }

    private void mostrarError(String message){
        Toast.makeText( this, message, Toast.LENGTH_SHORT).show();
    }



    //Accionar cuando la tarea tiene formato html
    public class TareaAsincrona extends AsyncTask<URL, Void, Resultado> {

        private ProgressDialog progreso;
        private Context context;

        public TareaAsincrona(Context context){
            this.context = context;
        }

        @Override
        public void onPreExecute(){
            // Creamos el progress Dialog
            barraProgreso = new ProgressDialog(MainActivity11.this);

            // Ponemos titulo, mensaje y marcamos si es indeterminada
            barraProgreso.setTitle("Descargando una web");
            barraProgreso.setMessage("Descargando una página web usando Async Task, por favor espere......");
            barraProgreso.setIndeterminate(false);

            //Para terminar lo mostramos
            barraProgreso.show();
        }
        @Override
        protected Resultado doInBackground(URL... urls) {
            Resultado resultado;

            try {
                resultado = Conexion.conectarJava(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Error de conexion ", e.getMessage());
                resultado = new Resultado();
                resultado.setCodigo(500);
                resultado.setMensaje(e.getMessage());
            }
            return resultado;
        }

        protected void onPostExecute(Resultado resultado){
            super.onPostExecute(resultado);
            barraProgreso.dismiss();
            if(resultado.getCodigo() == HttpURLConnection.HTTP_OK){
                binding.WebView.loadDataWithBaseURL(String.valueOf(url), resultado.getContenido(),"bitmap/png", "UTF-8", null);

            } else {
                mostrarError(resultado.getMensaje());
                binding.WebView.loadDataWithBaseURL(String.valueOf(url), resultado.getMensaje(), "text/html", "UTF-8", null);

            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            progreso.dismiss();
            mostrarError("cancelado");
        }
    }

    // DownloadImage AsyncTask
    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            
            // Creamos el progress Dialog
            barraProgreso = new ProgressDialog(MainActivity11.this);
            
           // Ponemos titulo, mensaje y marcamos si es indeterminada
            barraProgreso.setTitle("Descargando una imagen");
            barraProgreso.setMessage("Descargando una imagen usando Async Task, por favor espere......");
            barraProgreso.setIndeterminate(false);
            
            //Para terminar lo mostramos
            barraProgreso.show();
        }

        @Override
        protected Bitmap doInBackground(String... URL) {

            //Creamos las variables necesarias
            String imageURL = URL[0];
            Bitmap bitmap = null;

            try {

                //Descargamos la imagen de la URL entregada y decodificamos el bitmap correspondiente a la URL
                InputStream input = new java.net.URL(imageURL).openStream();
                bitmap = BitmapFactory.decodeStream(input);

            } catch (Exception e) {
                e.printStackTrace();
                mostrarError(e.getMessage());
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            //Asignamos a la imagen el bitmap obtenido
            binding.imagen.setImageBitmap(result);
            // Cerramos el Progress Dialog
            barraProgreso.dismiss();
        }
    }

}