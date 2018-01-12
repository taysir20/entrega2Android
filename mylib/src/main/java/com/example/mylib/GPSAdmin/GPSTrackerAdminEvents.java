package com.example.mylib.GPSAdmin;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by tay on 12/1/18.
 */

public class GPSTrackerAdminEvents implements LocationListener{
    /*
    implementamos los métodos del LocationListener dado que en el GPSTrackerAdmin dentro del método
    getLocation() vamos a pasar por parámetro este events y para ello debe de implementar estos el listener
     */

    private GPSTrackerAdmin gpsTrackerAdmin;

    public GPSTrackerAdminEvents(GPSTrackerAdmin gpsTrackerAdmin) {
        this.gpsTrackerAdmin = gpsTrackerAdmin;
    }

    //Llamado cuando hay un cambio de localización
    @Override
    public void onLocationChanged(Location location) {
        //Vamos a ver la localización que nos devuelve la llamada desde GPSTrackerAdmin de este método:
        System.out.println("La localización es: " + location.getLatitude() + " " + location.getLongitude());

    }
    //Llamado cuando hay un cambio de estado
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }
    //Llamado cuando el proveedor (antena gps o la red local) está activado
    @Override
    public void onProviderEnabled(String s) {

    }
    //Llamado cuando el proveedor (antena gps o la red local) está desactivado
    @Override
    public void onProviderDisabled(String s) {

    }
}
