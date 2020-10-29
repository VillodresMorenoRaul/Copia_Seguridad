package com.example.villodres_raul_androidstudio;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText numero;
    Button comprobar;
    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero = (EditText) findViewById(R.id.numeroSeleccionado);
        comprobar = (Button) findViewById(R.id.Comprobar);
        resultado = (TextView) findViewById(R.id.esPrimoONo);

        comprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int numeroInt;
                boolean primo = false;

                try {
                    numeroInt = Integer.parseInt(MainActivity.this.numero.getText().toString());

                    for (int i = 2; i < numeroInt; i++) {
                        if (numeroInt % i == 0) {
                            primo = true;
                        }
                    }
                    if (primo) {
                        resultado.setText("El número " + numeroInt + " no es primo");
                    } else {
                        resultado.setText("El número " + numeroInt + " es primo");
                    }

                } catch(Exception e) {
                    resultado.setText("El campo está vacío");
                }
                
            }
        });
    }
}