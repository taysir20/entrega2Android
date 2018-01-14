package com.example.pmdmentregas;

import android.view.View;

import com.example.mylib.GPSAdmin.GPSTrackerAdmin;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by tay on 12/1/18.
 */

public class MostrarPosicionFragmentEvents implements View.OnClickListener,OnMapReadyCallback, GoogleMap.OnMarkerClickListener{


    private MostrarPosicionFragment mostrarPosicionFragment;
    private GoogleMap mapPosition;

    public MostrarPosicionFragmentEvents(MostrarPosicionFragment mostrarPosicionFragment) {
        this.mostrarPosicionFragment = mostrarPosicionFragment;
    }

    public MostrarPosicionFragment getMostrarPosicionFragment() {
        return mostrarPosicionFragment;
    }

    public void setMostrarPosicionFragment(MostrarPosicionFragment mostrarPosicionFragment) {
        this.mostrarPosicionFragment = mostrarPosicionFragment;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnCerrar){
            this.getMostrarPosicionFragment().getMostrarPosicionFragmentListener().ocultarMostrarPoscionFragment();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapPosition = googleMap;
        if(mapPosition!=null){
            mapPosition.setOnMarkerClickListener(this);//decimos al mapa cuál es su escuchador para cuando pulsemos los pines
            this.getMostrarPosicionFragment().getMostrarPosicionFragmentListener().setMapPosition(mapPosition);
            System.out.println("¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡Comprobación de error 1 !!!!!!!!!!!!!!!!!!!!!!!!");
            DataHolder.MyDataHolder.getFirebaseAdmin().downloadDataAndObserveBranchChanges("Perfiles/" + DataHolder.MyDataHolder.getFirebaseAdmin().getmAuth().getCurrentUser().getUid()); // llamo al método de descarga con la rama que quiero que observe a partir de la raíz.

        }


    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    public GoogleMap getMapPosition() {
        return mapPosition;
    }

    public void setMapPosition(GoogleMap mapPosition) {
        this.mapPosition = mapPosition;
    }
}
