package com.example.pmdm_to03_parte2_raul_villodres_moreno;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorMascotas extends RecyclerView.Adapter<AdaptadorMascotas.MyViewHolder> {

    private List<Mascota> listaDeMascotas;

    public void setListaDeMascotas(List<Mascota> listaDeMascotas) {
        this.listaDeMascotas = listaDeMascotas;
    }

    public AdaptadorMascotas(List<Mascota> mascotas) {
        this.listaDeMascotas = mascotas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View filaMascota = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fila_mascota, viewGroup, false);
        return new MyViewHolder(filaMascota);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        // Obtener la mascota de nuestra lista gracias al índice i
        Mascota mascota = listaDeMascotas.get(i);

        // Obtener los datos de la lista
        String nombreMascota = mascota.getNombre();
        int edadMascota = mascota.getEdad();
        String colorMascota = mascota.getColor();
        // Y poner a los TextView los datos con setText
        myViewHolder.nombre.setText(nombreMascota);
        myViewHolder.edad.setText(String.valueOf(edadMascota));
        myViewHolder.color.setText(String.valueOf(colorMascota));
    }

    @Override
    public int getItemCount() {
        return listaDeMascotas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, edad, color;

        MyViewHolder(View itemView) {
            super(itemView);
            this.nombre = itemView.findViewById(R.id.tvNombre);
            this.edad = itemView.findViewById(R.id.tvEdad);
            this.color = itemView.findViewById(R.id.tvColor);
        }
    }
}
