package com.example.pmdmentregas.entity;

/**
 * Created by tay on 11/1/18.
 */

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by tay on 2/12/17.
 */
/*
Creamos la clase Paises, a la cuál se accede cuando descargamos una rama de la bbdd desde el método
downloadBranch de Firebase y con el snapshot obtenido entonces creamos un objeto que tiene como atributos
la latitud y longitud que hemos descargado de la base de datos.
*/

@IgnoreExtraProperties
public class Paises {
    public double lat;
    public double lon;
    public String nombre;

    public Paises() {
    }

    public Paises(double lat, double lon, String nombre) {
        this.lat = lat;
        this.lon = lon;
        this.nombre=nombre;
    }
}
