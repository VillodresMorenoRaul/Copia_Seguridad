package com.example.hlc_to03_raulvillodres;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import com.example.hlc_to03_raulvillodres.databinding.ActivityMain2Binding;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;
    private TextWatcher text = null;
    public final String URL = "https://dam.org.es/ficheros/rate.txt";
    public Double Ratio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Configuramos el ViewBinding
        setContentView(R.layout.activity_main1);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Desactivamos la posibilidad de escribir en el campoResultado
        binding.campoResultado.setEnabled(false);


        OkHttpClient client = new OkHttpClient();

        //Si se hace click en el botón EurosADolares
        binding.EurosADolares.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EurosADolaresPulsado();
            }
        });

        binding.DolaresAEuros.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                DolaresAEurosPulsado();
            }
        });

       text = new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {


               if(binding.campoValor.getText().toString().equals("")) {
                    binding.campoResultado.setText("");
               } else {

               Double cantidad = Double.parseDouble(binding.campoValor.getText().toString());
               Request request = new Request.Builder().url(URL).build();


                   client.newCall(request).enqueue(new Callback() {
                       @Override
                       public void onFailure(Call call, IOException e) {

                           binding.campoResultado.setText("0");
                           binding.campoResultado.setText("0");

                           Toast.makeText(getApplicationContext(), "Ha habido un error", Toast.LENGTH_LONG);

                       }

                       @Override
                       public void onResponse(Call call, Response response) throws IOException {
                           if(response.isSuccessful()) {

                               try {
                                   Ratio = Double.valueOf(response.body().string());
                               } catch (Exception e){
                                   Ratio = 0.0;
                               }


                               MainActivity2.this.runOnUiThread(new Runnable() {

                                   @Override
                                   public void run() {

                                       //Si el switch marcado es el de dolaresAEuros aplicamos el ratio de forma adecuada
                                       if (binding.DolaresAEuros.isChecked()){
                                                   binding.campoResultado.setText(String.valueOf(cantidad * Ratio));

                                       //Actuamos en consecuencia en caso de que el valor marcado sea el de EurosADolares
                                       } else if (binding.EurosADolares.isChecked()) {
                                               binding.campoResultado.setText(String.valueOf(cantidad / Ratio));

                                       } else {
                                           binding.campoResultado.setText("0");
                                           binding.campoValor.setText("0");
                                       }
                                   }
                               });
                           }
                       }
                   });
               }
           }


           @Override
           public void afterTextChanged(Editable s) {

           }

       };

       binding.campoValor.addTextChangedListener(text);
    }

    //Estos dos métodos resetean el texto al cambiar el JRadioButton seleccionado
    public void EurosADolaresPulsado(){
        binding.campoValor.setText("");
        binding.campoResultado.setText("");
    }

    public void DolaresAEurosPulsado(){
        binding.campoValor.setText("");
        binding.campoResultado.setText("");
    }
}