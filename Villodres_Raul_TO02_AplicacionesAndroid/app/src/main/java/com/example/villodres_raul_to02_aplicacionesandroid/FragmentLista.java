package com.example.villodres_raul_to02_aplicacionesandroid;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentLista extends Fragment {

    public static final String TAG ="FragmentLista" ;
    private RecyclerView rvlibros;
    private RecyclerView.Adapter adapternote;
    private ListAdapterEjercicio3.OnItemClickListener listener;
    private FragmentListaCallback callback;


    interface FragmentListaCallback {
        void  onNoteView(LibroEjercicio3 libro);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (FragmentListaCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement FragmentListaCallback");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista,
                container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Aquí ingresamos unos valores al array que almacenará los objetos.
        ArrayList<LibroEjercicio3> listaLibros = new ArrayList<>();
        listaLibros.add(new LibroEjercicio3(getString(R.string.Don_Quijote_Nombre), getString(R.string.Don_Quijote_Autor), getString(R.string.Don_Quijote_Editorial), getString(R.string.Don_Quijote_FechaPublicacion), getString(R.string.Don_Quijote_Sinopsis), R.drawable.donquijote));
        listaLibros.add(new LibroEjercicio3(getString(R.string.Fuentes_Del_Silencio_Nombre), getString(R.string.Fuentes_Del_Silencio_Autor) , getString(R.string.Fuentes_Del_Silencio_Editorial), getString(R.string.Fuentes_Del_Silencio_FechaPublicacion), getString(R.string.Fuentes_Del_Silencio_Sinopsis), R.drawable.fuentesdelsilencio));
        listaLibros.add(new LibroEjercicio3(getString(R.string.Harry_Potter_Nombre), getString(R.string.Harry_Potter_Autor), getString(R.string.Harry_Potter_Editorial), getString(R.string.Harry_Potter_FechaPublicacion), getString(R.string.Harry_Potter_Sinopsis), R.drawable.hppiedrafilosofal));
        listaLibros.add(new LibroEjercicio3(getString(R.string.Uzumaki_Nombre), getString(R.string.Uzumaki_Autor), getString(R.string.Uzumaki_Editorial), getString(R.string.Uzumaki_FechaPublicacion), getString(R.string.Uzumaki_Sinopsis), R.drawable.uzumaki));
        listaLibros.add(new LibroEjercicio3(getString(R.string.Coraline_Nombre), getString(R.string.Coraline_Autor), getString(R.string.Coraline_Editorial), getString(R.string.Coraline_FechaPublicacion), getString(R.string.Coraline_Sinopsis), R.drawable.coraline));
        listaLibros.add(new LibroEjercicio3(getString(R.string.Color_Del_Espacio_Nombre), getString(R.string.Color_Del_Espacio_Autor), getString(R.string.Color_Del_Espacio_Editorial), getString(R.string.Color_Del_Espacio_FechaPublicacion), getString(R.string.Color_Del_Espacio_Sinopsis), R.drawable.elcolorquecayodelespacio));



        //Se identifica el RecyclerView
        rvlibros = view.findViewById(R.id.rvLibros);
        rvlibros.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        listener = new ListAdapterEjercicio3.OnItemClickListener() {
            @Override
            public void onItemClick(LibroEjercicio3 libro) {
                callback.onNoteView(libro);
            }
        };
        adapternote = new ListAdapterEjercicio3(listaLibros, listener);
        rvlibros.setAdapter(adapternote);
    }

}