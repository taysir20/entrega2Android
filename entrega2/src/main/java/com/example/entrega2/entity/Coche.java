package com.example.entrega2.entity;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by tay on 2/12/17.
 */

@IgnoreExtraProperties
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
