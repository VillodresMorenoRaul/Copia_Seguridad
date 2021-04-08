package com.example.pmdm_to03_raul_villodres.Actividad1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.pmdm_to03_raul_villodres.R;
import com.example.pmdm_to03_raul_villodres.databinding.ActivityMain1Binding;

public class MainActivity1 extends AppCompatActivity { //Ahora no se extiende a AppCompatActivity, sino que simplemente a Activity

    private ActivityMain1Binding binding;
    private TextWatcher text = null;
    public final String URL = "https://dam.org.es/ficheros/rate.txt";
    public Double Ratio;
    public boolean EurosADolares = true;
    Toolbar toolbar;
    String color;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Configuramos el ViewBinding
        setContentView(R.layout.activity_main1);
        binding = ActivityMain1Binding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);

        //Toolbar
        toolbar = findViewById(R.id.miToolbar);
        setSupportActionBar(toolbar);

        //Desactivamos la posibilidad de escribir en el campoResultado
        binding.campoResultado.setEnabled(false);

        binding.cambioColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CambiarColor();
            }
        });

        //Prueba
        try {
            Intent myIntent = getIntent(); // gets the previously created intent
            color = myIntent.getStringExtra("color"); // will return "FirstKeyValue"
            color.trim();

            switch(color){
                case "verde":
                    view.setBackgroundColor(Color.GREEN);
                    break;

                case "amarilloClaro":
                    view.setBackgroundColor(Color.YELLOW);
                    break;

                case "azul":
                    view.setBackgroundColor(Color.BLUE);
                    break;

                default:
                    view.setBackgroundColor(Color.WHITE);
            }

            Toast.makeText(getApplicationContext(), "Color: " + color,Toast.LENGTH_SHORT).show();

        } catch(Exception e){
            Toast.makeText(getApplicationContext(), "Color predeterminado",Toast.LENGTH_SHORT).show();
        }


        //Si se hace click en el botón EurosADolares
        binding.EurosADolares.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EurosADolaresPulsado();
            }
        });

        binding.DolaresAEuros.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DolaresAEurosPulsado();
            }
        });

        text = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                try {
                    Intent myIntent = getIntent(); // gets the previously created intent
                    String RatioTemporal= myIntent.getStringExtra("ratioDevuelto"); // will return "FirstKeyValue"
                    Ratio = Double.valueOf(RatioTemporal);
                } catch(Exception e){
                    Ratio = 1.2034;
                }


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (binding.campoValor.getText().toString().equals("")) {
                    binding.campoResultado.setText("");
                } else {
                    Double cantidad = Double.parseDouble(binding.campoValor.getText().toString());

                    if(EurosADolares){
                        Double resultado = cantidad * Ratio;
                        binding.campoResultado.setText(resultado.toString());
                    } else {
                        Double resultado = cantidad / Ratio;
                        binding.campoResultado.setText(resultado.toString());
                    }
                }
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        binding.campoValor.addTextChangedListener(text);
    }

    //Estos dos métodos resetean el texto al cambiar el JRadioButton seleccionado
    public void EurosADolaresPulsado() {
        binding.campoValor.setText("");
        binding.campoResultado.setText("");
        EurosADolares = true;
    }

    public void DolaresAEurosPulsado() {
        binding.campoValor.setText("");
        binding.campoResultado.setText("");
        EurosADolares = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbarmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()){

            case R.id.settings:
                AbrirSettings();
                break;

            case R.id.acercaDe:
                Toast.makeText(getApplicationContext(), "Nombre: Raul" + "\nApellidos: Villodres Moreno" + "\nCurso: 2DAM",Toast.LENGTH_SHORT).show();
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void AbrirSettings(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void CambiarColor(){
        //Cambiar color
        try {
            Intent myIntent = getIntent(); // gets the previously created intent
            color = myIntent.getStringExtra("color"); // will return "FirstKeyValue"
            color.trim();
            Toast.makeText(getApplicationContext(), color,Toast.LENGTH_SHORT).show();

            switch(color){
                case "verde":
                    view.setBackgroundColor(Color.GREEN);
                    break;

                case "amarilloClaro":
                    view.setBackgroundColor(Color.YELLOW);
                    break;

                case "azul":
                    view.setBackgroundColor(Color.BLUE);
                    break;
            }

        } catch(Exception e){
            Toast.makeText(getApplicationContext(), "error",Toast.LENGTH_SHORT).show();
        }
    }


}
