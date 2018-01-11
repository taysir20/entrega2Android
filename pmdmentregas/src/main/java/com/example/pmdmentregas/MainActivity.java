package com.example.pmdmentregas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.pmdmentregas.firebase.FirebaseAdmin;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends AppCompatActivity {
    private MainActivityEvents mainActivityEvents;
    private FirebaseAdmin firebaseAdmin;
    private SupportMapFragment mapFragment; //creamos el SupportMapFragment que es más compatible que un MapFragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setFirebaseAdmin(new FirebaseAdmin());
        this.setMainActivityEvents(new MainActivityEvents(this));
       /*
       Mediante el método getSpportFragemntManagr que ya conocemos pues es un método exclusivo de clases
       que extienden AppCompatActivity vamos a asignar el componente MapFragment a la variable SupportMapFragment
        */
        this.mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);

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
}
