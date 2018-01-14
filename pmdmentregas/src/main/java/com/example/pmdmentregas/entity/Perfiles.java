package com.example.pmdmentregas.entity;

import com.google.android.gms.maps.model.Marker;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tay on 12/1/18.
 */

@IgnoreExtraProperties
public class Perfiles {
    public double lat;
    public double lon;
    public Marker marker; // creamos la variable marker para relacionar el pin con el pais para cuando queramos borrarlo

    public Perfiles() {
    }

    public Perfiles(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;

    }
    /*
    Para subir un objeto perfil a Firebase tenemos que transformarlo en un hashMap mediante el método siguiente
     */
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("lat", lat);
        result.put("lon", lon);
        return result;
    }
    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

}
