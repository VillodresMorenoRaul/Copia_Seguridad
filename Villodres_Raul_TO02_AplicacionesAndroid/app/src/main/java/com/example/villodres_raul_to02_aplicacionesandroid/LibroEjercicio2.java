package com.example.villodres_raul_to02_aplicacionesandroid;

import java.io.Serializable;

public class LibroEjercicio2 implements Serializable {
    public String nombreLibro;
    public String autorLibro;
    public String editorialLibro;
    public String fechaPublicacionLibro;
    public String sinopsisLibro;
    public int imagenLibro;


    //Constructor
    public LibroEjercicio2(String nombreLibro, String autorLibro, String editorialLibro, String fechaPublicacionLibro, String sinopsisLibro, int imagenLibro) {
        this.nombreLibro = nombreLibro;
        this.autorLibro = autorLibro;
        this.editorialLibro = editorialLibro;
        this.fechaPublicacionLibro = fechaPublicacionLibro;
        this.sinopsisLibro = sinopsisLibro;
        this.imagenLibro = imagenLibro;
    }

    //Getters y Setters

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public String getAutorLibro() {
        return autorLibro;
    }

    public void setAutorLibro(String autorLibro) {
        this.autorLibro = autorLibro;
    }

    public String getEditorialLibro() {
        return editorialLibro;
    }

    public void setEditorialLibro(String editorialLibro) {
        this.editorialLibro = editorialLibro;
    }

    public String getFechaPublicacionLibro() {
        return fechaPublicacionLibro;
    }

    public void setFechaPublicacionLibro(String fechaPublicacionLibro) {
        this.fechaPublicacionLibro = fechaPublicacionLibro;
    }

    public String getSinopsisLibro() {
        return sinopsisLibro;
    }

    public void setSinopsisLibro(String sinopsisLibro) {
        this.sinopsisLibro = sinopsisLibro;
    }

    public int getImagen() {return imagenLibro;}

    public void setImagen(int imagenLibro){this.imagenLibro = imagenLibro;}
}

