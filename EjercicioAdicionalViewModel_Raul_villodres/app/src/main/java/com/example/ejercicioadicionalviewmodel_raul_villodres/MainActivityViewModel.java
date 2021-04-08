package com.example.ejercicioadicionalviewmodel_raul_villodres;

import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    int contador = 0;

    public void sumarNumero(){
        contador++;
    }

    public void restarNumero(){
        contador--;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
}
