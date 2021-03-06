package com.example.pmdm_to03_raul_villodres.Actividad2;

public class Contador {
    private int cafes;
    private int tiempo;
    private  static final int DESCANSO = 5;

    public Contador() {
        this.cafes = 0;
        this.tiempo = DESCANSO;
    }

    public Contador(int c, int t) {
        this.cafes = c;
        this.tiempo = t;
    }

    public String aumentarTiempo(){
        this.tiempo += 1;
        return String.valueOf(this.tiempo) + ":00";
    }

    public String disminuirTiempo(){
        this.tiempo -= 1;
        if (this.tiempo < 1)
            tiempo = 1;
        return String.valueOf(this.tiempo) + ":00";
    }

    public String aumentarCafes(){
        this.cafes += 1;
        return String.valueOf(this.cafes);
    }

    public int getTiempo(){
        return  this.tiempo;
    }
}