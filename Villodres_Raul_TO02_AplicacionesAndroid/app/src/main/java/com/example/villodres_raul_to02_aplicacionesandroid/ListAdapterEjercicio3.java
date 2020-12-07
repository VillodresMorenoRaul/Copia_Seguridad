package com.example.villodres_raul_to02_aplicacionesandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ListAdapterEjercicio3 extends RecyclerView.Adapter<ListAdapterEjercicio3.NoteViewHolder> {

    private List<LibroEjercicio3> list;
    private OnItemClickListener listener;

    //Interfaz para el evento onClick de un elemento del RecyclerView
    public interface OnItemClickListener {
        void onItemClick(LibroEjercicio3 Libros);
    }

    //Constructor que se le pasa la lista de notas y el listener
    public ListAdapterEjercicio3(List<LibroEjercicio3> list, OnItemClickListener listener) {
        this.list = list;
        this.listener=listener;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_libro_ejercicio3, parent, false);

        return new NoteViewHolder(itemView);
    }

    //Método que vincula cada dato con la vista
    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        LibroEjercicio3 Libros = list.get(position);
        holder.title.setText(Libros.getNombreLibro());
        holder.bind(list.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * Clase ViewHolder
     */

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        public TextView title, content;

        public NoteViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            content = view.findViewById(R.id.content);

        }

        public void bind(final LibroEjercicio3 Libros, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(Libros);
                }
            });
        }
    }
}