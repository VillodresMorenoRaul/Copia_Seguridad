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
        //Ponemos el boolean termina en falso para que el programa pueda continuar
        termina.setValue(false);
        //Instanciamos un nuevo contador con el valor que le otorguemos
        contador = new CountDownTimer(valorContador.getValue(), 1000) {

            //Mostramos el valor
            @Override
            public void onTick(long l) {
                long tiempoRestante = l/1000;
                segundos.setValue(Integer.parseInt(String.valueOf(tiempoRestante)));
            }

            //Pongo la variable termina en true cuando acabe
            @Override
            public void onFinish() {
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
    public MutableLiveData<Integer> getSegundos() {
        return segundos;
    }



    //Setters
    public void setTimerValue(long timerValue) {
        this.valorContador.setValue(timerValue);
    }


}
