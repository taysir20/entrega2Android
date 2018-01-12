package com.example.pmdmentregas;

import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by tay on 12/1/18.
 */

public class MostrarPosicionFragmentEvents implements View.OnClickListener,OnMapReadyCallback, GoogleMap.OnMarkerClickListener{


    private MostrarPosicionFragment mostrarPosicionFragment;

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

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}
