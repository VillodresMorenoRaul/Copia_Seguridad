package com.example.villodres_raul_to02_aplicacionesandroid;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class FragmentDetalle extends Fragment {

    public static final String TAG = "FragmentDetalle";

    private TextView NombreDelLibro;
    private TextView AutorDelLibro;
    private TextView EditorialDelLibro;
    private TextView PublicacionDelLibro;
    private TextView SinopsisDelLibro;
    private ImageView ImagenDelLibro;

    public static Fragment newInstance(Bundle bundle) {
        FragmentDetalle fragment = new FragmentDetalle();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_detalle, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NombreDelLibro = view.findViewById(R.id.nombreDelLibro);
        AutorDelLibro = view.findViewById(R.id.autorDelLibro);
        EditorialDelLibro = view.findViewById(R.id.editorialDelLibro);
        PublicacionDelLibro = view.findViewById(R.id.publicacionDelLibro);
        SinopsisDelLibro = view.findViewById(R.id.sinopsisDelLibro);
        ImagenDelLibro = view.findViewById(R.id.imagenDelLibro);

        if (getArguments() != null) {
            LibroEjercicio3 libro = getArguments().getParcelable(LibroEjercicio3.TAG);
            NombreDelLibro.setText(libro.getNombreLibro());
            AutorDelLibro.setText(libro.getAutorLibro());
            EditorialDelLibro.setText(libro.getEditorialLibro());
            PublicacionDelLibro.setText(libro.getFechaPublicacionLibro());
            SinopsisDelLibro.setText(libro.getSinopsisLibro());
            ImagenDelLibro.setImageResource(libro.getImagen());
        };
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}