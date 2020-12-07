package com.example.villodres_raul_to02_aplicacionesandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity3 extends AppCompatActivity implements FragmentLista.FragmentListaCallback{

    private FragmentDetalle fragmentDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentcontainer,new FragmentLista(),FragmentLista.TAG);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onNoteView(LibroEjercicio3 libro) {

        FragmentManager fm=getSupportFragmentManager();
        fragmentDetalle = (FragmentDetalle) fm.findFragmentByTag(FragmentDetalle.TAG);

        if (fragmentDetalle == null) {
            Bundle bundle= null;
            if (libro!=null){
                bundle= new Bundle();
                bundle.putParcelable(LibroEjercicio3.TAG,libro);
            }
            fragmentDetalle = (FragmentDetalle) fragmentDetalle.newInstance(bundle);
        }

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentcontainer, fragmentDetalle, FragmentDetalle.TAG);
        ft.addToBackStack(null);
        ft.commit();

    }
}