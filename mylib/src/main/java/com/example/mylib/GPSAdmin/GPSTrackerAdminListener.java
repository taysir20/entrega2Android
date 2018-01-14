package com.example.mylib.GPSAdmin;

/**
 * Created by tay on 14/1/18.
 */

public interface GPSTrackerAdminListener {
    /*
    Creamos esta infertaz pues queremos que cada vez que se haga un cambio de localización se notifique al escuchador
    que yo quiera (en mi casa al mainActivityEvents) y de esta manera pueda volver a subir la nueva localización
    sobreescribiendo la última
     */
    public void firebaseLocationUpdate(boolean ok);
}
