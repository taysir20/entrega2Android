package com.example.examenandroid.entity;

/**
 * Created by tay on 19/12/17.
 */

public class Coche {
    public String marca;
    public String modelo;
    public String urlImg;

    public Coche() {
    }

    public Coche(String marca, String modelo, String urlImg) {
        this.marca = marca;
        this.modelo = modelo;
        this.urlImg=urlImg;
    }
}
