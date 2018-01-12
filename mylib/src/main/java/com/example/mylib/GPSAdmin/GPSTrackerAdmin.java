package com.example.mylib.GPSAdmin;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by tay on 12/1/18.
 */

public class GPSTrackerAdmin extends Service{
    /*
       Tenemos que extender de Service es decir de la clase padre Service que va a gestionar un servicio GPS
    */
    private GPSTrackerAdminEvents gpsTrackerAdminEvents;
    //Contexto de la clase que se usa para usar el locationManager
    private final Context mContext;

    //Si la posición se obtiene por GPS (la más precisa)
    boolean isGPSEnabled = false;

    // Si la posición se obtiene por red local (menos precisa)
    boolean isNetworkEnabled = false;
    //Si se puede obtener la localización
    boolean canGetLocation = false;

    Location location; // localización
    double latitude; // latitud
    double longitude; // longitud

    // La mínima distancia a la hora de actualizar la localización
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // El mínimo tiempo a la hora de actualizar la localización
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

    // Variable encargada de administrar todo lo relacionado con la localización
    protected LocationManager locationManager;

    public GPSTrackerAdmin(Context mContext) {
       this.setGpsTrackerAdminEvents(new GPSTrackerAdminEvents(this));
       this.mContext=mContext;
       this.getLocation(); // la primera llamada al getLocation que se produce al hacer el new del GPSTrackerAdmin
    }

    public Location getLocation() {
        try {
            //Inicializamos el location Manager
            locationManager = (LocationManager) mContext
                    .getSystemService(LOCATION_SERVICE);
            /*
            Se instancian las posiciones GPS o local, basta con que se instancie una o incluso las dos
             */
            // Para obtener el estado del GPS
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // Para obtener el estado del local
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            /*
            A continuación, tenemos un if/else que pregunta si alguno de los dos no es nulo
            dado que la posición la podemos obtener si al menos uno de estos dos (GPS o Network)
            no es nula.
            Una vez comprobado esto, dentro del else se pone a true
             la variable por la cual preguntamos si hemos podidos obtener la localización
             y se vuelven a hacer dos if/else de los cuéles
            uno comprueba si si El netWork es true y el otro si el GPS es true.
            Al menos uno de ellos debe de serlo o ambos y por tanto cada uno llama a las constantes
            que se encargan de actualziar esta posición es decir de vovler a llamar a este método
            (según la distancia o el tiempo transcurrido).
            Además como son distintas formas de obtener la localización entonces uno hará uso
            de NetWork_Provider y el otro de GPS_Provider respectivamente.
            Para acabar, si la localziación obtenido por al menos uno de los PROVIDER (constantes
             del propio  LocationManager) no es nula entonces
            almacenamos en dos variables la latitud y la longitiud

             */
            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                this.canGetLocation = true;
                // Obtener localización por local
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this.getGpsTrackerAdminEvents()); // se pasa el events
                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
                // Obtener localización por GPS
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this.getGpsTrackerAdminEvents()); // se pasa el events
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (SecurityException e) {
            //Si el try falla pasará por el catch para controlar la excepción
            e.printStackTrace();
        }

        return location;
    }

    public void stopUsingGPS(){
        if(locationManager != null){
            locationManager.removeUpdates(this.getGpsTrackerAdminEvents()); // el events recibe los eventos de stopUsingGPS
        }
    }

    /**
     * Function to get latitude
     * */
    //Devuelve la latitud
    public double getLatitude(){
        if(location != null){
            latitude = location.getLatitude();
        }

        // return latitude
        return latitude;
    }

    /**
     * Function to get longitude
     * */
    //Devuelve la longitud
    public double getLongitude(){
        if(location != null){
            longitude = location.getLongitude();
        }

        // return longitude
        return longitude;
    }

    /**
     * Function to check GPS/wifi enabled
     * @return boolean
     * */
    //Devuelve si se ha podido obtener la localización
    public boolean canGetLocation() {
        return this.canGetLocation;
    }

    /**
     * Function to show settings alert dialog
     * On pressing Settings button will lauch Settings Options
     * */

    //Pregunta al usuario si queremos dar permisos al GPS mediante un cuadro de diálogo (popup).
    //Se llama a este método siempre que el método getLocation() no puede ejecutarse dado que no tiene permisos
    /*
    Cuando se trabaja desde apps de desarrolladores los permisos tenemos que darlos nosotros desde los propios
    ajustes del móvil
     */
    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings");

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    public GPSTrackerAdminEvents getGpsTrackerAdminEvents() {
        return gpsTrackerAdminEvents;
    }

    public void setGpsTrackerAdminEvents(GPSTrackerAdminEvents gpsTrackerAdminEvents) {
        this.gpsTrackerAdminEvents = gpsTrackerAdminEvents;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
