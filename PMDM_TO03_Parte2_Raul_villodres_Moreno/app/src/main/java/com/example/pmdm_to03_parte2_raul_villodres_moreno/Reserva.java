package com.example.pmdm_to03_parte2_raul_villodres_moreno;

public class Reserva {

    private String jinete;
    private int movil;
    private String caballo;
    private String fecha;
    private String hora;
    private String comentario;

    private long id; // El ID de la BD

    public Reserva(String jinete, int movil, String caballo, String fecha, String hora, String comentario) {
        this.jinete = jinete;
        this.movil = movil;
        this.caballo = caballo;
        this.fecha = fecha;
        this.hora = hora;
        this.comentario = comentario;
    }

    // Constructor para cuando instanciamos desde la BD
    public Reserva(String jinete, int movil, String caballo, String fecha, String hora, String comentario, long id) {
        this.jinete = jinete;
        this.movil = movil;
        this.caballo = caballo;
        this.fecha = fecha;
        this.hora = hora;
        this.comentario = comentario;
        this.id = id;
    }

    //Getters
    public String getJinete() {
        return jinete;
    }

    public int getMovil() {
        return movil;
    }

    public String getCaballo() {
        return caballo;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getComentario() {
        return comentario;
    }

    public long getId() {
        return id;
    }

    //Setters
    public void setJinete(String jinete) {
        this.jinete = jinete;
    }

    public void setMovil(int movil) {
        this.movil = movil;
    }

    public void setCaballo(String caballo) {
        this.caballo = caballo;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setId(long id) {
        this.id = id;
    }

    //Constructor
    @Override
    public String toString() {
        return "Reserva{" +
                "jinete='" + jinete + '\'' +
                ", movil=" + movil +
                ", caballo='" + caballo + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", comentario='" + comentario + '\'' +
                ", id=" + id +
                '}';
    }
}
