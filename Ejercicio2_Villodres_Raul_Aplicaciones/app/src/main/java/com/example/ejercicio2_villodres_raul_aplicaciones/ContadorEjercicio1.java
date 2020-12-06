package com.example.ejercicio2_villodres_raul_aplicaciones;

public class ContadorEjercicio1 {
    private int cafes;
    private int tiempo;
    private  static final int DESCANSO = 5;

    public ContadorEjercicio1() {
        this.cafes = 0;
        this.tiempo = DESCANSO;
    }

    public ContadorEjercicio1(int c, int t) {
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

    public void aumentarCafes(){
        this.cafes += 1;
    }

    public int getTiempo(){
        return  this.tiempo;
    }

    public int obtenerCafes() {
        return cafes;
    }

    public void resetearContadorCafes(){
        this.cafes = 0;
    }
}
