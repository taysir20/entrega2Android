package com.example.pmdmentregas;

import android.view.View;

import com.example.pmdmentregas.firebase.InfoCiudadesFragmentListener;

/**
 * Created by tay on 12/1/18.
 */

public class InfoCiudadesEvents implements View.OnClickListener{
    private InfoCiudadesFragment infoCiudadesFragment;

    public InfoCiudadesEvents(InfoCiudadesFragment infoCiudadesFragment) {
        this.infoCiudadesFragment = infoCiudadesFragment;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnVolver){
            this.getInfoCiudadesFragment().getInfoCiudadesFragmentListener().pressBack();
        }
    }

    public InfoCiudadesFragment getInfoCiudadesFragment() {
        return infoCiudadesFragment;
    }

    public void setInfoCiudadesFragment(InfoCiudadesFragment infoCiudadesFragment) {
        this.infoCiudadesFragment = infoCiudadesFragment;
    }
}
