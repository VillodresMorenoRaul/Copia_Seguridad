package com.example.pmdm_to03_raul_villodres.Actividad1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pmdm_to03_raul_villodres.Actividad1.MainActivity1;
import com.example.pmdm_to03_raul_villodres.R;
import com.example.pmdm_to03_raul_villodres.databinding.ActivityCambiarValorBinding;

public class cambiarValor extends AppCompatActivity {

    String ratio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_valor);

        ActivityCambiarValorBinding binding;

        binding = ActivityCambiarValorBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



        binding.CambiarValor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ratio = binding.campoRatio.getText().toString();
                volverActividad();
            }
        });
    }

    public void volverActividad() {

        if(ratio.isEmpty()){
            Toast.makeText(getApplicationContext(), "El campo está vacio",Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, MainActivity1.class);
            intent.putExtra("ratioDevuelto",ratio);
            startActivity(intent);
        }

    }
}