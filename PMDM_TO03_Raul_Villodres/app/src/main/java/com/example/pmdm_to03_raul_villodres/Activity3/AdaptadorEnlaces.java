package com.example.pmdm_to03_raul_villodres.Activity3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmdm_to03_raul_villodres.R;

import java.util.List;

public class AdaptadorEnlaces extends RecyclerView.Adapter<AdaptadorEnlaces.MyViewHolder> {

    private List <Enlaces> listaDeEnlaces;

    public void setListaDeEnlaces(List<Enlaces> listaDeEnlaces){
        this.listaDeEnlaces = listaDeEnlaces;
    }

    public AdaptadorEnlaces(List<Enlaces> enlaces){this.listaDeEnlaces = enlaces;}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View filaEnlace = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fila_enlace, viewGroup, false);
        return new MyViewHolder(filaEnlace);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i){
        Enlaces enlace = listaDeEnlaces.get(i);

        String nombreEnlace = enlace.getNombre();
        String linkEnlace = enlace.getLink();
        String emailEnlace = enlace.getEmail();
        String categoriaEnlace = enlace.getCategoria();

        myViewHolder.nombre.setText(nombreEnlace);
        myViewHolder.link.setText(linkEnlace);
        myViewHolder.email.setText(emailEnlace);
        myViewHolder.categoria.setText(categoriaEnlace);
    }

    @Override
    public int getItemCount(){
        return listaDeEnlaces.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, link, email, categoria;

        MyViewHolder(View itemView){
            super(itemView);
                this.nombre = itemView.findViewById(R.id.tvnombre);
                this.link = itemView.findViewById(R.id.tvLink);
                this.email = itemView.findViewById(R.id.tvEmail);
                this.categoria = itemView.findViewById(R.id.tvCategoria);
            }
        }
    }
