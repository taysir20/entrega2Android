package com.example.pmdmentregas;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;


import com.example.mylib.GPSAdmin.GPSTrackerAdmin;
import com.example.pmdmentregas.firebase.FirebaseAdmin;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends AppCompatActivity {
    private MainActivityEvents mainActivityEvents;
    private FirebaseAdmin firebaseAdmin;
    private SupportMapFragment mapFragment; //creamos el SupportMapFragment que es más compatible que un MapFragment
    private InfoCiudadesFragment infoCiudadesFragment; // declaramos el fragmento de la información de las ciudades
    private Button btnTracker;
    private MostrarPosicionFragment mostrarPosicionFragment;
    private GPSTrackerAdmin gpsTrackerAdmin; // creamos la variable del GPSTrackerAdmin para poder inicializarla

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btnTracker = this.findViewById(R.id.btnTracker);
        this.setFirebaseAdmin(new FirebaseAdmin());
        this.setMainActivityEvents(new MainActivityEvents(this));
        this.getFirebaseAdmin().setFirebaseAdminListener(this.getMainActivityEvents());
        this.btnTracker.setOnClickListener(this.getMainActivityEvents());
        this.btnTracker.setText(R.string.btnTracker);
       /*
       Mediante el método getSpportFragemntManagr que ya conocemos pues es un método exclusivo de clases
       que extienden AppCompatActivity vamos a asignar el componente MapFragment a la variable SupportMapFragment
        */
        this.mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        this.mapFragment.getMapAsync(this.getMainActivityEvents()); // se setea el escuchador del mapa que es el events del main activity pues es quién lo implementa
        /*Con esté método getMapAsync se espera a que se hayan cargado
        todos los servicios de google maps.
        Una vez que se hayan cargado entonces se procede a llamar al mñetodo "onMapReady"
        dado que para trabajar con el mapa este debe de estar totalmente cargado.
                Este método "onMapReady" de implementa mediante el listener "OnMapReadyCallback"
         */
       this.infoCiudadesFragment = (InfoCiudadesFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentInfo);
       this.mostrarPosicionFragment = (MostrarPosicionFragment) getSupportFragmentManager().findFragmentById(R.id.trackerFragment);
        this.getInfoCiudadesFragment().setInfoCiudadesFragmentListener(this.getMainActivityEvents());
        this.getMostrarPosicionFragment().setMostrarPosicionFragmentListener(this.getMainActivityEvents());
        FragmentTransaction transition = this.getSupportFragmentManager().beginTransaction();
        transition.hide(this.getInfoCiudadesFragment());
        transition.hide(this.getMostrarPosicionFragment());
        transition.show(this.getMapFragment());
        transition.commit();

         /*
        LLamamos a un método creado por nosotros que inicialice el GPSTracker del lib y que pregunta si
        se puede obtener la localización o no y si no se puede entonces que llame a la función de pedir permisos.
         */

        this.GPSTrackerInitialize();
    }

    public void GPSTrackerInitialize(){
        this.setGpsTrackerAdmin(new GPSTrackerAdmin(this)); //inicializamos el GPSTrackerAdmin pasándole por parámetro el contexto que en este caso es el propio activity
        /*
        IMPORTANTE: El contexto para inicializarlo siempre debe de ser un activity
         */
        //Comprobamos que se puede obtener la localización en el caso contrario entonces se piden los permisos correspondientes.
        if (this.getGpsTrackerAdmin().canGetLocation()){
            System.out.println("La localización por primera vez es: " + this.getGpsTrackerAdmin().getLatitude() + " " + this.getGpsTrackerAdmin().getLongitude());
        }else{
            // Se llama a la función para pedir los permisos
            this.getGpsTrackerAdmin().showSettingsAlert();
        }
    }

    public MainActivityEvents getMainActivityEvents() {
        return mainActivityEvents;
    }

    public void setMainActivityEvents(MainActivityEvents mainActivityEvents) {
        this.mainActivityEvents = mainActivityEvents;
    }

    public FirebaseAdmin getFirebaseAdmin() {
        return firebaseAdmin;
    }

    public void setFirebaseAdmin(FirebaseAdmin firebaseAdmin) {
        this.firebaseAdmin = firebaseAdmin;
    }

    public SupportMapFragment getMapFragment() {
        return mapFragment;
    }

    public void setMapFragment(SupportMapFragment mapFragment) {
        this.mapFragment = mapFragment;
    }

    public InfoCiudadesFragment getInfoCiudadesFragment() {
        return infoCiudadesFragment;
    }

    public void setInfoCiudadesFragment(InfoCiudadesFragment infoCiudadesFragment) {
        this.infoCiudadesFragment = infoCiudadesFragment;
    }

    public Button getBtnTracker() {
        return btnTracker;
    }

    public void setBtnTracker(Button btnTracker) {
        this.btnTracker = btnTracker;
    }

    public MostrarPosicionFragment getMostrarPosicionFragment() {
        return mostrarPosicionFragment;
    }

    public void setMostrarPosicionFragment(MostrarPosicionFragment mostrarPosicionFragment) {
        this.mostrarPosicionFragment = mostrarPosicionFragment;
    }

    public GPSTrackerAdmin getGpsTrackerAdmin() {
        return gpsTrackerAdmin;
    }

    public void setGpsTrackerAdmin(GPSTrackerAdmin gpsTrackerAdmin) {
        this.gpsTrackerAdmin = gpsTrackerAdmin;
    }
}
