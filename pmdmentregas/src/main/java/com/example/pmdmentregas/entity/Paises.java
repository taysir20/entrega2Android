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
    public int latitud;
    public int longitud;
    public String nombre;

    public Paises() {
    }

    public Paises(int latitud, int longitud, String nombre) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre=nombre;
    }
}
