package com.example.pmdmentregas;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.pmdmentregas.firebase.FirebaseAdmin;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends AppCompatActivity {
    private MainActivityEvents mainActivityEvents;
    private FirebaseAdmin firebaseAdmin;
    private SupportMapFragment mapFragment; //creamos el SupportMapFragment que es más compatible que un MapFragment
    private InfoCiudadesFragment infoCiudadesFragment; // declaramos el fragmento de la información de las ciudades

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setFirebaseAdmin(new FirebaseAdmin());
        this.setMainActivityEvents(new MainActivityEvents(this));
        this.getFirebaseAdmin().setFirebaseAdminListener(this.getMainActivityEvents());
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
        this.getInfoCiudadesFragment().setInfoCiudadesFragmentListener(this.getMainActivityEvents());
        FragmentTransaction transition = this.getSupportFragmentManager().beginTransaction();
        transition.hide(this.getInfoCiudadesFragment());
        transition.show(this.getMapFragment());
        transition.commit();


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
}
