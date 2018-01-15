package com.example.pmdmentregas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mylib.AsyncTask.HttpAsyncTask;
import com.example.mylib.GPSAdmin.GPSTrackerAdmin;
import com.google.android.gms.maps.SupportMapFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class MostrarPosicionFragment extends Fragment {

    private Button btnVolver;
    private MostrarPosicionFragmentEvents mostrarPosicionFragmentEvents;
    private MostrarPosicionFragmentListener mostrarPosicionFragmentListener;
    private SupportMapFragment mapFragmentPosicion;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mostrar_posicion, container, false);
        this.setMostrarPosicionFragmentEvents(new MostrarPosicionFragmentEvents(this));
        this.btnVolver = (Button) v.findViewById(R.id.btnCerrar);
        this.btnVolver.setText(R.string.btnVolver);
        this.btnVolver.setOnClickListener(this.getMostrarPosicionFragmentEvents());
        this.mapFragmentPosicion = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragmentPosicion); // para que el frgment del mapa sea hijo del mostrarFragmentPoscion
        this.mapFragmentPosicion.getMapAsync(this.getMostrarPosicionFragmentEvents());




        return v;
    }


    public Button getBtnVolver() {
        return btnVolver;
    }

    public void setBtnVolver(Button btnVolver) {
        this.btnVolver = btnVolver;
    }

    public MostrarPosicionFragmentEvents getMostrarPosicionFragmentEvents() {
        return mostrarPosicionFragmentEvents;
    }

    public void setMostrarPosicionFragmentEvents(MostrarPosicionFragmentEvents mostrarPosicionFragmentEvents) {
        this.mostrarPosicionFragmentEvents = mostrarPosicionFragmentEvents;
    }

    public MostrarPosicionFragmentListener getMostrarPosicionFragmentListener() {
        return mostrarPosicionFragmentListener;
    }

    public void setMostrarPosicionFragmentListener(MostrarPosicionFragmentListener mostrarPosicionFragmentListener) {
        this.mostrarPosicionFragmentListener = mostrarPosicionFragmentListener;
    }

    public SupportMapFragment getMapFragmentPosicion() {
        return mapFragmentPosicion;
    }

    public void setMapFragmentPosicion(SupportMapFragment mapFragmentPosicion) {
        this.mapFragmentPosicion = mapFragmentPosicion;
    }


}
