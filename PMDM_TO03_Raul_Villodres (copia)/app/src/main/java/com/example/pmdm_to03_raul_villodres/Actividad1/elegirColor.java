package com.example.pmdm_to03_raul_villodres.Actividad1;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pmdm_to03_raul_villodres.Actividad1.MainActivity1;
import com.example.pmdm_to03_raul_villodres.R;
import com.example.pmdm_to03_raul_villodres.databinding.ActivityElegirColorBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class elegirColor extends AppCompatActivity {

    RadioButton rb;
    String color = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_color);
        ActivityElegirColorBinding binding;

        binding = ActivityElegirColorBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.volverActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(binding.group.getCheckedRadioButtonId() == binding.amarilloClaro.getId()) {
                    color = "amarilloClaro";
                } else if (binding.group.getCheckedRadioButtonId() == binding.verde.getId()){
                    color = "verde";
                } else if (binding.group.getCheckedRadioButtonId() == binding.azul.getId()){
                    color = "azul";
                }

                volverActividad();
            }
        });
    }

    public void volverActividad() {
        Intent intent = new Intent(this, MainActivity1.class);
        intent.putExtra("color", color);
        startActivity(intent);
    }
}