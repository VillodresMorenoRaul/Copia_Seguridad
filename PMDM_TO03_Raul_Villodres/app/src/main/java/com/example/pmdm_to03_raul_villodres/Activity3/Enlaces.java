package com.example.pmdm_to03_raul_villodres.Activity3;

public class Enlaces {
    private String nombre;
    private String link;
    private String email;
    private String categoria;
    private int imagen;
    private long id;

    //Constructor con imagen
    public Enlaces(String nombre, String link, String email, String categoria, int imagen) {
        this.nombre = nombre;
        this.link = link;
        this.email = email;
        this.categoria = categoria;
        this.imagen = imagen;
    }

    //Constructor sin imagen
    public Enlaces(String nombre, String link, String email, String categoria) {
        this.nombre = nombre;
        this.link = link;
        this.email = email;
        this.categoria = categoria;
        this.imagen = imagen;
    }

    //Constructor para base de datos
    public Enlaces(String nombre, String link, String email, String categoria, long id) {
        this.nombre = nombre;
        this.link = link;
        this.email = email;
        this.categoria = categoria;
        this.id = id;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public String getLink() {
        return link;
    }

    public String getEmail() {
        return email;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getImagen() {
        return imagen;
    }

    public long getId() {
        return id;
    }

    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setId(long id) {
        this.id = id;
    }

    //Constructor
    @Override
    public String toString() {
        return "Enlaces{" +
                "nombre='" + nombre + '\'' +
                ", link='" + link + '\'' +
                ", email='" + email + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
