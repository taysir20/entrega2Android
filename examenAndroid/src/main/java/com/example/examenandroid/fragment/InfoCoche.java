package com.example.examenandroid.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.examenandroid.R;
import com.example.examenandroid.entity.Coche;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoCoche extends Fragment {
    private TextView marca;
    private TextView modelo;
    private ImageView img;
    private Button btnVolver;
    private ArrayList<Coche> contenidoLista;
    private String imgUrl;
    private InfoCocheEvents infoCocheEvents;
    private InfoCocheListener infoCocheListener;


    public InfoCoche() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_info_coche, container, false);
        this.marca = fragmentView.findViewById(R.id.txtMarca);
        this.modelo = fragmentView.findViewById(R.id.txtModelo);
        this.img = fragmentView.findViewById(R.id.imgCoche);
        this.btnVolver = fragmentView.findViewById(R.id.btnVolver);
        this.btnVolver.setText(R.string.btnVolver);
        this.infoCocheEvents = new InfoCocheEvents(this);
        this.btnVolver.setOnClickListener(this.getInfoCocheEvents());

        return fragmentView;
    }

    public void establecerContenido(int pos){
        this.marca.setText(this.getContenidoLista().get(pos).marca);
        this.modelo.setText(this.getContenidoLista().get(pos).modelo);
        this.imgUrl=this.getContenidoLista().get(pos).urlImg;
        Glide.with(this.getContext()).load(this.getContenidoLista().get(pos).urlImg).into(this.img);
    }

    public TextView getMarca() {
        return marca;
    }

    public void setMarca(TextView marca) {
        this.marca = marca;
    }

    public TextView getModelo() {
        return modelo;
    }

    public void setModelo(TextView modelo) {
        this.modelo = modelo;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public Button getBtnVolver() {
        return btnVolver;
    }

    public void setBtnVolver(Button btnVolver) {
        this.btnVolver = btnVolver;
    }

    public ArrayList<Coche> getContenidoLista() {
        return contenidoLista;
    }

    public void setContenidoLista(ArrayList<Coche> contenidoLista) {
        this.contenidoLista = contenidoLista;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public InfoCocheEvents getInfoCocheEvents() {
        return infoCocheEvents;
    }

    public void setInfoCocheEvents(InfoCocheEvents infoCocheEvents) {
        this.infoCocheEvents = infoCocheEvents;
    }

    public InfoCocheListener getInfoCocheListener() {
        return infoCocheListener;
    }

    public void setInfoCocheListener(InfoCocheListener infoCocheListener) {
        this.infoCocheListener = infoCocheListener;
    }
}
