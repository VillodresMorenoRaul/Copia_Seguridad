package com.example.pmdm_to03_parte2_raul_villodres_moreno;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorReservas extends RecyclerView.Adapter<AdaptadorReservas.MyViewHolder> {

    private List<Reserva> listaDeReservas;

    public void setListaDeReservas(List<Reserva> listaDeReservas) {
        this.listaDeReservas = listaDeReservas;
    }

    public AdaptadorReservas(List<Reserva> reservas) {
        this.listaDeReservas = reservas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View filaMascota = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fila_reserva, viewGroup, false);
        return new MyViewHolder(filaMascota);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        // Obtener la reserva de nuestra lista gracias al índice i
        Reserva reserva = listaDeReservas.get(i);

        // Obtener los datos de la lista
        String jineteReserva = reserva.getJinete();
        int movilReserva = reserva.getMovil();
        String caballoReserva = reserva.getCaballo();
        String fechaReserva = reserva.getFecha();
        String horaReserva = reserva.getHora();
        String comentarioReserva = reserva.getComentario();

        // Y poner a los TextView los datos con setText
        myViewHolder.jinete.setText(jineteReserva);
        myViewHolder.movil.setText(String.valueOf(movilReserva));
        myViewHolder.caballo.setText(String.valueOf(caballoReserva));
        myViewHolder.fecha.setText(String.valueOf(fechaReserva));
        myViewHolder.hora.setText(String.valueOf(horaReserva));
        myViewHolder.comentario.setText(String.valueOf(comentarioReserva));

    }

    @Override
    public int getItemCount() {
        return listaDeReservas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView jinete, movil, caballo, fecha, hora, comentario;

        MyViewHolder(View itemView) {
            super(itemView);
            this.jinete = itemView.findViewById(R.id.tvJinete);
            this.movil = itemView.findViewById(R.id.tvMovil);
            this.caballo = itemView.findViewById(R.id.tvCaballo);
            this.fecha = itemView.findViewById(R.id.tvFecha);
            this.hora = itemView.findViewById(R.id.tvHora);
            this.comentario = itemView.findViewById(R.id.tvComentario);
        }
    }
}
