package com.example.pmdmentregas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class MostrarPosicionFragment extends Fragment {

    private Button btnVolver;
    private MostrarPosicionFragmentEvents mostrarPosicionFragmentEvents;
    private MostrarPosicionFragmentListener mostrarPosicionFragmentListener;
    private SupportMapFragment mapFragmentPosicion;


    //Variables que serán seteadas con el contenido del JSON que descargamos del HttpJsonAsyncTask
    private TextView txtLocation;
    private TextView txtTiempo;
    private TextView txtTemperatura;
    private TextView txtHumedad;




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
        this.txtLocation = (TextView) v.findViewById(R.id.txtLocation);
        this.txtTiempo = (TextView) v.findViewById(R.id.txtTiempo);
        this.txtTemperatura = (TextView) v.findViewById(R.id.txtTemperatura);
        this.txtHumedad = (TextView) v.findViewById(R.id.txtHumedad);




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


    public TextView getTxtLocation() {
        return txtLocation;
    }

    public void setTxtLocation(TextView txtLocation) {
        this.txtLocation = txtLocation;
    }

    public TextView getTxtTiempo() {
        return txtTiempo;
    }

    public void setTxtTiempo(TextView txtTiempo) {
        this.txtTiempo = txtTiempo;
    }

    public TextView getTxtTemperatura() {
        return txtTemperatura;
    }

    public void setTxtTemperatura(TextView txtTemperatura) {
        this.txtTemperatura = txtTemperatura;
    }

    public TextView getTxtHumedad() {
        return txtHumedad;
    }

    public void setTxtHumedad(TextView txtHumedad) {
        this.txtHumedad = txtHumedad;
    }
}
