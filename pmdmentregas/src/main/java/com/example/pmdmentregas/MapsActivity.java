package com.example.pmdmentregas;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        /*
        Como podemos ver el propio MapsActivity agrega el mapFragment mediante el
        SupportMapFragment
         */
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        /*
        Por último mediante la función "getMapAsync" que es el listener, se espera a que se hayan cargado
        todos los servicios de google maps.
        Una vez que se hayan cargado entonces se procede a llamar al mñetodo "onMapReady"
        dado que para trabajar con el mapa este debe de estar totalmente cargado.
        Este método "onMapReady" de implementa mediante el listener "OnMapReadyCallback"
         */

        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //Como podemos ver se crea una coordenada/posición
        LatLng sydney = new LatLng(-34, 151);
        //Después se añade un marker es decir un pin/anotación en esa coordenada con un título
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //Po último, se establece que la cámara que sigue el centro del mapa se colo que justo en esa coordenada que hemos creado arriba
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
