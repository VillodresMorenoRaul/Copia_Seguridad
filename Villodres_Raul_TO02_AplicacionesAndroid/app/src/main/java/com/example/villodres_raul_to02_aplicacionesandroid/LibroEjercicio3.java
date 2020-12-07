package com.example.villodres_raul_to02_aplicacionesandroid;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class LibroEjercicio3 implements Parcelable {

    public static final String TAG = "Libro";

    public String nombreLibro;
    public String autorLibro;
    public String editorialLibro;
    public String fechaPublicacionLibro;
    public String sinopsisLibro;
    public int imagenLibro;

    public LibroEjercicio3(String nombreLibro, String autorLibro, String editorialLibro, String fechaPublicacionLibro, String sinopsisLibro, int imagenLibro) {
        this.nombreLibro = nombreLibro;
        this.autorLibro = autorLibro;
        this.editorialLibro = editorialLibro;
        this.fechaPublicacionLibro = fechaPublicacionLibro;
        this.sinopsisLibro = sinopsisLibro;
        this.imagenLibro = imagenLibro;
    }

    protected LibroEjercicio3(Parcel in) {
        nombreLibro = in.readString();
        autorLibro = in.readString();
        editorialLibro = in.readString();
        fechaPublicacionLibro = in.readString();
        sinopsisLibro = in.readString();
        imagenLibro = in.readInt();
    }

    public static final Creator<LibroEjercicio3> CREATOR = new Creator<LibroEjercicio3>() {
        @Override
        public com.example.villodres_raul_to02_aplicacionesandroid.LibroEjercicio3 createFromParcel(Parcel in) {
            return new com.example.villodres_raul_to02_aplicacionesandroid.LibroEjercicio3(in);
        }

        @Override
        public com.example.villodres_raul_to02_aplicacionesandroid.LibroEjercicio3[] newArray(int size) {
            return new com.example.villodres_raul_to02_aplicacionesandroid.LibroEjercicio3[size];
        }
    };

    @NonNull
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombreLibro);
        parcel.writeString(autorLibro);
        parcel.writeString(editorialLibro);
        parcel.writeString(fechaPublicacionLibro);
        parcel.writeString(sinopsisLibro);
        parcel.writeInt(imagenLibro);
    }
}
