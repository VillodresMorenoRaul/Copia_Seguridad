package com.example.pmdm_to03_raul_villodres.Activity3;

public class Enlaces {
    private String nombre;
    private String link;
    private String email;
    private String categoria;
    private int foto;
    private long id;

    //Constructor estándar
    public Enlaces(String nombre, String link, String email, String categoria, int foto) {
        this.nombre = nombre;
        this.link = link;
        this.email = email;
        this.categoria = categoria;
        this.foto = foto;
    }

    //Constructor para base de datos
    public Enlaces(String nombre, String link, String email, String categoria, int foto, long id) {
        this.nombre = nombre;
        this.link = link;
        this.email = email;
        this.categoria = categoria;
        this.foto = foto;
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

    public int getFoto() {
        return foto;
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

    public void setFoto(int foto) {
        this.foto = foto;
    }

    //Constructor
    @Override
    public String toString() {
        return "Enlaces{" +
                "nombre='" + nombre + '\'' +
                ", link='" + link + '\'' +
                ", email='" + email + '\'' +
                ", categoria='" + categoria + '\'' +
                ", foto=" + foto +
                '}';
    }
}
