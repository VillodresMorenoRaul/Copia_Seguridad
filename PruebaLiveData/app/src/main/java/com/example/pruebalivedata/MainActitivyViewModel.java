package com.example.pruebalivedata;

import android.os.CountDownTimer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActitivyViewModel extends ViewModel {
    private MutableLiveData<Boolean> finished = new MutableLiveData<Boolean>();
    private MutableLiveData<Long> timerValue = new MutableLiveData<Long>();
    private MutableLiveData<Integer> seconds = new MutableLiveData<Integer>();
    private CountDownTimer timer;

    public void startTimer() {
        // sigo que no he acabado todavía
        finished.setValue(false);
        // creo el temporizador
        timer = new CountDownTimer(timerValue.getValue(), 1000) {
            @Override
            public void onTick(long l) {
                // extraigo los segundos
                long timeLeft = l / 1000;
                // y los publico en su variable
                seconds.setValue((int) timeLeft);
            }
            @Override
            public void onFinish() {
                // al acabar, establezco que se acabó
                finished.setValue(true);
            }
        };
        // y lo lanzo
        timer.start();
    }

    public void stopTimer() {
        timer.cancel();
        finished.setValue(true);
    }

    public void setTimerValue(long timerValue) {
        this.timerValue.setValue(timerValue);
    }

    public LiveData<Integer> getSecondsLiveData() {
        return seconds;
    }

    public LiveData<Boolean> getFinishedLiveData() {
        return finished;
    }
}
