package com.example.villodres_raul_to02_aplicacionesandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.villodres_raul_to02_aplicacionesandroid.databinding.ActivityMain2Binding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends  AppCompatActivity {

    List <LibroEjercicio2> elements;
    ActivityMain2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();
    }

    public void init(){
        elements = new ArrayList<>();
        elements.add(new LibroEjercicio2("El ingenioso Hidalgo Don Quijote", "Miguel de Cervantes Savaedra", "S.L.U. ESPASA", "x/x/1615", "Las desventuras de un peculiar caballero.", R.drawable.donquijote));
        elements.add(new LibroEjercicio2("Las fuentes del silencio", "Ruta Sepetys", "MAEVA", "28/07/2020", "Desenterrar el pasado es doloroso", R.drawable.fuentesdelsilencio));
        elements.add(new LibroEjercicio2("Harry Potter y la piedra filosofal", "J.K Rowling", "S.A Salamandra", "27/08/2000", "Un joven entra en Hogwarts para iniciarse como mago.", R.drawable.hppiedrafilosofal));
        elements.add(new LibroEjercicio2("Uzumaki", "Junji Ito", "Planeta de Agostini", "29/08/1998", "Aterradora historia donde las espirales son la clave", R.drawable.uzumaki));
        elements.add(new LibroEjercicio2("Coraline ", "Neil Gaiman", "S.A Salamandra", "24/07/2002", "Un mundo de fantasía oscura donde aprendes a valorar lo que tienes.", R.drawable.coraline));
        elements.add(new LibroEjercicio2("El color que cayó del espacio", "H.P lovecraft", "Nordica", "x/09/1927", "En ocasiones, la entidad más temible no es cómo uno se imagina", R.drawable.elcolorquecayodelespacio));


        binding.listRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ListAdapterEjercicio2 adapter = new ListAdapterEjercicio2(elements);

        adapter.setOnItemClickListener(new ListAdapterEjercicio2.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int posicion) {
                String nombre = elements.get(posicion).getNombreLibro();
                String fechaPublicacion = elements.get(posicion).getFechaPublicacionLibro();
                Toast.makeText(MainActivity2.this,  "Nombre: " + nombre + "\n" +  "Fecha publicación: " + fechaPublicacion, Toast.LENGTH_SHORT).show();
            }
        });

        binding.listRecyclerView.setAdapter(adapter);
    }
}