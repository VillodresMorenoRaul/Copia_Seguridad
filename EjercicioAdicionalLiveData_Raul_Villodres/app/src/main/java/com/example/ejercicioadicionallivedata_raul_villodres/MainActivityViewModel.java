package com.example.ejercicioadicionallivedata_raul_villodres;

import android.os.CountDownTimer;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private CountDownTimer contador;
    private MutableLiveData<Integer> segundos = new MutableLiveData<Integer>();
    private MutableLiveData<Long> valorContador = new MutableLiveData<Long>();
    private MutableLiveData<Boolean> termina = new MutableLiveData<Boolean>();

    public void StartTimer(){
        termina.setValue(false);
        contador = new CountDownTimer(valorContador.getValue(), 1000) {
            @Override
            public void onTick(long l) {
                long tiempoRestante = l/1000;
                segundos.setValue((int) tiempoRestante);
                //segundos.setValue(Integer.parseInt(String.valueOf(tiempoRestante)));
            }

            @Override
            public void onFinish() {
                //Pongo la variable termina en true cuando acabe
                termina.setValue(true);
            }
        };
        contador.start();
    }

    //Cuando se use el método para parar el contador el programa termina y el contador se cancela.
    public void StopTimer(){
        termina.setValue(false);
        contador.cancel();
    }

    //Getters

    public void setTimerValue(long timerValue) {
        this.valorContador.setValue(timerValue);
    }

    public MutableLiveData<Integer> getSegundos() {
        return segundos;
    }

    public MutableLiveData<Long> getValorContador() {
        return valorContador;
    }

    public MutableLiveData<Boolean> getTermina() {
        return termina;
    }


    //Setters

    public void setSegundos(MutableLiveData<Integer> segundos) {
        this.segundos = segundos;
    }

    public void setValorContador(MutableLiveData<Long> valorContador) {
        this.valorContador = valorContador;
    }

    public void setTermina(MutableLiveData<Boolean> termina) {
        this.termina = termina;
    }
}
