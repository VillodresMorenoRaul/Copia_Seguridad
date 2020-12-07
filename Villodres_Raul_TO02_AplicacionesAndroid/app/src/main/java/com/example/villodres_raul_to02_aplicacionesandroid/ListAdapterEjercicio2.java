package com.example.villodres_raul_to02_aplicacionesandroid;

//Importes
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.villodres_raul_to02_aplicacionesandroid.databinding.ItemLibroEjercicio2Binding;

import java.util.List;


public class ListAdapterEjercicio2 extends RecyclerView.Adapter<ListAdapterEjercicio2.MyViewHolder> {

    private List<LibroEjercicio2> listaElementos;

    //En esta linea pasamos el array al constructor.
    public ListAdapterEjercicio2(List<LibroEjercicio2> libros){
        listaElementos = libros;
    }

    //Definición del OnItemClickListener
    private OnItemClickListener listener;

    //Aquí declaramos el onItemClickListener
    public interface OnItemClickListener {
        void onItemClick(View itemView, int posicion);
    }

    public void setOnItemClickListener (OnItemClickListener listener){this.listener = listener;}

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        ItemLibroEjercicio2Binding binding; //Esta parte del código es la primera parte para implementar el binding, declarando la variable.

        //Dentro del MyViewHolder procedemos a hacer que el binding tome los datos adecuados para su funcionamiento, además de establecer el setOnClickListener
        public MyViewHolder(ItemLibroEjercicio2Binding b){
            super(b.getRoot());
            binding = b;
            binding.MostrarDatos.setOnClickListener(this);
        }

        //Esta es la función onClick, que comprueba la posición, y si existe realiza su acción correspondiente
        @Override
        public void onClick (View view){
            if (listener != null){
                int posicion = getAdapterPosition();
                if(posicion != RecyclerView.NO_POSITION){
                    listener.onItemClick(itemView, posicion);
                }
            }
        }
    }

    //Y este de aquí abajo es el último cambio para poder implementar el ViewBinding, donde en esa linea realizamos todas las tareas que se harían en el método con una vista, pero usando el binding en su lugar
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return new MyViewHolder(ItemLibroEjercicio2Binding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        LibroEjercicio2 libro = listaElementos.get(position); //Creamos esta linea de código para obtener el nímero de posicion del elemento a añadir

        //Y aquí abajo declaramos un holder que tomará ciertos elementos
        holder.binding.NombreLibro.setText(libro.getNombreLibro());
        holder.binding.AutorLibro.setText(libro.getAutorLibro());
        holder.binding.EditorialLibro.setText(libro.getEditorialLibro());
        holder.binding.FechaPublicacionLibro.setText(libro.getFechaPublicacionLibro());
        holder.binding.SinopsisLibro.setText(libro.getSinopsisLibro());
        holder.binding.ImagenLibro.setImageResource(listaElementos.get(position).getImagen());
    }

    public int getItemCount(){return listaElementos.size();}
}