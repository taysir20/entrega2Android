package com.example.pmdmentregas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pmdmentregas.entity.Paises;
import com.example.pmdmentregas.firebase.InfoCiudadesFragmentListener;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoCiudadesFragment extends Fragment {
    private TextView nombre;
    private TextView descripcion;
    private ImageView imgCiudad;
    private Button btnVolver;
    private InfoCiudadesEvents infoCiudadesEvents;
    private InfoCiudadesFragmentListener infoCiudadesFragmentListener;


    public InfoCiudadesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_info_ciudades, container, false);
        this.nombre = (TextView) v.findViewById(R.id.lblNombre);
        this.descripcion= (TextView) v.findViewById(R.id.multilineDescrip);
        this.imgCiudad = (ImageView)  v.findViewById(R.id.imgCiudad);
        this.btnVolver = (Button) v.findViewById(R.id.btnVolver);
        this.btnVolver.setText(R.string.btnVolver);
        this.setInfoCiudadesEvents(new InfoCiudadesEvents(this));
        this.btnVolver.setOnClickListener(this.getInfoCiudadesEvents());
        return v;
    }

    public void setContenido(Paises pais){
        this.nombre.setText(pais.nombre);
        this.descripcion.setText(pais.descripcion);
        Glide.with(this.getContext()).load(pais.urlImg).into(this.getImgCiudad());
    }

    public TextView getNombre() {
        return nombre;
    }

    public void setNombre(EditText nombre) {
        this.nombre = nombre;
    }

    public TextView getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(EditText descripcion) {
        this.descripcion = descripcion;
    }

    public ImageView getImgCiudad() {
        return imgCiudad;
    }

    public void setImgCiudad(ImageView imgCiudad) {
        this.imgCiudad = imgCiudad;
    }

    public Button getBtnVolver() {
        return btnVolver;
    }

    public void setBtnVolver(Button btnVolver) {
        this.btnVolver = btnVolver;
    }

    public InfoCiudadesEvents getInfoCiudadesEvents() {
        return infoCiudadesEvents;
    }

    public void setInfoCiudadesEvents(InfoCiudadesEvents infoCiudadesEvents) {
        this.infoCiudadesEvents = infoCiudadesEvents;
    }

    public InfoCiudadesFragmentListener getInfoCiudadesFragmentListener() {
        return infoCiudadesFragmentListener;
    }

    public void setInfoCiudadesFragmentListener(InfoCiudadesFragmentListener infoCiudadesFragmentListener) {
        this.infoCiudadesFragmentListener = infoCiudadesFragmentListener;
    }
}
