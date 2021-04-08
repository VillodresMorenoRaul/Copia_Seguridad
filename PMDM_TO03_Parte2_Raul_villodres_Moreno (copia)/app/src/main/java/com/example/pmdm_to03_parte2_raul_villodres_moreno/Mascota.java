package com.example.pmdm_to03_parte2_raul_villodres_moreno;

public class Mascota {

    private String nombre;
    private int edad;
    private String color;

    private long id; // El ID de la BD

    public Mascota(String nombre, int edad, String color) {
        this.nombre = nombre;
        this.edad = edad;
        this.color = color;
    }

    // Constructor para cuando instanciamos desde la BD
    public Mascota(String nombre, int edad, String color, long id) {
        this.nombre = nombre;
        this.edad = edad;
        this.color = color;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", color='" + color + '\'' +
                ", id=" + id +
                '}';
    }
}
